package com.wuxianggujun.aop;

import com.wuxianggujun.aop.advice.Advice;
import com.wuxianggujun.aop.advice.AfterReturningAdvice;
import com.wuxianggujun.aop.advice.MethodBeforeAdvice;
import com.wuxianggujun.aop.advice.ThrowsAdvice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProxyAdvisor {

    /**
     * 通知
     */
    private Advice advice;

    private ProxyPointcut pointcut;

    /**
     * 执行顺序
     */
    private int order;

    /**
     * 执行代理方法
     */
    public Object doProxy(AdviceChain adviceChain) throws Throwable {
        Object result = null;
        Class<?> targetClass = adviceChain.getTargetClass();
        Method method = adviceChain.getMethod();
        Object[] args = adviceChain.getArgs();

        if (advice instanceof MethodBeforeAdvice) {
            ((MethodBeforeAdvice) advice).before(targetClass, method, args);
        }
        try {
            result = adviceChain.doAdviceChain();//执行代理链方法
            if (advice instanceof AfterReturningAdvice) {
                ((AfterReturningAdvice) advice).afterReturning(targetClass, result, method, args);
            }

        } catch (Exception e) {
            if (advice instanceof ThrowsAdvice) {
                ((ThrowsAdvice) advice).afterThrowing(targetClass, method, args, e);
            } else {
                throw new Throwable(e);
            }
        }
        return result;
    }
}
