package com.zhenmei.leetcode;

import cn.hutool.core.io.FileUtil;

import javax.script.ScriptException;
import java.io.File;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Test6 {


    public static void main(String[] args) throws ParseException, ScriptException {

        String input = FileUtil.readString(new File("D://tmp1.txt"), Charset.defaultCharset());

        String s = input.split("\n")[0];
        s = s.trim();
        String t = input.split("\n")[1];
        t.trim();
        System.out.println(new Test6().minWindow(s, t));

    }

    static Map<Character, Integer> wordMap = new HashMap<>();
    static Map<Character, Integer> wordTmpMap = new HashMap<>();

    public String minWindow(String s, String t) {

        wordMap.clear();
        wordTmpMap.clear();
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            Integer count = wordTmpMap.get(c);
            if (count == null) {
                wordTmpMap.put(c, 1);
            } else {
                count = count + 1;
                wordTmpMap.put(c, count);
            }
        }
        String minResult = "";
        int min = s.length();
        int right = 0;
        for (int left = 0; left < s.length(); left++) {
            if (left > 0) {
                // 每当left向右边滑动的时候，删除上一个元素
                removeWord(s.charAt(left - 1));
            }
            boolean isContains = false;
            while (!isContains) {
                isContains = isContains();
                // 如果符合条件，说明找到了最短距离，且可以退出while循环
                if (isContains) {
                    String okStr = s.substring(left, right);
                    if (okStr.length() <= min) {
                        min = okStr.length();
                        minResult = okStr;
                    }
                    break;
                } else {
                    // 如果不符合条件，则窗口范围扩增，也就是right+1
                    if (right != s.length()) {
                        addWord(s.charAt(right));
                        right = right + 1;
                    } else {
                        // 如果窗口范围已经扩张到极限，也退出
                        break;
                    }
                }

            }
        }


        return minResult;

    }


    private static void addWord(Character c) {

        Integer count = wordMap.get(c);
        if (count != null) {
            count = count + 1;
            wordMap.put(c, count);
        } else {
            wordMap.put(c, 1);
        }
    }

    private static void removeWord(Character pre) {
        Integer count = wordMap.get(pre);
        if (count != null) {
            count = count - 1;
            wordMap.put(pre, count);
        }

    }

    private static boolean isContains() {
        boolean isEquals = true;
        for (Map.Entry<Character, Integer> entry : wordTmpMap.entrySet()) {
            if (wordMap.containsKey(entry.getKey())) {
                if (entry.getValue() <= wordMap.get(entry.getKey())) {
                    continue;
                } else {
                    isEquals = false;
                    break;
                }
            } else {
                isEquals = false;
                break;
            }
        }
        return isEquals;
    }


}
