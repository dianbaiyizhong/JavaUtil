package com.zhenmei.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupAnagrams {


    public static boolean isOk(String s, String t) {

        if(s.length()!=t.length()){

            return false;
        }


        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
            table[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < table.length; i++) {
            if (table[i] != 0) {
                return false;
            }
        }
        return true;


    }


    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();


        // 记录已经分好组的序号
        Set grouped = new HashSet();

        for (int i = 0; i < strs.length; i++) {
            List<String> child = new ArrayList<>();
            if (grouped.contains(i)) {
                // 分过组的不要再分了
                continue;
            } else {
                child.add(strs[i]);
            }
            for (int j = i + 1; j < strs.length; j++) {
                boolean is = isOk(strs[i], strs[j]);
                if (is) {
                    child.add(strs[j]);
                    grouped.add(j);
                }
            }
            ret.add(child);
        }


        return ret;

    }

    public static void main(String[] args) {


        String[] arr ={"eat", "tea", "tan", "ate", "nat", "bat"};
//        String[] arr = {"", "eat"};
//        System.out.println(isOk("", "b"));
       System.out.println(new GroupAnagrams().groupAnagrams(arr));
    }
}
