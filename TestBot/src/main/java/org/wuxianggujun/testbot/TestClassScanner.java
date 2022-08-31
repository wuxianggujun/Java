package org.wuxianggujun.testbot;

import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

public class TestClassScanner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Predicate<String> predicate = s -> true;
        ClassScanner classScanner = new ClassScanner("org.wuxianggujun.testbot", true, predicate, null);
        Set<Class<?>> params = classScanner.doScanAllClasses();
        params.forEach(it -> {
            System.out.println(it.getName());
        });
    }
}
