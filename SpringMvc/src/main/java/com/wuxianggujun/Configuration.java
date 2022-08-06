package com.wuxianggujun;

import lombok.Builder;
import lombok.Getter;

/**
 * 服务器相关配置
 */
@Builder
@Getter
public class Configuration {

    /**
     * 启动类
     */
    private Class<?> bootClass;

    /**
     * 资源目录
     */
    @Builder.Default
    private String resourcePath = "src/main/resources/";

    /**
     * 静态文件目录
     */
    @Builder.Default
    private String assetPath ="/static/";

    /**
     * 端口号
     */
    @Builder.Default
    private int serverPort =9090;

    /**
     * tomcat docBase目录
     */
    @Builder.Default
    private String docBase = "doc";

    /**
     * tomcat contextPath目录
     */
    @Builder.Default
    private String contextPath ="";
    
}
