package com.wuxianggujun.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 目标代理类的范围
     */
    //Class<? extends Annotation> target();
    String pointcut() default "";
}
