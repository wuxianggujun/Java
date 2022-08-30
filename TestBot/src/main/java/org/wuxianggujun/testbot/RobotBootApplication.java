package org.wuxianggujun.testbot;

import java.lang.annotation.*;

/**
 * 标记机器人框架启动类的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RobotBootApplication {
}
