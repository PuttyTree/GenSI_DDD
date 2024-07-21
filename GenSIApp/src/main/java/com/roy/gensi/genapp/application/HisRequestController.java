package com.roy.gensi.genapp.application;

import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisLog;
import com.roy.gensi.genapp.domain.hisrequest.service.GsRequestDomainService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author loulan
 * @desc
 */
@RestController
@RequestMapping("/hisrequest")
public class HisRequestController {
    private Logger logger = Logger.getLogger(HisRequestController.class);

    @Resource
    GsRequestDomainService gsRequestDomainService;

    @RequestMapping(value = "/getHistory", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public Object getHistory(String transId, String serviceCode) {
        logger.info("查询历史请求记录：transId => "+transId+";serviceCode => "+serviceCode);
        Map<String, Object> res = new HashMap<>();
        res.put("code", "1");
        res.put("desc", "获取数据成功！");
        res.put("data",gsRequestDomainService.queryGsRequest(transId,serviceCode));
        return res;
    }

    /**
     * 获取指定目录下transId文件列表
     */
    @RequestMapping(value = "/getTransIdLogFiles", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public Object getTransIdLogFiles(HttpServletRequest request) {
        Map<String, Object> res = new HashMap<>();
        String transId = request.getParameter("transId");
        List<GsHisLog> dataList = gsRequestDomainService.listAllFiles(transId);
        String transIdLogFilesRootPath = gsRequestDomainService.getLogPath();
        if(ObjectUtils.isNotEmpty(dataList)){
            res.put("code", "1");
            res.put("desc", "获取数据成功！");
            res.put("data", dataList);
            res.put("logPath", transIdLogFilesRootPath);
        }else{
            res.put("code", "0");
            res.put("desc", "获取数据失败！");
        }
        return res;
    }

    /**
     * 获取指定transId文件的内容
     */
    @RequestMapping(value = "/getTransIdLogFileContent", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
    public Object getTransIdLogFileContent(String transIdLogFilePath, String transIdLogFileName) {
        Map<String, Object> res = new HashMap<>();
        try {
            //transId命名的日志文件存放的根目录
            GsHisLog gsHisLog = gsRequestDomainService.readLogFileContent(transIdLogFilePath,transIdLogFileName);
            res.put("code", "1");
            res.put("desc", "获取数据成功！");
            res.put("data", gsHisLog);
        } catch (Exception ex) {
            res.put("code", "0");
            res.put("desc", "获取数据失败！");
            logger.error(ex.getMessage(), ex);
        }
        return res;
    }
}
