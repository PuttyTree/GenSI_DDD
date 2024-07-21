package com.roy.gensi.genapp.domain.genservice.adaptor;

import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;

/**
 * @author ：楼兰
 * @description:
 **/
public interface GsService {

    JSONObject doBusi(SimpleRequestMsg requestMsg);

    String serviceCode();


}
