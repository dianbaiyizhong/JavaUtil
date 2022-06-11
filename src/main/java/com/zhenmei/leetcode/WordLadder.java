package com.zhenmei.leetcode;

import java.util.*;

public class WordLadder {




    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        System.out.println(edge);
        System.out.println(wordId);

        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }















    // 是否相差一个字母
    private static boolean isOne(String s1, String s2) {
        if (s1.equals(s2)) {
            return false;
        }

        int[] table = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            table[s1.charAt(i) - 'a']++;
            table[s2.charAt(i) - 'a']--;
        }

        int count = 0;
        for (int i = 0; i < table.length; i++) {

            if (table[i] != 0) {
                count++;
            }
        }

        if (count == 2) {
            return true;
        }

        return false;
    }


    class Node {
        public List<Node> child = new ArrayList<>();

        public Node(String word) {
            this.word = word;
        }

        @Override
        public String toString() {
            return this.word;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(word, node.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word);
        }

        public String word;
    }

    public int getMaxLength(String beginWord, String endWord, List<String> wordList) {

        List<Node> nodeList = new ArrayList<>();

        List<String> words = new ArrayList<>();
        if (!wordList.contains(beginWord)) {
            words.add(beginWord);
        }
        for (int i = 0; i < wordList.size(); i++) {
            words.add(wordList.get(i));
        }


        if (!words.contains(endWord)) {
            return 0;
        }


        for (int i = 0; i < words.size(); i++) {
            Node node = new Node(words.get(i));
            nodeList.add(node);
        }


        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                if (isOne(nodeList.get(i).word, nodeList.get(j).word)) {
                    if (!nodeList.get(i).child.contains(nodeList.get(j))) {
                        nodeList.get(i).child.add(nodeList.get(j));
                    }
                }
            }
        }


        Queue<Node> queue = new LinkedList<>();
        queue.add(nodeList.get(0));

        List ret = new ArrayList();
        Set visited = new HashSet();
        visited.add(nodeList.get(0).word);
        int count = 0;
        boolean success = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.word);
            if (node.word.equals(endWord)) {
                success = true;
                ret.add(count);
                count = 0;

            } else {
                count++;
            }

            for (int i = 0; i < node.child.size(); i++) {

                if(node.child.get(i).word.equals(endWord)){
                    success = true;
                    ret.add(count);
                    count = 0;

                    break;
                } else {
                    if(!visited.contains(node.child.get(i).word)){
                        visited.add(node.child.get(i).word);
                        queue.add(node.child.get(i));
                    }

                }

            }

        }
        System.out.println(ret);


        if (success) {
            return count;
        } else {
            return 0;
        }

    }

    public static void main(String[] args) {
//        isOne("dog", "cog");

        String beginWord = "hit";
        String endWord = "cog";


        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");


//        System.out.println(new WordLadder().getMaxLength(beginWord, endWord, wordList));


        System.out.println(new WordLadder().ladderLength(beginWord,endWord,wordList));
//        int[] visited = new int[wordList.length];
//        for (int i = 0; i < wordList.length; i++) {
//
//            if (beginWord.equals(wordList[i])) {
//
//            }
//
//
//        }


//        int length = new WordLadder().ladderLength(beginWord, endWord, Arrays.asList(wordList.clone()));

//        System.out.println(length);


    }


}
