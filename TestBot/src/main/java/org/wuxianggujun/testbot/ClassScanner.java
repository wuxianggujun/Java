package org.wuxianggujun.testbot;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

public class ClassScanner {
    private final String basePackage;
    private final boolean recursive;
    private final Predicate<String> packagePredicate;
    private final Predicate<Class> classPredicate;

    /**
     * Instantiates a new Class scanner.
     *
     * @param basePackage      the base package
     * @param recursive        是否递归扫描
     * @param packagePredicate the package predicate
     * @param classPredicate   the class predicate
     */
    public ClassScanner(String basePackage, boolean recursive, Predicate<String> packagePredicate,
                        Predicate<Class> classPredicate) {
        this.basePackage = basePackage;
        this.recursive = recursive;
        this.packagePredicate = packagePredicate;
        this.classPredicate = classPredicate;
    }

    /**
     * Do scan all classes set.
     *
     * @return the set
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Set<Class<?>> doScanAllClasses() throws IOException, ClassNotFoundException {

        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

        String packageName = basePackage;

        // 如果最后一个字符是“.”，则去掉
        if (packageName.endsWith(".")) {
            packageName = packageName.substring(0, packageName.lastIndexOf('.'));
        }

        // 将包名中的“.”换成系统文件夹的“/”
        String basePackageFilePath = packageName.replace('.', '/');

        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(basePackageFilePath);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String protocol = resource.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(resource.getFile(), "UTF-8");
                // 扫描文件夹中的包和类
                doScanPackageClassesByFile(classes, packageName, filePath, recursive);
            }
        }

        return classes;
    }

    /**
     * 在文件夹中扫描包和类
     */
    private void doScanPackageClassesByFile(Set<Class<?>> classes, String packageName, String packagePath,
                                            boolean recursive) throws ClassNotFoundException {
        // 转为文件
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        final boolean fileRecursive = recursive;
        // 列出文件，进行过滤
        // 自定义文件过滤规则
        File[] dirFiles = dir.listFiles((FileFilter) file -> {
            String filename = file.getName();

            if (file.isDirectory()) {
                if (!fileRecursive) {
                    return false;
                }

                if (packagePredicate != null) {
                    return packagePredicate.test(packageName + "." + filename);
                }
                return true;
            }

            return filename.endsWith(".class");
        });

        if (null == dirFiles) {
            return;
        }

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                // 如果是目录，则递归
                doScanPackageClassesByFile(classes, packageName + "." + file.getName(), file.getAbsolutePath(), recursive);
            } else {
                // 用当前类加载器加载 去除 fileName 的 .class 6 位
                String className = file.getName().substring(0, file.getName().length() - 6);
                Class<?> loadClass = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
                if (classPredicate == null || classPredicate.test(loadClass)) {
                    classes.add(loadClass);
                }
            }
        }
    }
}
