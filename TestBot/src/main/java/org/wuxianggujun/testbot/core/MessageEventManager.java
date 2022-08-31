package org.wuxianggujun.testbot.core;

import java.util.HashMap;
import java.util.Map;

public class MessageEventManager implements BeanPostProcessor {

    private static final Map<String, String> messageClassMaps = new HashMap<>();

    @Override
    public void postProcessInitialization(Class<?> clazz) {
        System.out.println(clazz);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.init("org.wuxianggujun.testbot");
    }

}
