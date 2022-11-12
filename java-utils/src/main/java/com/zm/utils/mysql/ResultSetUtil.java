package com.zm.utils.mysql;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ResultSetUtil {
    public static <T> ArrayList<T> packed(ResultSet resultSet, Class<T> tClass) {
        ArrayList<T> arrayList = new ArrayList<>();
        Field[] declaredFields = tClass.getDeclaredFields();
        try {
            while (resultSet.next()) {
                T t = tClass.getDeclaredConstructor().newInstance();
                int i = 1;
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    Object object = resultSet.getObject(i++);
                    declaredField.set(t, object);
                }
                arrayList.add(t);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
