package com.roy.gensi.genapp.domain.hisrequest.adaptor;

import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisRequest;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author loulan
 * @desc
 */
public interface HisRequestInterface {
    boolean addHisRequest(GsHisRequest gsHisRequest);

    void addTransLogAppender(Logger logger,String transId);

    void removeTransLogAppender(Logger logger);

    List<GsHisRequest> queryHisRequest(String transId, String serviceCode);
}
