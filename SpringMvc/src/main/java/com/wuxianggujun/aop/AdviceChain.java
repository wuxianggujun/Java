package com.wuxianggujun.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 通知链
 */
public class AdviceChain {

    /**
     * 目标类
     */
    @Getter
    private final Class<?> targetClass;

    /**
     * 目标实例
     */
    @Getter
    private final Object target;

    /**
     * 目标方法
     */
    @Getter
    private final Method method;

    /**
     * 目标方法参数
     */
    @Getter
    private final Object[] args;

    /**
     * 代理方法
     */
    private final MethodProxy methodProxy;

    /**
     * 代理通知类
     */
    private List<ProxyAdvisor> proxyAdvisorList;

    /**
     * 代理通知的 index
     */
    private int adviceIndex = 0;


    public AdviceChain(Class<?> targetClass, Object target, Method method, Object[] args, MethodProxy methodProxy, List<ProxyAdvisor> proxyAdvisorList) {
        this.targetClass = targetClass;
        this.target = target;
        this.method = method;
        this.args = args;
        this.methodProxy = methodProxy;
        this.proxyAdvisorList = proxyAdvisorList;
    }

    /**
     * 递归执行，执行代理通知列
     *
     * @return
     */
    public Object doAdviceChain() throws Throwable{
        Object result;
        while (adviceIndex<proxyAdvisorList.size() && !proxyAdvisorList.get(adviceIndex).getPointcut().matches(method)){
            //如果当前方法不匹配切点，则略过该代理通知类
            adviceIndex++;
        }
        if (adviceIndex <proxyAdvisorList.size()){
            result = proxyAdvisorList.get(adviceIndex++).doProxy(this);
        }else {
            result = methodProxy.invokeSuper(target,args);
        }
        return result;
    }

}
