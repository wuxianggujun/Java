package com.wuxianggujun;

import com.wuxianggujun.aop.Order;
import com.wuxianggujun.aop.advice.AroundAdvice;
import com.wuxianggujun.core.annotation.Aspect;
import com.wuxianggujun.core.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
@Order(1)
@Aspect(pointcut = "@within(com.wuxianggujun.core.annotation.Controller)")
public class DoodleAspect implements AroundAdvice {

    @Override
    public void before(Class<?> clz, Method method, Object[] args) throws Throwable {
        log.info("-----------before  DoodleAspect-----------");
        log.info("class: {}, method: {}", clz.getName(), method.getName());
    }

    @Override
    public void afterReturning(Class<?> clz, Object returnValue, Method method, Object[] args) throws Throwable {
        log.info("-----------after  DoodleAspect-----------");
        log.info("class: {}, method: {}", clz, method.getName());
    }

    @Override
    public void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e) {
        log.error("-----------error  DoodleAspect-----------");
        log.error("class: {}, method: {}, exception: {}", clz, method.getName(), e.getMessage());
    }
}