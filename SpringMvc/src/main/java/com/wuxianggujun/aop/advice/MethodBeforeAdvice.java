package com.wuxianggujun.aop.advice;

import java.lang.reflect.Method;

/**
 * ǰ��֪ͨ�ӿ�
 */
public interface MethodBeforeAdvice extends Advice{
    /**
     * ǰ�÷���
     */
    void before(Class<?> clazz, Method method,Object[] args) throws Throwable;
}
