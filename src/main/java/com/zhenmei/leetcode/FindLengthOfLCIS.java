package com.zhenmei.leetcode;

import java.util.Arrays;

public class FindLengthOfLCIS {

    public int findLengthOfLCIS(int[] str) {

        if(str.length==0){
            return 0;
        }


        int finalRet = 0;
        int longRet = 0;

        boolean flag = false;


        for (int i = 0; i < str.length - 1; i++) {

            if (str[i + 1] > str[i]) {
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

        int[] str = {1,3,5,4,7};
        System.out.println(new FindLengthOfLCIS().findLengthOfLCIS(str));
    }
}
