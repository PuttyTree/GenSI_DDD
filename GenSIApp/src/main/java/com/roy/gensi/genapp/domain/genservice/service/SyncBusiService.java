package com.roy.gensi.genapp.domain.genservice.service;

import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.entity.SimpleResponseMsg;
import org.apache.log4j.Logger;

/**
 * @author ：楼兰
 * @description: 同步业务防腐层
 **/
public interface SyncBusiService {
    SimpleResponseMsg dealMessageSync(SimpleRequestMsg requestMsg,Logger logger);
}
