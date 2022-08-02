package com.wuxianggujun.aop;

import com.wuxianggujun.aop.advice.Advice;
import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.core.annotation.Aspect;
import lombok.extern.slf4j.Slf4j;

/**
 * AopÖ´ÐÐÆ÷
 */
@Slf4j
public class Aop {

    /**
     * Bean ÈÝÆ÷
     */
    private final BeanContainer beanContainer;

    public Aop() {
        beanContainer = BeanContainer.getInstance();
    }


    public void doAop() {
        beanContainer.getClassesBySuper(Advice.class)
                .stream()
                .filter(clazz -> clazz.isAnnotationPresent(Aspect.class))
                .forEach(clazz -> {
                    Advice advice = (Advice) beanContainer.getBean(clazz);
                    Aspect aspect = clazz.getAnnotation(Aspect.class);
                    beanContainer.getClassesByAnnotation(aspect.target())
                            .stream()
                            .filter(target -> !Advice.class.isAssignableFrom(target))
                            .filter(target -> !target.isAnnotationPresent(Aspect.class))
                            .forEach(target -> {
                                ProxyAdvisor advisor = new ProxyAdvisor(advice);
                                Object proxyBean = ProxyCreator.createProxy(target, advisor);
                                beanContainer.addBean(target, proxyBean);
                            });
                });
    }
}
