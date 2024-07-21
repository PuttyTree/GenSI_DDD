package com.roy.gensi.genapp.domain.hisrequest.adaptor.impl;

import com.roy.gensi.genapp.domain.hisrequest.adaptor.HisRequestInterface;
import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;
import com.roy.gensi.genapp.domain.hisrequest.infrastructure.GsLogConfig;
import com.roy.gensi.genapp.domain.hisrequest.repository.GsRequestRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author loulan
 * @desc
 */
@Service
public class HisRequestInterfaceImpl implements HisRequestInterface {

    //private final Logger logger = Logger.getLogger(HisRequestInterfaceimpl.class);

    @Resource
    private GsRequestRepository gsRequestRepository;

    @Resource
    private GsLogConfig gsLogConfig;

    @Override
    public boolean addHisRequest(GsHisRequest gsHisRequest) {
        return gsRequestRepository.addGsRequest(gsHisRequest) > 0;
    }

    @Override
    public void addTransLogAppender(Logger logger, String transId) {
        gsLogConfig.addTransLogAppender(logger, transId);
    }

    @Override
    public void removeTransLogAppender(Logger logger) {
        gsLogConfig.removeTransLogAppender(logger);
    }

    @Override
    public List<GsHisRequest> queryHisRequest(String transId, String serviceCode) {
        return gsRequestRepository.queryGsRequest(transId, serviceCode);
    }
}
