package com.wuxianggujun.aop.advice;

import java.lang.reflect.Method;

/**
 * ����֪ͨ�ӿ�
 */
public interface AfterReturningAdvice extends Advice{
    /**
     * ���غ󷽷�
     */
    void afterReturning(Class<?> clazz, Object returnValue, Method method,Object[] args) throws Throwable;
}
