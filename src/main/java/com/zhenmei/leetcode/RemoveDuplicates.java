package com.zhenmei.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static int removeDuplicates(int[] str) {

        Set set = new HashSet();

        int k = 0;
        for (int i = 0; i < str.length; i++) {

            if (!set.contains(str[i])) {
                str[k] = str[i];
                set.add(str[i]);
                k++;
            }
        }

        return set.size();

    }

    public static void main(String[] args) {
        int[] str = {0,0,1,1,1,2,2,3,3,4};


        System.out.println(removeDuplicates(str));
    }
}
