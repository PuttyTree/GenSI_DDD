package com.roy.gensi.genapp.domain.genservice.service;

import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import org.apache.log4j.Logger;

/**
 * @author ：楼兰
 * @description: 将所有外部业务抽象，这就相当于是个防腐层。以后如果需要将单体架构改成微服务架构，只需要在这一层修改具体业务服务的调用方式。
 **/
public interface AsyncBusiService {
    void dealMessageAsync(SimpleRequestMsg requestMsg,Logger logger);
}
