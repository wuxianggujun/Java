package com.wuxianggujun.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * Ŀ�������ķ�Χ
     */
    //Class<? extends Annotation> target();
    String pointcut() default "";
}
