package com.zhenmei.leetcode;

import java.util.*;

public class WordLadder3 {
    // 是否相差一个字母
    private static boolean isOne(String s1, String s2) {
        if (s1.equals(s2)) {
            return false;
        }

        int count = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }

        }

        if (count == 1) {
            return true;
        } else {
            return false;
        }


    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }
        Map<String, Integer> visited = new HashMap<>();
        visited.put(beginWord, 1);
        Queue<String> queue = new LinkedList();
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int level = visited.get(word);
            for (int i = 0; i < wordList.size(); i++) {
                String anotherWord = wordList.get(i);
                if (visited.get(anotherWord) == null) {
                    if (isOne(word, anotherWord)) {
                        if (anotherWord.equals(endWord)) {
                            queue.clear();
                            System.out.println(visited);
                            return level + 1;
                        }
                        visited.put(anotherWord, level + 1);
                        queue.add(anotherWord);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
//        String beginWord = "hit";
//        String endWord = "cog";
//
//        List<String> wordList = new ArrayList<>();
//        wordList.add("hot");
//        wordList.add("dot");
//        wordList.add("dog");
//        wordList.add("lot");
//        wordList.add("log");
//        wordList.add("cog");

        String beginWord = "leet";
        String endWord = "code";

        List<String> wordList = new ArrayList<>();
        wordList.add("lest");
        wordList.add("leet");
        wordList.add("lose");
        wordList.add("code");
        wordList.add("lode");
        wordList.add("robe");
        wordList.add("lost");


//        System.out.println(isOne("lest", "code"));
        System.out.println(new WordLadder3().ladderLength(beginWord, endWord, wordList));
    }


}
