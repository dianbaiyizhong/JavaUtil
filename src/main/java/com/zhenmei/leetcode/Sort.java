package com.zhenmei.leetcode;

import java.util.Arrays;


public class Sort {

    /**
     * 冒泡排序法
     */
    private static void bubbleSort(int[] nums) {

        boolean isSwap = true;
        for (int i = 0; i < nums.length && isSwap; i++) {
            isSwap = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    isSwap = true;
                }
            }
        }

    }

    private static void swap(int[] nums, int leftIndex, int rightIndex) {
        int tmp = nums[rightIndex];
        nums[rightIndex] = nums[leftIndex];
        nums[leftIndex] = tmp;
    }

    /**
     * 插入排序法
     */
    private static void insertionSort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            int sortNum = nums[i + 1];
            for (int j = i + 1; j > 0; j--) {
                int tmp = nums[j - 1];
                if (sortNum > tmp) {
                    // 如果指定元素比有序队列最大的那个还要大，那就没有排序的必要了，直接退出这一轮扫描
                    break;
                } else {
                    nums[j] = nums[j - 1];
                    nums[j - 1] = sortNum;
                }

            }
        }

    }

    /**
     * 选择排序法
     */
    private static void selectionSort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);

        }


    }


    /**
     * 归并算法
     *
     * @param nums
     * @return
     */
    public static int[] mergeSort(int[] nums) {

        int[] result = Arrays.copyOfRange(nums, 0, nums.length);

        if (result.length < 2) {
            return result;
        }

        // 取中间值
        int mid = result.length >>> 1;


        int[] left = Arrays.copyOfRange(result, 0, mid);
        int[] right = Arrays.copyOfRange(result, mid, result.length);

        left = mergeSort(left);
        right = mergeSort(right);


        // 当所有数组都拆成一个的时候最先执行
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i] = left[0];
                i++;
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                // 将小的先赋值，然后i++
                result[i] = right[0];
                i++;
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i] = left[0];
            i++;
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i] = right[0];
            i++;
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;

    }


    public static int getMid(int[] nums, int left, int right) {

        // 第一个值作为基准值
        int flag = nums[left];
        // 从右边开始移动
        boolean leftOrRight = false;
        while (left != right) {
            if (leftOrRight) {
                if (nums[left] > flag) {
                    // 从左边开始移动下标，如果大于flag，则来到right下标位置
                    nums[right] = nums[left];
                    leftOrRight = false;
                    right--;
                } else {
                    left++;
                }

            } else {
                if (nums[right] < flag) {
                    nums[left] = nums[right];
                    leftOrRight = true;
                    left++;
                } else {
                    right--;
                }
            }
        }

        nums[left] = flag;

        return right;
    }

    public static int[] quickSort(int[] nums, int left, int right) {


        if (left < right) {
            int mid = getMid(nums, left, right);
            quickSort(nums, left, mid - 1);
            quickSort(nums, mid + 1, right);
        }


        return nums;
    }


    public static void main(String[] args) {
        int[] nums = {3 ,1 ,2, 4, 5};

        int[] arr = Arrays.copyOf(nums, nums.length);


        System.out.println(Arrays.toString(quickSort(arr, 0, arr.length - 1)));
    }
}
