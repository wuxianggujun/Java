package com.wuxianggujun.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ķ���������
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    /**
     * ������������
     * @return
     */
    String value() default "";

    /**
     * �Ƿ�ش�
     * @return
     */
    boolean required() default true;
}
