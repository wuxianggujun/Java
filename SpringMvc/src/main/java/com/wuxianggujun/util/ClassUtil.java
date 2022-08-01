package com.wuxianggujun.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;

/**
 * 类操作工具类
 */
@Slf4j
public class ClassUtil {
    /**
     * File 形式url协议
     */
    public static final String FILE_PROTOCOL = "file";

    /**
     * Jar 形式url协议
     */
    public static final String JAR_PROTOCOL = "jar";

    /**
     * 获取 classloader
     *
     * @return ClassLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取 Class
     *
     * @param className
     * @return Class<?>
     */
    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化 class
     *
     * @param className
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        try {
            Class<?> clazz = loadClass(className);
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化 class
     *
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        try {
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }


    public static void setField(Field field, Object target, Object value) {
        setField(field, target, value, true);
    }

    /**
     * 设置类的属性值
     *
     * @param field
     * @param target
     * @param value
     * @param accessible
     */
    public static void setField(Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取包下类集合
     * @param basePackage
     * @return
     */
    public static Set<Class<?>> getPackageClass(String basePackage) {
        URL url = getClassLoader().getResource(basePackage.replace(".", "/"));
        if (url == null) {
            throw new RuntimeException("无法正常获取项目路径文件");
        }
        try {
            if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
                //诺为普通文件，则遍历
                File file = new File(url.getFile());
                Path basePath = file.toPath();
                return Files.walk(basePath).filter(path -> path.toFile().getName().endsWith(".class"))
                        .map(path -> getClassByPath(path, basePath, basePackage))
                        .collect(Collectors.toSet());
            } else if (url.getProtocol().equalsIgnoreCase(JAR_PROTOCOL)) {
                //诺在jar包中，则解析jar包中的entry
                JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                return jarURLConnection.getJarFile().stream().filter(jarEntry -> jarEntry.getName().endsWith(".class"))
                        .map(ClassUtil::getClassByJar)
                        .collect(Collectors.toSet());
            }
            return Collections.emptySet();
        } catch (IOException e) {
            log.error("load package error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从jar 包获取class
     *
     * @param jarEntry
     * @return
     */
    private static Class<?> getClassByJar(JarEntry jarEntry) {
        String jarEntryName = jarEntry.getName();
        //获取类名
        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
        return loadClass(className);
    }


    /**
     * 从path加载class
     *
     * @param ClassPath
     * @param basePath
     * @param basePackage
     * @return
     */
    private static Class<?> getClassByPath(Path ClassPath, Path basePath, String basePackage) {
        String packageName = ClassPath.toString().replace(basePath.toString(), "");
        String className = (basePackage + packageName)
                .replace("/", ".")
                .replace("\\", ".")
                .replace(".class", "");
        return loadClass(className);
    }

}
