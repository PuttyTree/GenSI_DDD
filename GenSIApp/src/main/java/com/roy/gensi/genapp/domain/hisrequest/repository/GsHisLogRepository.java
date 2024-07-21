package com.roy.gensi.genapp.domain.hisrequest.repository;

import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisLog;

import java.util.List;

/**
 * @author loulan
 * @desc
 */
public interface GsHisLogRepository {

    List<GsHisLog> listLogFiles(String transId);

    GsHisLog readLogFileContesnt(String filePath,String fileName);

    String getLogPath();
}
