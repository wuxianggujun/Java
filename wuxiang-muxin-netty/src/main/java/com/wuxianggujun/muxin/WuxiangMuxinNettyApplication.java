package com.wuxianggujun.muxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wuxianggujun.muxin"})
public class WuxiangMuxinNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuxiangMuxinNettyApplication.class, args);
    }

}
