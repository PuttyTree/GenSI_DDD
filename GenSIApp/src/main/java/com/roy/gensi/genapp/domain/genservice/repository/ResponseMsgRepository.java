package com.roy.gensi.genapp.domain.genservice.repository;

import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.entity.SimpleResponseMsg;
import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;

/**
 * @author ：楼兰
 * @description:
 **/

public interface ResponseMsgRepository {
    GsHisRequest generateGsHisRequest(SimpleRequestMsgHeader requestMsgHeader, SimpleRequestMsg requestMsg, SimpleResponseMsg simpleResponseMsg);
}
