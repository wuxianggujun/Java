package com.wuxianggujun.aop.advice;

import java.lang.reflect.Method;

/**
 * 前置通知接口
 */
public interface MethodBeforeAdvice extends Advice{
    /**
     * 前置方法
     */
    void before(Class<?> clazz, Method method,Object[] args) throws Throwable;
}
