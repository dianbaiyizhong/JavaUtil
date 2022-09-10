package com.zhenmei.leetcode.l720;

import cn.hutool.extra.tokenizer.Word;

import java.util.*;

public class Solution {

    private static Set<String> set = new HashSet<>();

    public String longestWord(String[] words) {
        set.clear();
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        int max = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (isOk(word)) {
                if (word.length() >= max) {
                    max = word.length();
                    result.add(word);
                }
            }
        }
        List<String> result2 = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).length() == max) {
                result2.add(result.get(i));
            }
        }
        Collections.sort(result2);
        if (result2.size() == 0) {
            return "";
        }
        return result2.get(0);
    }

    private static boolean isOk(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            String w = word.substring(0, i + 1);
            if (!set.contains(w)) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {

//        System.out.println(isOk("fcmz"));
//        System.out.println("fcmz">"yodn");
//        String[] words = {"w", "wo", "wor", "worl", "world"};
//      String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
//        String[] words = {"yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod", "ewqz", "y"};
//        String[] words = {"m", "mo", "moc", "moch", "mocha", "l", "la", "lat", "latt", "latte", "c", "ca", "cat"};

//        String[] words = {"rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy", "onpd"};
//        String[] words = {"ogz", "eyj", "e", "ey", "hmn", "v", "hm", "ogznkb", "ogzn", "hmnm", "eyjuo", "vuq", "ogznk", "og", "eyjuoi", "d"};
        String[] words = {"wo", "wor", "worl", "world"};
        System.out.println(new Solution().longestWord(words));

    }
}
