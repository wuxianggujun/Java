package org.wuxianggujun.testbot.core;

import org.wuxianggujun.testbot.ClassScanner;

import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

public class ApplicationContext {

    Set<Class<?>> allClasses = null;

    /**
     * 初始化扫描全部的类
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void init(String packageName) {

        Predicate<String> predicate = s -> true;
        ClassScanner classScanner = new ClassScanner(packageName, true, predicate, null);
        try {
            allClasses = classScanner.doScanAllClasses();
            for (Class<?> it : allClasses) {
                if (BeanPostProcessor.class.isAssignableFrom(it) && !it.isInterface() && !it.isAssignableFrom(BeanPostProcessor.class)) {
                    BeanPostProcessor beanPostProcessor = (BeanPostProcessor) it.getDeclaredConstructor().newInstance();
                    //通知全部的实现类
                    allClasses.forEach(beanPostProcessor::postProcessInitialization);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("扫描类失败！", e);
        }
    }
}