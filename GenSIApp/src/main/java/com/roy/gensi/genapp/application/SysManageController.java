package com.roy.gensi.genapp.application;

import com.roy.gensi.genapp.domain.sysManage.entity.SysManage;
import com.roy.gensi.genapp.domain.sysManage.service.SysManageDomainService;
import com.roy.gensi.genapp.utils.GsConstants;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：楼兰
 * @description: 用户接口层
 **/

@RestController
@RequestMapping("/sysManage")
public class SysManageController {

    private Logger logger = Logger.getLogger(SysManageController.class);
    @Resource
    SysManageDomainService sysManageDomainService;

    @RequestMapping(value = "/newSys", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public Object newSys(@RequestBody SysManage gsSys) throws Exception {
        logger.info("SysManageAction.newSys: gsmanage=> " + gsSys);
        gsSys.generateKey();
        int opRes = sysManageDomainService.saveSys(gsSys);
        Map<String, Object> res = new HashMap<String, Object>();
        if (opRes > 0) {
            res.put("code", 1);
            res.put("desc", "外围系统创建成功");
        } else {
            res.put("code", 0);
            res.put("desc", "外围系统创建失败。");
        }
        return res;
    }

    @RequestMapping(value = "/deleteSys", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public Object deleteSys(String sysId) throws Exception {
        logger.info("SysManageAction.deleteSys: sysid=> " + sysId);
        int opRes = sysManageDomainService.deleteSysById(sysId);
        return opRes;
    }

    @RequestMapping(value = "/querySys", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public Object querySys() throws Exception {
        logger.info("SysManageAction.querySys: ");
        List<SysManage> res = sysManageDomainService.queryAllSys();
        return res;
    }

    @RequestMapping(value = "/passKey", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public Object passKey(String passKey) throws Exception {
        Map<String, Object> res = new HashMap<String, Object>();
        if (passKey.equals(GsConstants.PASSKEY)) {
            res.put("result", "0");
        } else {
            res.put("result", "1");
        }
        return res;
    }


}
