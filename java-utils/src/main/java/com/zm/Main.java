package com.zm;


import cn.hutool.core.text.StrFormatter;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


//        HttpRequest post = HttpUtil.createPost("http://localhost:2408/user//register");
//        HttpResponse execute = post.body("{\n" +
//                "    \"userName\": \"zhangsan\"\n" +
//                "}").execute();
//
//        System.out.println(execute.body());

        List<Map> maps = JSON.parseArray("", Map.class);

        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");

        String format = StrFormatter.format("{a} and {b}", map);

        System.out.println(format);

    }
}