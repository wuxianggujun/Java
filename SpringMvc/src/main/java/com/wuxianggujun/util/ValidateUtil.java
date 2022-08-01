package com.wuxianggujun.util;
/**
 * ��֤��ع�����
 */
public final class ValidateUtil {

    /**
     * Object �Ƿ�Ϊ null
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * String �Ƿ�Ϊ null ��""
     */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }
    
    /**
     * Object �Ƿ�Ϊ null
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * String �Ƿ�Ϊ null ��""
     */
    public static boolean isNotEmpty(String obj) {
        return !isEmpty(obj);
    }
    
}