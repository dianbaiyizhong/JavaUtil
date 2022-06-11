package com.zhenmei.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Test2 {

    public static void main(String[] args) {


        Stack stack = new Stack();

        int[] arr = {1, 1, 1, 0, 1};


        for (int i = 0; i < arr.length; i++) {
            shirtLeft(arr);

            System.out.println(Arrays.toString(arr));

            System.out.println(getTen(arr));
        }

//        Arrays.cop


    }

    public static int getTen(int[] arr) {

        int result = 0;
        for (int i = 0; i < arr.length; i++) {


            if (arr[i] != 0) {
                result = result + (int) Math.pow(2, i);

            }

        }

        return result;
    }


    public static void shirtLeft(int[] arr) {
        int tmp = arr[0];

        for (int i = 0; i < arr.length; i++) {


            if (i == arr.length - 1) {
                arr[i] = tmp;
            } else {
                arr[i] = arr[i + 1];
            }


        }

    }
}
