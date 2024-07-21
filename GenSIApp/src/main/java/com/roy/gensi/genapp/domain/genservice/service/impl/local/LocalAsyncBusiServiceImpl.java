package com.roy.gensi.genapp.domain.genservice.service.impl.local;

import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.GsService;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.entity.SimpleResponseMsg;
import com.roy.gensi.genapp.domain.genservice.repository.BusiServiceRepository;
import com.roy.gensi.genapp.domain.genservice.repository.ResponseMsgRepository;
import com.roy.gensi.genapp.domain.genservice.service.AsyncBusiService;
import com.roy.gensi.genapp.domain.hisrequest.adaptor.HisRequestInterface;
import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;
import com.roy.gensi.genapp.domain.hisrequest.service.GsRequestDomainService;
import com.roy.gensi.genapp.domain.sysManage.adaptor.SysManagerInterface;
import com.roy.gensi.genapp.domain.genservice.infrastructure.ThreadPoolHolder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author loulan
 */
@Service("LocalAsyncBusiService")
public class LocalAsyncBusiServiceImpl implements AsyncBusiService {

    @Autowired
    private BusiServiceRepository busiServiceRepository;

    @Autowired
    private ResponseMsgRepository responseMsgRepository;

    @Autowired
    private GsRequestDomainService gsRequestDomainService;

    @Resource
    private HisRequestInterface hisRequestInterface;

    @Resource
    private SysManagerInterface sysManagerInterface;

    @Override
    public void dealMessageAsync(SimpleRequestMsg requestMsg,Logger logger) {
        SimpleRequestMsgHeader reqHeader = requestMsg.getRequestMsgHeader();
        logger.info("GsRestInterface: 请求验证通过 request=> " + requestMsg);
        //报文检查通过后，同步返回请求接收成功的响应，异步推送业务处理结果
        final List<GsHisRequest> gsRequests = gsRequestDomainService.queryGsRequest(reqHeader.getTransId(), reqHeader.getServiceCode());
        String hisResponse;
        if (null != gsRequests && !gsRequests.isEmpty()) {
            //重复的请求，直接推送之前的响应结果。
            hisResponse = gsRequests.get(0).getRspbody();
            logger.info("GsRestInterface: 查询到历史请求记录，响应结果：hisResponse => " + hisResponse);
            sysManagerInterface.sendHttpResponse(reqHeader.getSysId(), hisResponse);
        } else {
            //处理实际业务
            this.doBusi(requestMsg,logger);
        }
    }

    public void doBusi(SimpleRequestMsg requestMsg, Logger logger){
        final SimpleRequestMsgHeader requestMsgHeader = requestMsg.getRequestMsgHeader();

        GsService gsService = busiServiceRepository.getGsService(requestMsgHeader.getServiceCode());
        if(null == gsService){
            logger.error("ServiceCode["+requestMsgHeader.getServiceCode()+"] is not support yet.");
            return;
        }

        ThreadPoolHolder.callBusiExecutor.execute(()->{
            logger.info("busi porcess entry => "+requestMsgHeader.getServiceCode());
            final JSONObject busiRes = gsService.doBusi(requestMsg);
            logger.info("busi Res => "+busiRes);

            SimpleResponseMsg simpleResponseMsg = new SimpleResponseMsg();
            simpleResponseMsg.getHeaderFromRequest(requestMsg);
            simpleResponseMsg.setResponseMsgBody(busiRes.toJSONString());

            GsHisRequest gsHisRequest = responseMsgRepository.generateGsHisRequest(requestMsgHeader,requestMsg,simpleResponseMsg);

            if(hisRequestInterface.addHisRequest(gsHisRequest)){
                logger.info("history loaded: gsHisRequest => "+gsHisRequest);
            }
            if(sysManagerInterface.sendHttpResponse(requestMsgHeader.getSysId(),JSONObject.toJSONString(simpleResponseMsg))){
                logger.info("callback info sended to sysId => "+requestMsgHeader.getSysId());
            }
        });
    }


}
