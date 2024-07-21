package com.roy.gensi.genapp.domain.genservice.repository;

import com.roy.gensi.genapp.domain.genservice.adaptor.GsService;
import org.springframework.stereotype.Component;

/**
 * @author ：楼兰
 * @description:
 **/

@Component
public interface BusiServiceRepository {

    void loadBusiServices();

    public GsService getGsService(String serviceCode);
}