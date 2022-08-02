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
     * Bean ����
     */
    private final BeanContainer beanContainer;

    public Ioc() {
        beanContainer = BeanContainer.getInstance();
    }

    public void doIoc() {
        for (Class<?> clazz : beanContainer.getClasses()) {//����Bean���������е�bean
            final Object targetBean = beanContainer.getBean(clazz);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {//���� Bean�����е�����
                if (field.isAnnotationPresent(Autowired.class)) {//��������Ա�Autowired ע�⣬�����ע��
                    final Class<?> fieldClass = field.getType();
                    Object fieldValue = getClassInstance(fieldClass);
                    if (fieldValue != null) {
                        ClassUtil.setField(field, targetBean, fieldValue);
                    } else {
                        throw new RuntimeException("�޷�ע���Ӧ���࣬Ŀ�����ͣ�" + fieldClass.getName());
                    }
                }
            }
        }
    }

    /**
     * ���� Class ��ȡ��ʵ������ʵ����
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
     * ��ȡ�ӿڵ�ʵ����
     *
     * @param interfaceClazz
     * @return
     */
    private Class<?> getImplementClass(final Class<?> interfaceClazz) {
        return beanContainer.getClassesBySuper(interfaceClazz)
                .stream().findFirst().orElse(null);
    }
}
