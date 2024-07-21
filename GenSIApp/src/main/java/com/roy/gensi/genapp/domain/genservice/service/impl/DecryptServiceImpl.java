package com.roy.gensi.genapp.domain.genservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.entity.DecryptMessageException;
import com.roy.gensi.genapp.domain.genservice.entity.EncryptedRequestMsg;
import com.roy.gensi.genapp.domain.genservice.entity.EncryptedRequestMsgHeader;
import com.roy.gensi.genapp.domain.genservice.service.DecryptService;
import com.roy.gensi.genapp.domain.sysManage.entity.SysManage;
import com.roy.gensi.genapp.domain.sysManage.service.SysManageDomainService;
import com.roy.gensi.genapp.utils.AESUtil;
import com.roy.gensi.genapp.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author loulan
 * @desc
 */
@Service
public class DecryptServiceImpl implements DecryptService {

    @Autowired
    SysManageDomainService sysManageDomainService;

    @Override
    //这个地方传的参数最好要封装成一个对象，Domian Primative。 这里偷个懒
    public SimpleRequestMsg decrypData(String ftRequestInfo) throws DecryptMessageException {
        final EncryptedRequestMsg encryptedRequestMsg = JSONObject.parseObject(ftRequestInfo, EncryptedRequestMsg.class);
        if(null == encryptedRequestMsg){
            throw new DecryptMessageException("消息格式异常；请配合客户端程序使用");
        }
        final EncryptedRequestMsgHeader reqHeader = encryptedRequestMsg.getHeader();
        final String encryptedBody = encryptedRequestMsg.getBody();
        if(null == reqHeader || null == encryptedBody){
            throw new DecryptMessageException("请求参数错误；请配合客户端程序使用");
        }
        final SysManage sysManage = sysManageDomainService.getSysManage(reqHeader.getSysId());
        if (null == sysManage) {
            throw new DecryptMessageException("无效的sysId；请检查客户端参数");
        }
        String aesKey;
        String requestBody = null;
        try{
            aesKey = RsaUtils.RSADecodeByPrivateKey(sysManage.getPrivatekey(), reqHeader.getKey());
            requestBody = AESUtil.decryptByAES(encryptedBody, aesKey).replace("\"","").replace("\\","\"");
        }catch (Exception e){
            throw new DecryptMessageException("消息解密错误；请检查公钥是否正确");
        }

        SimpleRequestMsg requestMsg = new SimpleRequestMsg();
        //key字段解密后就丢弃掉
        SimpleRequestMsgHeader requestMsgHeader = new SimpleRequestMsgHeader();
        requestMsgHeader.setSysId(reqHeader.getSysId());
        requestMsgHeader.setServiceCode(reqHeader.getServiceCode());
        requestMsgHeader.setTransId(reqHeader.getTransId());
        requestMsgHeader.setSysUser(reqHeader.getSysUser());
        requestMsgHeader.setSysPwd(reqHeader.getSysPwd());
        requestMsgHeader.setInTime(reqHeader.getInTime());

        requestMsg.setRequestMsgHeader(requestMsgHeader);
        requestMsg.setRequestMsgBody(JSON.parseObject(requestBody));

        return requestMsg;
    }


}
