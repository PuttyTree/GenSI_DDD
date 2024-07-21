package com.roy.gensi.genapp.domain.genservice.service;

import com.roy.gensi.genapp.domain.genservice.adaptor.SimpleRequestMsg;
import com.roy.gensi.genapp.domain.genservice.entity.DecryptMessageException;
import org.springframework.stereotype.Service;

/**
 * @author ：楼兰
 * @date ：Created in 2021/5/6
 * @description: 服务工具类
 **/

@Service
public interface DecryptService {
    //这个地方传的参数最好要封装成一个对象，Domian Primative。 这里偷个懒
    SimpleRequestMsg decrypData(String ftRequestInfo) throws DecryptMessageException;
}
