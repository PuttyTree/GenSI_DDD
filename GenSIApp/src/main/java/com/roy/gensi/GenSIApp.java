package com.roy.gensi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * @author ：楼兰
 * @description:
 **/

@SpringBootApplication
@MapperScan("com.roy.gensi.genapp.domain.*.infrastructure")
public class GenSIApp {
    public static void main(String[] args) {
        SpringApplication.run(GenSIApp.class,args);
    }
}
