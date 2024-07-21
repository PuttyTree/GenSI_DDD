package com.roy.gensi.genapp.domain.genservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.entity.CommonGsResponse;
import com.roy.gensi.genapp.domain.genservice.service.MessageCheckService;
import com.roy.gensi.genapp.domain.sysManage.adaptor.SysManagerInterface;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author loulan
 * @desc
 */
@Service
public class MessageCheckServiceImpl implements MessageCheckService {
    @Resource
    private SysManagerInterface sysManagerInterface;

    @Override
    public CommonGsResponse checkSimpleMsg(SimpleRequestMsg requestMsg) {
        CommonGsResponse response = null;
        final SimpleRequestMsgHeader requestMsgHeader = requestMsg.getRequestMsgHeader();
        final JSONObject requestMsgBody = requestMsg.getRequestMsgBody();
        if (null == requestMsgHeader || null == requestMsgBody) {
            response = new CommonGsResponse("", "", "", CommonGsResponse.RESULT_CODE_FORMAT_ERROR, "接口数据错误");
        } else if (StringUtils.isEmpty(requestMsgHeader.getTransId()) ||
                StringUtils.isEmpty(requestMsgHeader.getServiceCode()) ||
                StringUtils.isEmpty(requestMsgHeader.getSysId())) {
            response = new CommonGsResponse("", "", "", CommonGsResponse.RESULT_CODE_PARAM_ERROR, "参数处理错误");
        } else if (!sysManagerInterface.isContainSys(requestMsgHeader.getSysId())) {
            response = new CommonGsResponse("", "", "", CommonGsResponse.RESULT_CODE_SYSID_ERROR, "无效的系统标识");
        } else {
            response = new CommonGsResponse(requestMsgHeader.getServiceCode(), requestMsgHeader.getTransId(),
                    requestMsgHeader.getSysId(), CommonGsResponse.RESULT_CODE_SUCCESS, "请求接收成功");
        }
        return response;
    }
}
