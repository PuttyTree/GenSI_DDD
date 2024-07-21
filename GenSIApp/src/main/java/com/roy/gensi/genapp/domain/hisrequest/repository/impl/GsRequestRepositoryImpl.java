package com.roy.gensi.genapp.domain.hisrequest.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;
import com.roy.gensi.genapp.domain.hisrequest.infrastructure.GsRequestMapper;
import com.roy.gensi.genapp.domain.hisrequest.repository.GsRequestRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author loulan
 * @desc
 */
@Service
public class GsRequestRepositoryImpl implements GsRequestRepository {
    @Autowired
    private GsRequestMapper gsRequestMapper;

    @Override
    public List<GsHisRequest> queryGsRequest(String transId, String serviceCode){
        QueryWrapper<GsHisRequest> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(transId)){
            queryWrapper.eq("transId",transId);
        }
        if(StringUtils.isNotEmpty(serviceCode)){
            queryWrapper.eq("serviceCode",serviceCode);
        }
        return gsRequestMapper.selectList(queryWrapper);
    }

    @Override
    public int addGsRequest(GsHisRequest gsRequest) {
        return gsRequestMapper.insert(gsRequest);
    }
}
