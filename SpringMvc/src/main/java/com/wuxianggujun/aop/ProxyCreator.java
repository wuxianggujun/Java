package com.wuxianggujun.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 代理类创建器
 */
public final class ProxyCreator {
   
    /**
     * 创建代理类
     */
    public static Object createProxy(Class<?> targetClass, ProxyAdvisor proxyAdvisor) {
        return Enhancer.create(targetClass,
                (MethodInterceptor) (target, method, args, proxy) ->
                        proxyAdvisor.doProxy(target, targetClass, method, args, proxy));
    }
}
