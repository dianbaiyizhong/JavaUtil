package com.zhenmei.leetcode;

import java.util.*;

public class Test3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();

        List<Character> list = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            list.add(input.charAt(i));
        }

        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Integer.valueOf(o1) - Integer.valueOf(o2);
            }
        });

    }
}
