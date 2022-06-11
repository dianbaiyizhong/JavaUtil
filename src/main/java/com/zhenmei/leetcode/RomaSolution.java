package com.zhenmei.leetcode;

public class RomaSolution {
    public String intToRoman(int num) {

        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();

        String[] romaNum = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < nums.length; i++) {

            while (num >= nums[i]) {
                num = num - nums[i];
                result.append(romaNum[i]);
            }

        }

        return result.toString();

    }


    public static void main(String[] args) {

        System.out.println(new RomaSolution().intToRoman(58));
    }
}
