package com.wuxianggujun.ioc;

import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.core.annotation.Autowired;
import com.wuxianggujun.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Optional;

@Slf4j
public class Ioc {
    /**
     * Bean 容器
     */
    private final BeanContainer beanContainer;

    public Ioc() {
        beanContainer = BeanContainer.getInstance();
    }

    public void doIoc() {
        for (Class<?> clazz : beanContainer.getClasses()) {//遍历Bean容器中所有的bean
            final Object targetBean = beanContainer.getBean(clazz);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {//遍历 Bean中所有的属性
                if (field.isAnnotationPresent(Autowired.class)) {//如果该属性被Autowired 注解，则对其注入
                    final Class<?> fieldClass = field.getType();
                    Object fieldValue = getClassInstance(fieldClass);
                    if (fieldValue != null) {
                        ClassUtil.setField(field, targetBean, fieldValue);
                    } else {
                        throw new RuntimeException("无法注入对应的类，目标类型：" + fieldClass.getName());
                    }
                }
            }
        }
    }

    /**
     * 根据 Class 获取其实例或者实现类
     *
     * @param clazz
     * @return
     */
    private Object getClassInstance(final Class<?> clazz) {
        return Optional.ofNullable(beanContainer.getBean(clazz))
                .orElseGet(() -> {
                    Class<?> implementClass = getImplementClass(clazz);
                    if (implementClass != null) {
                        return beanContainer.getBean(implementClass);
                    }
                    return null;
                });
    }

    /**
     * 获取接口的实现类
     *
     * @param interfaceClazz
     * @return
     */
    private Class<?> getImplementClass(final Class<?> interfaceClazz) {
        return beanContainer.getClassesBySuper(interfaceClazz)
                .stream().findFirst().orElse(null);
    }
}
