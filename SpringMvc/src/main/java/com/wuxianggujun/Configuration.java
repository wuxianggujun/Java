package com.wuxianggujun;

import lombok.Builder;
import lombok.Getter;

/**
 * �������������
 */
@Builder
@Getter
public class Configuration {

    /**
     * ������
     */
    private Class<?> bootClass;

    /**
     * ��ԴĿ¼
     */
    @Builder.Default
    private String resourcePath = "src/main/resources/";

    /**
     * ��̬�ļ�Ŀ¼
     */
    @Builder.Default
    private String assetPath ="/static/";

    /**
     * �˿ں�
     */
    @Builder.Default
    private int serverPort =9090;

    /**
     * tomcat docBaseĿ¼
     */
    @Builder.Default
    private String docBase = "doc";

    /**
     * tomcat contextPathĿ¼
     */
    @Builder.Default
    private String contextPath ="";
    
}
