package com.zm.utils.print;



import java.util.*;
import java.util.stream.Collectors;

/**
 * 日志工具类，可以打印表格日志哦
 */
public class PrintUtils {


    @SuppressWarnings("unchecked")
    public static String getPrintStrByMap(Object mapListParam) {
        List<Map<Object,Object>> mapList = (List<Map<Object, Object>>) mapListParam;
        TableGenerator tableGenerator = new TableGenerator();
        // 获取一个元素
        Optional<Map<Object, Object>> first = mapList.stream().findFirst();
        // 得到key集合
        Set<Object> keyObjects = first.get().keySet();
        List<Object> objects = new ArrayList<>(keyObjects);
        // 转string
        List<String> headList = objects.stream().map(String::valueOf).collect(Collectors.toList());

        List<List<String>> rowsList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            List<String> row = new ArrayList<>();
            int finalI = i;
            keyObjects.forEach(key -> row.add(String.valueOf(mapList.get(finalI).get(key))));
            rowsList.add(row);
        }
        return tableGenerator.generateTable(headList, rowsList);
    }




    public static void main(String[] args) {
        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "张三");
        map1.put("sex", "男");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "李四");
        map2.put("sex", "女");
        mapList.add(map1);
        mapList.add(map2);
        System.out.println(PrintUtils.getPrintStrByMap(mapList));
    }
}
