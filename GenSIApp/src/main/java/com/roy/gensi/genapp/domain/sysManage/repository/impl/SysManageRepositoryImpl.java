package com.roy.gensi.genapp.domain.sysManage.repository.impl;

import com.roy.gensi.genapp.domain.sysManage.entity.SysManage;
import com.roy.gensi.genapp.domain.sysManage.infrastructure.SysManageMapper;
import com.roy.gensi.genapp.domain.sysManage.repository.SysManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author loulan
 * @desc
 */
@Service
public class SysManageRepositoryImpl implements SysManageRepository {
    @Resource
    SysManageMapper sysManageMapper;

    public int saveSys(SysManage gsSys) {
        try{
            return sysManageMapper.insert(gsSys);
        }catch (Exception e){
            return sysManageMapper.updateById(gsSys);
        }
    }

    public int deleteSysById(String sysid) {
        return sysManageMapper.deleteById(sysid);
    }


    public List<SysManage> queryAllSys() {
        return sysManageMapper.selectList(null);
    }
}
