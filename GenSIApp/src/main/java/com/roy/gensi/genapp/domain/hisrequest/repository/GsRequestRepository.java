package com.roy.gensi.genapp.domain.hisrequest.repository;

import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;

import java.util.List;

/**
 * @author ：楼兰
 * @description:
 **/
public interface GsRequestRepository {

    List<GsHisRequest> queryGsRequest(String transId, String serviceCode);

    int addGsRequest(GsHisRequest gsRequest);
}
