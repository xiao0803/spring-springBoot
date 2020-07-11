package com.example.common.tools;


import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Object 简单的工具类
 * @author Created by xiaolj@jiguang.cn on 2018/11/21.
 */
public final class ObjectTools {

    /**
     * 判断对象是否为 null
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断对象是否非 null
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 判断对象是否为空 (主要针对集合和 String 类)
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof String) {
            return ((String) obj).trim().length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Optional) {
            return !((Optional)obj).isPresent();
        } else {
            return false;
        }
    }

    /**
     * 判断对象是否不为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 将对象转为 String
     * @param object object, 需要自己实现 toString 方法, 否则调用默认的 Object 里的 toString
     * @return
     */
    public static String covertToString(Object object) {
        return (null == object)? null: object.toString();
    }

    /**
     * 将对象转为 Integer
     * @param object
     * @return
     * @throws NumberFormatException
     */
    public static Integer convertToInteger(Object object) throws NumberFormatException{
        return Integer.valueOf(covertToString(object));
    }

    /**
     * 将对象转为 Long
     * @param object
     * @return
     * @throws NumberFormatException
     */
    public static Long convertToLong(Object object) throws NumberFormatException{
        return Long.valueOf(covertToString(object));
    }

    /**
     * 将对象转为 Short
     * @param object
     * @return
     * @throws NumberFormatException
     */
    public static Short convertToShort(Object object) throws NumberFormatException{
        return Short.valueOf(covertToString(object));
    }

    /**
     * 将对象转为 Byte
     * @param object
     * @return
     * @throws NumberFormatException
     */
    public static Byte convertToByte(Object object) throws NumberFormatException{
        return Byte.valueOf(covertToString(object));
    }

    /**
     * 将对象转为 Double
     * @param object
     * @return
     * @throws NumberFormatException
     */
    public static Double convertToDouble(Object object) throws NumberFormatException{
        return Double.valueOf(covertToString(object));
    }

    /**
     * 将对象转为 Float
     * @param object
     * @return
     * @throws NumberFormatException
     */
    public static Float convertToFloat(Object object) throws NumberFormatException{
        return Float.valueOf(covertToString(object));
    }

    /**
     * 将对象转为 Boolean
     * @param object
     * @return
     */
    public static Boolean convertToBoolean(Object object) {
        return Boolean.valueOf(covertToString(object));
    }

    /**
     * 将包含特殊字符的全角字符串转为半角字符串
     *
     * @param fullWidthStr 全角字符串
     * @return 半角字符串
     */
    public static String convertToHalfChar(String fullWidthStr) {
        if (null == fullWidthStr || fullWidthStr.length() <= 0) {
            return "";
        }
        char[] charArray = fullWidthStr.toCharArray();
        // 对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            // 如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray);
    }

    /**
     * 生成8位由数字组成的随机字符串
     *
     * @return
     */
    /*public static String create8UniqueStr() {
        String code = "";
        int[] arr = new int[8];
        int num = 0;
        for (int i = 0; i < 8; i++) {
            arr[i] = new Random().nextInt(10);
            // 防止第一位出现 0
            if (0 == i && 0 == arr[0]) {
                i-- ;
                continue ;
            }
            num = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] == num) {
                    i--;
                    break;
                }
            }
        }
        if (arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                code += arr[i];
            }
        }
        return code;
    }*/

    /**
     * 判断一个对象的所有字段是否都为空
     * @param object
     * @return true: 都为空; false: 只要有一个字段不为空
     */
    public static boolean allFieldIsEmpty(Object object) {
        if (object == null){
            return true;
        }
        // 1. 获取其从父类继承下来的所有字段 (Object 除外)
        List<Field> fieldList = new ArrayList<>();
        Class clazz = object.getClass();
        while (clazz != null && !clazz.equals(Object.class)) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        // 2. 非空校验
        for (Field field: fieldList) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = null;
            // 忽略序列化 id
            if ("serialVersionUID".equalsIgnoreCase(fieldName)) {
                continue;
            }
            // 忽略静态变量
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if (isStatic) {
                continue;
            }
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                // 已经设置了 accessible ...
                e.printStackTrace();
            }
            if (ObjectTools.isNotEmpty(fieldValue)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个对象的所有字段是否都不为 null
     * @param object
     * @return true: 都不为 null; false: 只要有一个字段为 null
     */
    public static boolean allFieldNotNull(Object object) {
        if (object == null){
            return false;
        }
        // 1. 获取其从父类继承下来的所有字段 (Object 除外)
        List<Field> fieldList = new ArrayList<>();
        Class clazz = object.getClass();
        while (clazz != null && !clazz.equals(Object.class)) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        // 2. 非空校验
        for (Field field: fieldList) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = null;
            // 忽略序列化 id
            if ("serialVersionUID".equalsIgnoreCase(fieldName)) {
                continue;
            }
            // 忽略静态变量
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if (isStatic) {
                continue;
            }
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                // 已经设置了 accessible ...
                e.printStackTrace();
            }
            if (null == fieldValue) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取进程 id
     * @return
     */
    public static long getPID(){
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        // get pid
        String pid = name.split("@")[0];
        return Long.parseLong(pid);
    }

    public static void main(String[] args) {
        System.out.println(isEmpty(new HashMap<>(1)));
        System.out.println(allFieldIsEmpty(new Object()));
        System.out.println(getPID());
    }
}
