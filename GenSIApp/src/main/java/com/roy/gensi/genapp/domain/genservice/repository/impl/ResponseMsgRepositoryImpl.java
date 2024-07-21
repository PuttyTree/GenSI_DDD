package com.roy.gensi.genapp.domain.genservice.repository.impl;

import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.entity.SimpleResponseMsg;
import com.roy.gensi.genapp.domain.genservice.repository.ResponseMsgRepository;
import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;
import org.springframework.stereotype.Component;

/**
 * @author ：楼兰
 * @description:
 **/

@Component
public class ResponseMsgRepositoryImpl implements ResponseMsgRepository {
    @Override
    public GsHisRequest generateGsHisRequest(SimpleRequestMsgHeader requestMsgHeader, SimpleRequestMsg requestMsg, SimpleResponseMsg simpleResponseMsg) {
        GsHisRequest gsHisRequest = new GsHisRequest();
        gsHisRequest.setTransid(requestMsgHeader.getTransId());
        gsHisRequest.setSysid(requestMsgHeader.getSysId());
        gsHisRequest.setServicecode(requestMsgHeader.getServiceCode());
        gsHisRequest.setReqbody(JSONObject.toJSONString(requestMsg));
        simpleResponseMsg.initRspTime();
        gsHisRequest.setRspbody(JSONObject.toJSONString(simpleResponseMsg));
        gsHisRequest.setIntime(requestMsgHeader.getInTime());
        gsHisRequest.setRsptime(requestMsgHeader.getRespTime());
        return gsHisRequest;
    }
}
