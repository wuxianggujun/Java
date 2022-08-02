package com.wuxianggujun.aop.advice;

import java.lang.reflect.Method;

/**
 * �쳣֪ͨ����
 */
public interface ThrowsAdvice extends Advice{
    /**
     * �쳣����
     * @param clazz
     * @param method
     * @param args
     * @param e
     */
    void afterThrowing(Class<?> clazz, Method method,Object[] args,Throwable e);
}
