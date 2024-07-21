package com.roy.gensi.genapp.domain.hisrequest.repository.impl;

import com.roy.gensi.genapp.domain.hisrequest.entity.GsHisLog;
import com.roy.gensi.genapp.domain.hisrequest.infrastructure.GsLogConfig;
import com.roy.gensi.genapp.domain.hisrequest.repository.GsHisLogRepository;
import com.roy.gensi.genapp.utils.GsConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author loulan
 * @desc
 */
@Component
public class GsHisLogRepositoryImpl implements GsHisLogRepository {

    @Resource
    GsLogConfig gsLogConfig;

    @Override
    public List<GsHisLog> listLogFiles(String transId) {
        List<GsHisLog> dataList = new ArrayList<>();
        List<File> files = (List<File>) FileUtils.listFiles(new File(gsLogConfig.getTransLogDir()), new String[]{GsConstants.LOG_SUFFIX}, false);
        if (files != null && !files.isEmpty()) {
            //按文件最后修改时间倒序排序
            Collections.sort(files, (file1, file2) -> (int) (file2.lastModified() - file1.lastModified()));
            //如果transId不为空，只展现该transId的文件。 否则，返回最近的100个日志文件
            Predicate<File> logFileFilter = (f)-> f.getName().contains(transId.trim());
            if(StringUtils.isNotEmpty(transId)) {
                files = files.stream().filter(logFileFilter).collect(Collectors.toList());
            }else if(files.size()>GsConstants.LOG_FILE_MAX_SIZE) {
                files = files.subList(0, GsConstants.LOG_FILE_MAX_SIZE);
            }
            for (File file : files) {
                dataList.add(new GsHisLog(file.getAbsolutePath(),file.getName()));
            }
        }
        return dataList;
    }

    @Override
    public GsHisLog readLogFileContesnt(String filepath,String fileName) {
        try {
            String content = FileUtils.readFileToString(new File(filepath), "utf-8");
            return new GsHisLog(filepath,fileName,content);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String getLogPath() {
        return gsLogConfig.getTransLogDir();
    }
}
