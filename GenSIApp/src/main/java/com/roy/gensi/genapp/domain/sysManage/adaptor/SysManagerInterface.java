package com.roy.gensi.genapp.domain.sysManage.adaptor;

/**
 * @author loulan
 * @desc
 */
public interface SysManagerInterface {
    boolean isContainSys(String sysId);

    String getPrivateKey(String sysId);

    boolean sendHttpResponse(String sysId, String message);
}
