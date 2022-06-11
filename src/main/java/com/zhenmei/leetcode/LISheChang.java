package com.zhenmei.leetcode;
import java.util.Arrays;
import java.util.Scanner;

public class LISheChang {
    /**
     计算最少出列多少位同学，使得剩下的同学排成合唱队形
     */

    public static void main(String[] args){
        int[] peoples = {16, 103, 132, 23, 211, 75, 155, 82, 32, 48, 79, 183, 13, 91, 51, 172, 109, 102, 189, 121, 12, 120, 116, 133, 79, 120, 116, 208, 47, 110, 65, 187, 69, 143, 140, 173, 203, 35, 184, 49, 245, 50, 179, 63, 204, 34, 218, 11, 205, 100, 90, 19, 145, 203, 203, 215, 72, 108, 58, 198, 95, 116, 125, 235, 156, 133, 220, 236, 125, 29, 235, 170, 130, 165, 155, 54, 127, 128, 204, 62, 59, 226, 233, 245, 46, 3, 14, 108, 37, 94, 52, 97, 159, 190, 143, 67, 24, 204, 39, 222, 245, 233, 11, 80, 166, 39, 224, 12, 38, 13, 85, 21, 47, 25, 180, 219, 140, 201, 11, 42, 110, 209, 77, 136};

        int[] l = left(peoples);

        System.out.println(Arrays.toString(l));

        int[] r = right(peoples);
        int max = 0;
        for(int i = 0 ; i < peoples.length ; i++){
            if(max < (l[i]+r[i]-1)){
                max = l[i]+r[i]-1;
            }
        }
        System.out.println(124 -max);
    }
    //186 186 150 200 160 130 197 200
    // 1   1   1   2   2   1   3   4
    //LIs:上升子序列长度
    //f(i): 已arr[i]结尾的LIS
    //如果左边的数：arr[j] < arr[i]
    // f(i) = f(j)+1
    // arr[j+1] < arr[i]
    // f(i) = f(j+1)+1
    //最长LIS: max{ f(j)+1,f(j+1)+1 }
    public static  int[]  left(int[] arr){
        int[] left = new int[arr.length];
        for(int i =1 ; i < arr.length ; i++){
            left[i] = 1;
            for(int j = 1 ; j < i ;j++){
                if(arr[j] < arr[i]){
                    left[i] = Math.max(left[i],left[j]+1);
                }
            }
        }
        return left;
    }
    //186 186 150 200 160 130 197 200
    public static  int[]  right(int[] arr){
        int[] right= new int[arr.length];
        for(int i =arr.length-1 ; i > 0; i--){
            right[i] = 1;
            for(int j = arr.length-1; j >i ;j--){
                if(arr[j] < arr[i]){
                    right[i] = Math.max(right[i],right[j]+1);
                }
            }
        }
        return right;
    }
}