package com.zm.utils.str;

/**
 * 空判断类
 */
public class EmptyUtils {

    /**
     * @param object
     * @return
     */
    public static boolean isEmptyStrict(Object object) {
        if (object == null) {
            return true;
        }
        String objectStr = object.toString();
        if (objectStr.length() == 0) {
            return true;
        }
        if ("null".equalsIgnoreCase(objectStr)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyStrict(Object object) {
        return !isEmptyStrict(object);
    }

    public static void main(String[] args) {

        System.out.println(EmptyUtils.isEmptyStrict(null));
        System.out.println(EmptyUtils.isEmptyStrict("Null"));
        System.out.println(EmptyUtils.isEmptyStrict(""));
        System.out.println(EmptyUtils.isEmptyStrict(1));
        System.out.println(EmptyUtils.isEmptyStrict(0));
        System.out.println(EmptyUtils.isEmptyStrict(1.0));
        System.out.println(EmptyUtils.isEmptyStrict(1d));


    }
}
