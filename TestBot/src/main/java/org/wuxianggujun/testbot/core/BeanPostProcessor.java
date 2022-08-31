package org.wuxianggujun.testbot.core;

/**
 * 监听扫描的bean
 */
public interface BeanPostProcessor {
   void postProcessInitialization(Class<?> clazz);
}
