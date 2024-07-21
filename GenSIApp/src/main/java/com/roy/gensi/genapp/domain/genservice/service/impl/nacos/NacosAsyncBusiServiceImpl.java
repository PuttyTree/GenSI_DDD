package com.roy.gensi.genapp.domain.genservice.service.impl.nacos;

import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.service.AsyncBusiService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author loulan
 */
@Service("NacosAsyncBusiService")
public class NacosAsyncBusiServiceImpl implements AsyncBusiService {
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
    public void dealMessageAsync(SimpleRequestMsg requestMsg, Logger logger) {
//        SimpleRequestMsgHeader reqHeader = requestMsg.getRequestMsgHeader();
//        logger.info("GsRestInterface: 请求验证通过 request=> " + requestMsg);
//        //报文检查通过后，同步返回请求接收成功的响应，异步推送业务处理结果
//        final List<GsHisRequest> hisRequests = hisRequestInterface.queryHisRequest(reqHeader.getTransId(), reqHeader.getServiceCode());
//        String hisResponse;
//        if (null != hisRequests && !hisRequests.isEmpty()) {
//            //重复的请求，直接推送之前的响应结果。
//            hisResponse = hisRequests.get(0).getRspbody();
//            logger.info("GsRestInterface: 查询到历史请求记录，响应结果：hisResponse => " + hisResponse);
//            sysManagerInterface.sendHttpResponse(reqHeader.getSysId(), hisResponse);
//        } else {
//            //处理实际业务
//            this.doBusi(requestMsg,logger);
//        }
    }
//    //FIXME 简化实现，就懒得再做抽象了。 有兴趣自己优化。
//    private void doBusi(SimpleRequestMsg requestMsg, Logger logger) {
//        final SimpleRequestMsgHeader requestMsgHeader = requestMsg.getRequestMsgHeader();
//        final JSONObject requestMsgBody = requestMsg.getRequestMsgBody();
//        String serviceCode = requestMsgHeader.getServiceCode();
//        //1、去Nacos上检查服务是否存在。
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceCode);
//        //1-1、如果服务不存在，直接返回不支持的serviceCode
//        if(null == instances || instances.isEmpty()){
//            logger.error("ServiceCode["+serviceCode+"] is not support yet.");
//            return;
//        }
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
//        //异步推送结果给客户端。
//        ThreadPoolHolder.callBusiExecutor.execute(()->{
//            logger.info("busi porcess entry => "+requestMsgHeader.getServiceCode());
//            String busiRes;
//            try{
//                busiRes = CommonHttpUtil.sendHttpBodyRequest(requestUrl.toString(), JSONObject.toJSONString(requestMsgBody));
//                logger.info("busi Res => "+busiRes);
//
//                SimpleResponseMsg simpleResponseMsg = new SimpleResponseMsg();
//                simpleResponseMsg.getHeaderFromRequest(requestMsg);
//                simpleResponseMsg.setResponseMsgBody(busiRes);
//
//                GsHisRequest gsHisRequest = responseMsgRepository.generateGsHisRequest(requestMsgHeader,requestMsg,simpleResponseMsg);
//                //跨领域的业务只访问interface，不干预实现细节。
//                if(hisRequestInterface.addHisRequest(gsHisRequest)){
//                    logger.info("history loaded: gsHisRequest => "+gsHisRequest);
//                }
//
//                if(sysManagerInterface.sendHttpResponse(requestMsgHeader.getSysId(), JSONObject.toJSONString(simpleResponseMsg))){
//                    logger.info("callback info sended to sysId => "+requestMsgHeader.getSysId());
//                }
//            }catch (Exception e){
//                logger.error("server side error. requestMsg : "+requestMsg+";e : "+e.getMessage());
//            }
//        });
//    }
}
