package com.wuxianggujun.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * �����ഴ����
 */
public final class ProxyCreator {
   
    /**
     * ����������
     */
    public static Object createProxy(Class<?> targetClass, List<ProxyAdvisor>  proxyList) {
        return Enhancer.create(targetClass,new AdviceMethodInterceptor(targetClass,proxyList));
    }

    /**
     * MethodInterceptor ʵ����
     */
    private static class AdviceMethodInterceptor implements MethodInterceptor {

        /**
         * Ŀ����
         */
        private final Class<?> targetClass;
        /**
         * ����֪ͨ�б�
         */
        private List<ProxyAdvisor> proxyList;

        public AdviceMethodInterceptor(Class<?> targetClass, List<ProxyAdvisor> proxyList) {
            this.targetClass = targetClass;
            this.proxyList = proxyList;
        }


        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            return new AdviceChain(targetClass,target,method,args,methodProxy,proxyList).doAdviceChain();
        }
    }
}
