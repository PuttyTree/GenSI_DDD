package com.roy.gensi.genapp.domain.hisrequest.service;

import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisLog;
import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;
import com.roy.gensi.genapp.domain.hisrequest.repository.GsHisLogRepository;
import com.roy.gensi.genapp.domain.hisrequest.repository.GsRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：楼兰
 * @date ：Created in 2021/5/5
 * @description:
 **/

@Service
public class GsRequestDomainService {

    @Autowired
    private GsRequestRepository gsRequestRepository;

    @Resource
    private GsHisLogRepository gsHisLogRepository;

    public List<GsHisRequest> queryGsRequest(String transId, String serviceCode) {
        return gsRequestRepository.queryGsRequest(transId,serviceCode);
    }

    public List<GsHisLog> listAllFiles(String transId){
        return gsHisLogRepository.listLogFiles(transId);
    }

    public GsHisLog readLogFileContent(String filePath,String transId){
        return gsHisLogRepository.readLogFileContesnt(filePath,transId);
    }

    public String getLogPath() {
        return gsHisLogRepository.getLogPath();
    }
}
