package com.wuxianggujun.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * ControllerInfo �洢Controller�����Ϣ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerInfo {

    /**
     * controller ��
     */
    private Class<?> controllerClass;

    /**
     * ִ�еķ���
     */
    private Method invokeMethod;

    /**
     * ������������Ӧ��������
     */
    private Map<String,Class<?>> methodParameter;
}
