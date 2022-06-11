package com.zhenmei.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {

    Map<Integer, Integer> resultMap = new HashMap<>();

    public int climbStairs(int n) {
        resultMap.put(1, 1);
        resultMap.put(2, 2);
        for (int i = 1; i <= n; i++) {
            int result = getMaxResult(i);
            if (i == n) {
                return result;
            }
        }
        return 0;
    }

    public int getMaxResult(int n) {
        if (n == 1 || n == 2) {
            return resultMap.get(n);
        }
        if (resultMap.get(n) == null) {
            resultMap.put(n, resultMap.get(n - 1) + resultMap.get(n - 2));
        }
        return resultMap.get(n);
    }


    public static void main(String[] args) {

        System.out.println(new Test().climbStairs(4));

    }
}
