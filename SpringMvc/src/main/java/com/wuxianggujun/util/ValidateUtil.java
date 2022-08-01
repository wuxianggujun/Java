package com.wuxianggujun.util;
/**
 * 验证相关工具类
 */
public final class ValidateUtil {

    /**
     * Object 是否为 null
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * String 是否为 null 或""
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }
    
    /**
     * Object 是否不为 null
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * String 是否不为 null 或""
     */
    public static boolean isNotEmpty(String obj) {
        return !isEmpty(obj);
    }
    
}