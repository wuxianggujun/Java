package com.wuxianggujun.aop.advice;

import java.lang.reflect.Method;

/**
 * 异常通知方法
 */
public interface ThrowsAdvice extends Advice{
    /**
     * 异常方法
     * @param clazz
     * @param method
     * @param args
     * @param e
     */
    void afterThrowing(Class<?> clazz, Method method,Object[] args,Throwable e);
}
