package com.wuxianggujun.muxin;

import com.wuxianggujun.muxin.utils.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication()
//扫描mybatis mapper包路径
//@MapperScan(basePackages={"com.wuxianggujun.muxin.mapper"})
public class WuxiangMuxinNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuxiangMuxinNettyApplication.class, args);
    }

}
