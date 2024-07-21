package com.roy.gensi.genapp.domain.sysManage.repository;

import com.roy.gensi.genapp.domain.sysManage.entity.SysManage;
import com.roy.gensi.genapp.domain.sysManage.infrastructure.SysManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：楼兰
 * @description:
 **/

public interface SysManageRepository {

    int saveSys(SysManage gsSys);

    int deleteSysById(String sysid);

    List<SysManage> queryAllSys();
}
