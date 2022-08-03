package com.wuxianggujun.aop;

import com.wuxianggujun.aop.advice.Advice;
import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.core.annotation.Aspect;
import lombok.extern.slf4j.Slf4j;

/**
 * Aop执行器
 */
@Slf4j
public class Aop {

    /**
     * Bean 容器
     */
    private final BeanContainer beanContainer;

    public Aop() {
        beanContainer = BeanContainer.getInstance();
    }


    public void doAop() {
        beanContainer.getClassesBySuper(Advice.class)
                .stream()
                .filter(clazz -> clazz.isAnnotationPresent(Aspect.class))
                .map(this::createProxyAdvisor)
                .forEach(proxyAdvisor -> beanContainer.getClasses()
                        .stream()
                        .filter(target -> !Advice.class.isAssignableFrom(target))
                        .filter(target -> !target.isAnnotationPresent(Aspect.class))
                        .forEach(target -> {
                            if (proxyAdvisor.getPointcut().matches(target)) {
                                Object proxyBean = ProxyCreator.createProxy(target, proxyAdvisor);
                                beanContainer.addBean(target, proxyBean);
                            }
                        }));
    }

    /**
     * 通过Aspect切面类创建代理类
     *
     * @param aspectClass
     * @return
     */
    private ProxyAdvisor createProxyAdvisor(Class<?> aspectClass) {
        String expression = aspectClass.getAnnotation(Aspect.class).pointcut();
        ProxyPointcut proxyPointcut = new ProxyPointcut();
        proxyPointcut.setExpression(expression);
        Advice advice = (Advice) beanContainer.getBean(aspectClass);
        return new ProxyAdvisor(advice, proxyPointcut);
    }
}
