package com.zhenmei.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test0419 {


    /**
     * 获得的最大硬币数目
     * <p>
     * 有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：
     * <p>
     * 每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。
     * Alice 将会取走硬币数量最多的那一堆。
     * 你将会取走硬币数量第二多的那一堆。
     * Bob 将会取走最后一堆。
     * 重复这个过程，直到没有更多硬币。
     * 给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。
     * <p>
     * 返回你可以获得的最大硬币数目。
     * <p>
     * <p>
     * 示例 1：
     * 输入：piles = [2,4,1,2,7,8]
     * 输出：9
     * 解释：选出 (2, 7, 8) ，Alice 取走 8 枚硬币的那堆，你取走 7 枚硬币的那堆，Bob 取走最后一堆。
     * 选出 (1, 2, 4) , Alice 取走 4 枚硬币的那堆，你取走 2 枚硬币的那堆，Bob 取走最后一堆。
     * 你可以获得的最大硬币数目：7 + 2 = 9.
     * 考虑另外一种情况，如果选出的是 (1, 2, 8) 和 (2, 4, 7) ，你就只能得到 2 + 4 = 6 枚硬币，这不是最优解。
     * <p>
     * 示例 2：
     * 输入：piles = [2,4,5]
     * 输出：4
     * <p>
     * 示例 3：
     * 输入：piles = [9,8,7,6,5,1,2,3,4]
     * 输出：18
     * <p>
     * <p>
     * 提示：
     * 3 <= piles.length <= 10^5
     * piles.length % 3 == 0
     * 1 <= piles[i] <= 10^4
     *
     * @param args
     */
    public static void main(String[] args) {


//        int[] piles = {2, 4, 1, 2, 7, 8};

        int[] piles = {9, 8, 7, 6, 5, 1, 2, 3, 4};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < piles.length; i++) {
            list.add(piles[i]);
        }

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        System.out.println(list);

        List<Integer> alice = new ArrayList<>();
        List<Integer> my = new ArrayList<>();
        List<Integer> bob = new ArrayList<>();


        while (list.size() != 0) {
            alice.add(list.get(0));
            list.remove(0);
            my.add(list.get(0));
            list.remove(0);
            bob.add(list.get(list.size() - 1));
            list.remove(list.size() - 1);
        }


//        System.out.println(alice);
//        System.out.println(my);
//        System.out.println(bob);

        int sum = 0;
        for (int i = 0; i < my.size(); i++) {
            sum = sum + my.get(i);
        }

        System.out.println(sum);


    }
}