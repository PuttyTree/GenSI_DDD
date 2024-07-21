package com.roy.gensi.genapp.domain.genservice.service.impl.local;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.GsService;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.entity.SimpleResponseMsg;
import com.roy.gensi.genapp.domain.genservice.repository.BusiServiceRepository;
import com.roy.gensi.genapp.domain.genservice.repository.ResponseMsgRepository;
import com.roy.gensi.genapp.domain.genservice.service.SyncBusiService;
import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;
import com.roy.gensi.genapp.domain.hisrequest.repository.GsRequestRepository;
import com.roy.gensi.genapp.domain.hisrequest.service.GsRequestDomainService;
import com.roy.gensi.genapp.domain.sysManage.service.SysManageDomainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author loulan
 */
@Service("LocalSyncBusiService")
public class LocalSyncBusiServiceImpl implements SyncBusiService {

    @Autowired
    BusiServiceRepository busiServiceRepository;
    @Autowired
    ResponseMsgRepository responseMsgRepository;

    @Autowired
    SysManageDomainService sysManageDomainService;

    @Autowired
    GsRequestRepository gsRequestRepository;

    @Autowired
    GsRequestDomainService gsRequestDomainService;

    @Override
    public SimpleResponseMsg dealMessageSync(SimpleRequestMsg requestMsg,Logger logger) {
        SimpleResponseMsg response = new SimpleResponseMsg();
        SimpleRequestMsgHeader reqHeader = requestMsg.getRequestMsgHeader();
        logger.info("GsRestInterface: 请求验证通过 request=> " + requestMsg);
        //报文检查通过后，同步返回请求接收成功的响应，异步推送业务处理结果
        final List<GsHisRequest> gsRequests = gsRequestDomainService.queryGsRequest(reqHeader.getTransId(), reqHeader.getServiceCode());
        String hisResponse;
        if (null != gsRequests && !gsRequests.isEmpty()) {
            //重复的请求，直接推送之前的响应结果。
            hisResponse = gsRequests.get(0).getRspbody();
            logger.info("GsRestInterface: 查询到历史请求记录，响应结果：hisResponse => " + hisResponse);
            response = JSON.parseObject(hisResponse, SimpleResponseMsg.class);
        } else {
            //处理实际业务
            response = this.doBusi(requestMsg,logger);
        }
        return response;
    }

    private SimpleResponseMsg doBusi(SimpleRequestMsg requestMsg, Logger logger) {
        SimpleResponseMsg simpleResponseMsg = new SimpleResponseMsg();
        simpleResponseMsg.getHeaderFromRequest(requestMsg);
        final JSONObject responseBody = new JSONObject();

        final SimpleRequestMsgHeader requestMsgHeader = requestMsg.getRequestMsgHeader();
        GsService gsService = busiServiceRepository.getGsService(requestMsgHeader.getServiceCode());
        if (null == gsService) {
            logger.error("ServiceCode[" + requestMsgHeader.getServiceCode() + "] is not support yet.");
            responseBody.put("error", "ServiceCode[" + requestMsgHeader.getServiceCode() + "] is not support yet.");
            simpleResponseMsg.setResponseMsgBody(responseBody.toJSONString());
            return simpleResponseMsg;
        }

        logger.info("busi porcess entry => " + requestMsgHeader.getServiceCode());
        final JSONObject busiRes = gsService.doBusi(requestMsg);
        logger.info("busi Res => " + busiRes);

        simpleResponseMsg.setResponseMsgBody(busiRes.toJSONString());

        GsHisRequest gsHisRequest = responseMsgRepository.generateGsHisRequest(requestMsgHeader,requestMsg,simpleResponseMsg);

        gsRequestRepository.addGsRequest(gsHisRequest);

        logger.info("history loaded: gsHisRequest => " + gsHisRequest);
        return simpleResponseMsg;
    }
}
