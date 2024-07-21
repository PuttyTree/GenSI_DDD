package com.roy.gensi.genapp.domain.sysManage.adaptor.impl;

import com.roy.gensi.genapp.domain.sysManage.adaptor.SysManagerInterface;
import com.roy.gensi.genapp.domain.sysManage.entity.SysManage;
import com.roy.gensi.genapp.domain.sysManage.service.SysManageDomainService;
import com.roy.gensi.genapp.utils.CommonHttpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author loulan
 * @desc
 */
@Service
public class SysManagerInterfaceImpl implements SysManagerInterface {

    @Resource
    private SysManageDomainService sysManageDomainService;

    @Override
    public boolean isContainSys(String sysId) {
        return sysManageDomainService.isContainSys(sysId);
    }

    @Override
    public String getPrivateKey(String sysId) {
        SysManage sysManage = sysManageDomainService.getSysManage(sysId);
        if(null == sysId){
            return "";
        }
        return sysManage.getPrivatekey();
    }

    @Override
    public boolean sendHttpResponse(String sysId, String message) {
        SysManage sysManage = sysManageDomainService.getSysManage(sysId);
        if(null == sysManage){
            return false;
        }
        CommonHttpUtil.sendHttpBodyRequest(sysManage.getNotifyurl(), message);
        return true;
    }
}
