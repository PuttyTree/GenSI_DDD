package com.roy.gensi.mobiletag.application;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.roy.gensi.mobiletag.util.CodeUtil;
import com.roy.gensi.mobiletag.util.HTTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author loulan
 * @desc 响应开放平台的服务请求。
 */
@RestController
public class MobileTagController {

    private final Logger logger = LoggerFactory.getLogger(MobileTagController.class);

    @RequestMapping(value = "/open/service",method = RequestMethod.POST)
    public Object getMobileTag(@RequestBody String requestJson){
        Map<String,Object> response = new HashMap<>();
        logger.info("received request:"+requestJson);
        try {
            JSONObject requestObj = JSONObject.parseObject(requestJson);
            String mobile = requestObj.getString("mobile");

            response.put("mobile",mobile);
            String tagSearchUrl = "https://www.sogou.com/reventondc/inner/vrapi?number=${mobile}&callback=show&isSogoDomain=0&objid=10001001";

            String httpRes = HTTPUtil.httpGet(tagSearchUrl.replace("${mobile}", mobile));
            String mobileTag = CodeUtil.decodeUnicode(httpRes.substring(httpRes.indexOf(":") + 1, httpRes.indexOf(",")).replace("\"", "").replace("\\\\", "\\"));
            logger.info("查询到手机号码信息："+mobileTag);
            response.put("mobileTag",mobileTag);
        } catch(JSONException e){
            logger.error("请求报文格式错误");
            response.put("error","请求报文格式错误");
        }catch (Exception e) {
            logger.error("查询出错，请检查服务或重新查询");
            response.put("error","查询出错，请检查服务或重新查询");
        }
        return response;
    }
}
