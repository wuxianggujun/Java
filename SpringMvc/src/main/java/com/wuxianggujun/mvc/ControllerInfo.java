package com.wuxianggujun.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * ControllerInfo 存储Controller相关信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerInfo {

    /**
     * controller 类
     */
    private Class<?> controllerClass;

    /**
     * 执行的方法
     */
    private Method invokeMethod;

    /**
     * 方法参数名对应参数类型
     */
    private Map<String,Class<?>> methodParameter;
}
