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
 * Bean ����
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * �Ƿ����bean
     */
    private boolean isLoadBean = false;

    /**
     * ����bean��ע���б�
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);


    /**
     * �������bean��map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * ��ȡbean����ʵ��
     *
     * @return
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    /**
     * ɨ����ص�����bean
     *
     * @param basePackage
     */
    public void loadBeans(String basePackage) {
        if (isLoadBean) {
            log.warn("bean �Ѿ����ع���");
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
     * �Ƿ����bean
     *
     * @return
     */
    public boolean isLoadBean() {
        return isLoadBean;
    }

    /**
     * ��ȡbeanʵ��
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
     * ��ȡ����bean�ļ���
     *
     * @return
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * ���һ��beanʵ��
     *
     * @param clazz
     * @param bean
     * @return
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * �Ƴ�beanʵ��
     *
     * @param clazz
     */
    public void removeBean(Class<?> clazz) {
        beanMap.remove(clazz);
    }

    /**
     * ���� beanʵ������
     *
     * @return
     */
    public int size() {
        return beanMap.size();
    }

    /**
     * ����bean��class����
     *
     * @return
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * ͨ��ע���ȡBean��Class����
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
     * ͨ��ʵ������߸����ȡbean��class����
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
     * ͨ���ڲ�ö��������Ψһ��BeanContainer
     */
    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

}
