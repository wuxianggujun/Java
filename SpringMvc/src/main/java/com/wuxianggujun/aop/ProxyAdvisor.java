package com.wuxianggujun.aop;

import com.wuxianggujun.aop.advice.Advice;
import com.wuxianggujun.aop.advice.AfterReturningAdvice;
import com.wuxianggujun.aop.advice.MethodBeforeAdvice;
import com.wuxianggujun.aop.advice.ThrowsAdvice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodProxy;

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
     * 执行代理方法
     * @param target
     * @param targetClass
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    public Object doProxy(Object target, Class<?> targetClass, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (!pointcut.matches(method)){
            return proxy.invokeSuper(target,args);
        }
        
        Object result = null;

        if (advice instanceof MethodBeforeAdvice) {
            ((MethodBeforeAdvice) advice).before(targetClass, method, args);
        }

        try {
            //执行目标类的方法
            result = proxy.invokeSuper(target, args);

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
