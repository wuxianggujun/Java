package com.wuxianggujun.aop;

import com.wuxianggujun.aop.advice.Advice;
import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.core.annotation.Aspect;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        //创建所有的代理通知类
        List<ProxyAdvisor> proxyList = beanContainer.getClassesBySuper(Advice.class).stream().filter(clazz -> clazz.isAnnotationPresent(Aspect.class))
                .map(this::createProxyAdvisor).toList();
        //创建代理类并且注入到Bean容器中
        beanContainer.getClasses()
                .stream()
                .filter(target -> !Advice.class.isAssignableFrom(target))
                .filter(target -> !target.isAnnotationPresent(Aspect.class))
                .forEach(target -> {
                    List<ProxyAdvisor> matchProxies = createMatchProxies(proxyList, target);
                    if (matchProxies.size() > 0) {
                        Object proxyBean = ProxyCreator.createProxy(target, matchProxies);
                        beanContainer.addBean(target, proxyBean);
                    }
                });
    }

    /**
     * 通过Aspect切面类创建代理类
     *
     * @param aspectClass
     * @return
     */
    private ProxyAdvisor createProxyAdvisor(Class<?> aspectClass) {
        int order = 0;
        if (aspectClass.isAnnotationPresent(Order.class)) {
            order = aspectClass.getAnnotation(Order.class).value();
        }
        String expression = aspectClass.getAnnotation(Aspect.class).pointcut();
        ProxyPointcut proxyPointcut = new ProxyPointcut();
        proxyPointcut.setExpression(expression);
        Advice advice = (Advice) beanContainer.getBean(aspectClass);
        return new ProxyAdvisor(advice, proxyPointcut, order);
    }

    /**
     * 获取目标类匹配的代理通知类
     *
     * @param proxyList
     * @param targetClass
     * @return
     */
    private List<ProxyAdvisor> createMatchProxies(List<ProxyAdvisor> proxyList, Class<?> targetClass) {
        Object targetBean = beanContainer.getBean(targetClass);
        return proxyList.stream().filter(advisor -> advisor.getPointcut().matches(targetBean.getClass()))
                .sorted(Comparator.comparingInt(ProxyAdvisor::getOrder))
                .collect(Collectors.toList());
    }
}
