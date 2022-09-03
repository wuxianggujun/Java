package com.wuxianggujun.muxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication()
//扫描mybatis mapper包路径
@MapperScan(basePackages = {"com.wuxianggujun.muxin.mapper"})
@ComponentScan(basePackages = {"com.wuxianggujun.muxin","org.n3r.idworker"})
public class WuxiangMuxinNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuxiangMuxinNettyApplication.class, args);
    }

}
