package com.roy.gensi.genapp.application;

import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.entity.CommonGsResponse;
import com.roy.gensi.genapp.domain.genservice.entity.DecryptMessageException;
import com.roy.gensi.genapp.domain.genservice.entity.SimpleResponseMsg;
import com.roy.gensi.genapp.domain.genservice.service.AsyncBusiService;
import com.roy.gensi.genapp.domain.genservice.service.DecryptService;
import com.roy.gensi.genapp.domain.genservice.service.MessageCheckService;
import com.roy.gensi.genapp.domain.genservice.service.SyncBusiService;
import com.roy.gensi.genapp.domain.hisrequest.adaptor.HisRequestInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ：楼兰
 **/
@RestController
@RequestMapping(value = "/genSI")
@Api("GenSI服务请求接口")
public class GsRequestController {

    private final Logger logger = Logger.getLogger(getClass());
    @Resource
    private  HisRequestInterface hisRequestInterface;
    @Resource
    private DecryptService decryptService;
    //Local：基于本地SPI找服务， Nacos：基于openfeign读取Nacos上注册的微服务
    @Resource(name = "LocalAsyncBusiService")
//    @Resource(name = "NacosAsyncBusiService")
    private AsyncBusiService asyncBusiService;
    @Resource(name = "LocalSyncBusiService")
//    @Resource(name = "NacosSyncBusiService")未实现
    private SyncBusiService syncBusiService;
    @Resource
    private MessageCheckService messageCheckService;

    @ApiOperation(value = "GenSI Restful接口", notes = "内部调试接口")
    @RequestMapping(value = "/gsServiceTest", method = RequestMethod.POST)
    public Object gsRestInterface(@RequestBody String requestMessage) {
        logger.info("GsRestInterface: request=> " + requestMessage);
        JSONObject jRequestMessage = JSONObject.parseObject(requestMessage);
        SimpleRequestMsgHeader reqHeader = jRequestMessage.getObject("header", SimpleRequestMsgHeader.class);
        reqHeader.initIntime();
        JSONObject jRequestBody = jRequestMessage.getJSONObject("body");

        SimpleRequestMsg requestMsg = new SimpleRequestMsg(reqHeader, jRequestBody);

        //检查报文 报文检查不通过就直接返回响应。
        CommonGsResponse response = messageCheckService.checkSimpleMsg(requestMsg);
        if (!response.getResult().equals(CommonGsResponse.RESULT_CODE_SUCCESS)) {
            return response;
        }
        hisRequestInterface.addTransLogAppender(logger, reqHeader.getTransId());
        //处理业务
        asyncBusiService.dealMessageAsync(requestMsg,logger);
        logger.info("GsRestInterface: 返回请求响应  => " + response.toJsonFormat());
        hisRequestInterface.removeTransLogAppender(logger);
        return response;
    }

    @ApiOperation(value = "GenSI异步业务接口", notes = "配合客户端使用的正式接口。")
    @RequestMapping(value = "/gsServiceAsync", method = RequestMethod.POST)
    public Object gsServiceAsync(String ftRequestInfo, HttpServletRequest request) {
        logger.info("gsServiceAsync: received Request => " + ftRequestInfo);
        // 报文解密
        SimpleRequestMsg requestMsg;
        try {
            requestMsg = decryptService.decrypData(ftRequestInfo);
        } catch (DecryptMessageException e) {
            return new CommonGsResponse("","","", CommonGsResponse.RESULT_CODE_PARAM_ERROR,e.getMessage());
        }
        requestMsg.getRequestMsgHeader().initIntime();

        CommonGsResponse response = messageCheckService.checkSimpleMsg(requestMsg);
        if (!response.getResult().equals(CommonGsResponse.RESULT_CODE_SUCCESS)) {
            return response;
        }
        hisRequestInterface.addTransLogAppender(logger, requestMsg.getRequestMsgHeader().getTransId());
        //处理业务
        asyncBusiService.dealMessageAsync(requestMsg,logger);
        logger.info("gsServiceAsync: 返回请求响应  => " + response.toJsonFormat());
        hisRequestInterface.removeTransLogAppender(logger);
        return response;
    }

    @ApiOperation(value = "GenSI同步业务接口", notes = "配合客户端使用的正式接口。")
    @RequestMapping(value = "/gsServiceSync", method = RequestMethod.POST)
    public Object gsServiceSync(String ftRequestInfo) {
        logger.info("gsServiceSync: received Request => " + ftRequestInfo);
        // 报文解密
        SimpleRequestMsg requestMsg;
        try {
            requestMsg = decryptService.decrypData(ftRequestInfo);
        } catch (DecryptMessageException e) {
            return new CommonGsResponse("","","", CommonGsResponse.RESULT_CODE_PARAM_ERROR,e.getMessage());
        }
        requestMsg.getRequestMsgHeader().initIntime();

        CommonGsResponse response = messageCheckService.checkSimpleMsg(requestMsg);
        if (!response.getResult().equals(CommonGsResponse.RESULT_CODE_SUCCESS)) {
            return response;
        }
        hisRequestInterface.addTransLogAppender(logger, requestMsg.getRequestMsgHeader().getTransId());
        //处理业务
        final SimpleResponseMsg simpleResponseMsg = syncBusiService.dealMessageSync(requestMsg,logger);
        logger.info("gsServiceSync: 返回请求响应  => " + simpleResponseMsg);
        return simpleResponseMsg;
    }
}
