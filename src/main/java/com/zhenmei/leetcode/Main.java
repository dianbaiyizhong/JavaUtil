package com.zhenmei.leetcode;

import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        ArrayList list = new ArrayList<>();

        int[] a = {1, 2, 3, 2, 5, 1, 4, 5, 7, 2};
        list.add(2);
        list.add(list.indexOf(2),1);


        list.add(list.indexOf(2),3);

        System.out.println(list);

        Collections.reverse(list);


    }
}