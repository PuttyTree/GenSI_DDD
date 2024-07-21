package com.roy.gensi.genapp.domain.genservice.service;

import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.entity.CommonGsResponse;
import org.springframework.stereotype.Service;

/**
 * @author loulan
 * @desc
 */
@Service
public interface MessageCheckService {

    CommonGsResponse checkSimpleMsg(SimpleRequestMsg requestMsg);
}
