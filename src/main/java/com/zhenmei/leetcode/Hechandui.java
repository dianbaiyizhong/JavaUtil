package com.zhenmei.leetcode;

import java.util.*;

public class Hechandui {


    static class Node {

        String name;
        int score;

        public Node(String name, int score) {
            this.score = score;
            this.name = name;
        }

        public String toString() {
            return name + "__" + score;
        }


    }

    public static void main(String[] args) {



        String input = "Z";


        int value=Integer.valueOf(input.charAt(0));//97
        System.out.println(value);
    }


}
