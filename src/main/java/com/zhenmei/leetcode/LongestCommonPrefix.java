package com.zhenmei.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestCommonPrefix {

    public static boolean isAllEquals(char str[]) {

        Set set = new HashSet();
        for (int i = 0; i < str.length; i++) {
            set.add(str[i]);
        }

        if (set.size() == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static String longestCommonPrefix(String[] str) {


        int[] lengthSum = new int[str.length];

        for (int i = 0; i < lengthSum.length; i++) {
            lengthSum[i] = str[i].length();

        }

        Arrays.sort(lengthSum);


        if (str.length == 1) {
            return str[0];
        }
        int exampleLength = lengthSum[0];

        int pos = 0;
        for (int i = 0; i < exampleLength; i++) {
            pos = i;
            char[] arr = new char[str.length];
            for (int j = 0; j < str.length; j++) {

                arr[j] = str[j].charAt(i);

            }

            boolean isAllEquals = isAllEquals(arr);

            if (!isAllEquals) {
                break;
            }
            pos++;

        }


        return str[0].substring(0, pos);
    }


    public static void main(String[] args) {
//        String[] strs = {"flower","flow","flight"};
        String[] strs = {"ab","a"};


        System.out.println(longestCommonPrefix(strs));

    }
}
