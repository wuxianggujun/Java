package com.wuxianggujun.core;

import com.wuxianggujun.core.annotation.Component;
import com.wuxianggujun.core.annotation.Controller;
import com.wuxianggujun.core.annotation.Repository;
import com.wuxianggujun.core.annotation.Service;
import com.wuxianggujun.util.ClassUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Bean 容器
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 是否加载bean
     */
    private boolean isLoadBean = false;

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);


    /**
     * 存放所有bean的map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 获取bean容器实例
     *
     * @return
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    /**
     * 扫描加载的所有bean
     *
     * @param basePackage
     */
    public void loadBeans(String basePackage) {
        if (isLoadBean) {
            log.warn("bean 已经加载过了");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.getPackageClass(basePackage);
        classSet.stream().filter(clazz -> {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                if (clazz.isAnnotationPresent(annotation)) {
                    return true;
                }
            }
            return false;
        }).forEach(clazz -> beanMap.put(clazz, ClassUtil.newInstance(clazz)));
        isLoadBean = true;
    }

    /**
     * 是否加载bean
     *
     * @return
     */
    public boolean isLoadBean() {
        return isLoadBean;
    }

    /**
     * 获取bean实例
     *
     * @param clazz
     * @return
     */
    public Object getBean(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return beanMap.get(clazz);
    }

    /**
     * 获取所有bean的集合
     *
     * @return
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 添加一个bean实例
     *
     * @param clazz
     * @param bean
     * @return
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除bean实例
     *
     * @param clazz
     */
    public void removeBean(Class<?> clazz) {
        beanMap.remove(clazz);
    }

    /**
     * 返回 bean实例数量
     *
     * @return
     */
    public int size() {
        return beanMap.size();
    }

    /**
     * 所有bean的class集合
     *
     * @return
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 通过注解获取Bean的Class集合
     *
     * @param annotation
     * @return
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        return beanMap.keySet()
                .stream()
                .filter(clazz -> clazz.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }

    /**
     * 通过实现类或者父类获取bean的class集合
     *
     * @param superClass
     * @return
     */
    public Set<Class<?>> getClassesBySuper(Class<?> superClass) {
        return beanMap.keySet()
                .stream()
                .filter(superClass::isAssignableFrom)
                .filter(clazz -> !clazz.equals(superClass))
                .collect(Collectors.toSet());
    }

    /**
     * 通过内部枚举类生成唯一的BeanContainer
     */
    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

}
