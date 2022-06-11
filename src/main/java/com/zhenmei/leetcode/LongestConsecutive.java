package com.zhenmei.leetcode;

import java.util.Arrays;

public class LongestConsecutive {


    public static int longestConsecutive(int[] str) {

        if(str.length==0){
            return 0;
        }

        Arrays.sort(str);

        int finalRet = 0;
        int longRet = 0;

        boolean flag = false;


//       System.out.println(Arrays.toString(str));
        for (int i = 0; i < str.length - 1; i++) {

            if (str[i + 1] - str[i] == 0){
                continue;
            }
            if (str[i + 1] - str[i] == 1) {
                flag = true;
            } else {
                flag = false;
                longRet = 0;
            }

            if (flag) {
                longRet++;
            }
            if (longRet > finalRet) {
                finalRet = longRet;
            }

        }

        return finalRet + 1;
    }

    public static void main(String[] args) {


//        int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {1,2,0,1};
//        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(nums));

    }
}
