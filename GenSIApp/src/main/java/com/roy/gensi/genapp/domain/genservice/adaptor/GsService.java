package com.roy.gensi.genapp.domain.genservice.adaptor;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ：楼兰
 * @date ：Created in 2021/5/6
 * @description:
 **/
public interface GsService {

    JSONObject doBusi(SimpleRequestMsg requestMsg);

    String serviceCode();


}
