package com.wuxianggujun.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Http ����·��
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * ����·��
     * @return
     */
    String value() default "";

    /**
     * ���󷽷�
     * @return
     */
    RequestMethod method() default RequestMethod.GET;
}
