package com.roy.gensi.genapp.domain.genservice.service.impl.nacos;

import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.entity.SimpleResponseMsg;
import com.roy.gensi.genapp.domain.genservice.service.SyncBusiService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author loulan
 * @desc 基于Nacos的同步服务调用。 没有去实现了。
 */
//@Service("NacosSyncBusiService")
public class NacosSyncBusiServiceImpl implements SyncBusiService {

//    @Resource
//    private HisRequestInterface hisRequestInterface;
//    @Resource
//    private SysManagerInterface sysManagerInterface;
//    @Resource
//    private DiscoveryClient discoveryClient;
//    @Resource
//    private ResponseMsgRepository responseMsgRepository;
//
//    private final String SERVICE_SUFFIX = "/open/service";

    @Override
    public SimpleResponseMsg dealMessageSync(SimpleRequestMsg requestMsg, Logger logger) {
        SimpleResponseMsg response = new SimpleResponseMsg();
//        SimpleRequestMsgHeader reqHeader = requestMsg.getRequestMsgHeader();
//        logger.info("GsRestInterface: 请求验证通过 request=> " + requestMsg);
//        //报文检查通过后，同步返回请求接收成功的响应，异步推送业务处理结果
//        final List<GsHisRequest> hisRequests = hisRequestInterface.queryHisRequest(reqHeader.getTransId(), reqHeader.getServiceCode());
//        String hisResponse = "";
//        if (null != hisRequests && !hisRequests.isEmpty()) {
//            //重复的请求，直接推送之前的响应结果。
//            hisResponse = hisRequests.get(0).getRspbody();
//            logger.info("GsRestInterface: 查询到历史请求记录，响应结果：hisResponse => " + hisResponse);
//            response = JSON.parseObject(hisResponse, SimpleResponseMsg.class);
//        } else {
//            //处理实际业务
//            response = this.doBusi(requestMsg,logger);
//        }
        return response;
    }

//    private SimpleResponseMsg doBusi(SimpleRequestMsg requestMsg, Logger logger) {
//        SimpleResponseMsg simpleResponseMsg = new SimpleResponseMsg();
//        simpleResponseMsg.getHeaderFromRequest(requestMsg);
//
//        final SimpleRequestMsgHeader requestMsgHeader = requestMsg.getRequestMsgHeader();
//        final JSONObject requestMsgBody = requestMsg.getRequestMsgBody();
//        String serviceCode = requestMsgHeader.getServiceCode();
//
//        final JSONObject responseBody = new JSONObject();
//
//        //1、去Nacos上检查服务是否存在。
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceCode);
//        //1-1、如果服务不存在，直接返回不支持的serviceCode
//        if(null == instances || instances.isEmpty()){
//            logger.error("ServiceCode["+serviceCode+"] is not support yet.");
//            responseBody.put("error", "ServiceCode[" + requestMsgHeader.getServiceCode() + "] is not support yet.");
//            simpleResponseMsg.setResponseMsgBody(responseBody.toJSONString());
//            return simpleResponseMsg;
//        }
//        logger.info("busi porcess entry => " + requestMsgHeader.getServiceCode());
//        StringBuffer requestUrl = new StringBuffer("http://");
//        ServiceInstance serviceInstance;
//        //随机找一个实例
//        if(instances.size()==1){
//            serviceInstance = instances.get(0);
//        }else{
//            Random random = new Random();
//            int index = random.nextInt(instances.size());
//            serviceInstance = instances.get(index);
//        }
//        //接口路径固定请求 SERVICE_SUFFIX
//        requestUrl.append(serviceInstance.getHost()).append(":")
//                .append(serviceInstance.getPort()).append(SERVICE_SUFFIX);
//
//        String busiRes;
//        try {
//            busiRes = CommonHttpUtil.sendHttpBodyRequest(requestUrl.toString(), JSONObject.toJSONString(requestMsgBody));
//            logger.info("busi Res => " + busiRes);
//
//            simpleResponseMsg.setResponseMsgBody(busiRes);
//            GsHisRequest gsHisRequest = responseMsgRepository.generateGsHisRequest(requestMsgHeader, requestMsg, simpleResponseMsg);
//            hisRequestInterface.addHisRequest(gsHisRequest);
//
//            logger.info("history loaded: gsHisRequest => " + gsHisRequest);
//            return simpleResponseMsg;
//        }catch (Exception e){
//            logger.error("server side error. requestMsg : "+requestMsg+";e : "+e.getMessage());
//            return null;
//        }
//    }
}
