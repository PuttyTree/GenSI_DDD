package com.roy.gensi.genapp.domain.genservice.entity;

import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;

/**
 * @author ：楼兰
 * @description:
 **/

public class SimpleResponseMsg {
    private SimpleRequestMsgHeader requestMsgHeader;

    private String responseMsgBody;

    public SimpleRequestMsgHeader getRequestMsgHeader() {
        return requestMsgHeader;
    }

    public void setRequestMsgHeader(SimpleRequestMsgHeader requestMsgHeader) {
        this.requestMsgHeader = requestMsgHeader;
    }

    public String getResponseMsgBody() {
        return responseMsgBody;
    }

    public void setResponseMsgBody(String responseMsgBody) {
        this.responseMsgBody = responseMsgBody;
    }

    @Override
    public String toString() {
        return "SimpleResponseMsg{" +
                "requestMsgHeader=" + requestMsgHeader +
                ", responseMsgBody=" + responseMsgBody +
                '}';
    }

    public void getHeaderFromRequest(SimpleRequestMsg requestMsg){
        this.setRequestMsgHeader(requestMsg.getRequestMsgHeader());
        this.setResponseMsgBody(null);
    }

    public void initRspTime() {
        this.getRequestMsgHeader().initRespTime();
    }
}
