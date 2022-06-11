package com.zhenmei.leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> ret = new ArrayList<>();

        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> item = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                item.add(node.val);
            }
            ret.add(item);

        }

        return ret;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

        }

        return list;


    }


    public int searchInsert(int[] nums, int target) {

        if (target > nums[nums.length - 1]) {
            return nums.length;
        }

        if (target <= nums[0]) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;

        int retPos = 0;
        while (left < right) {
            int mid = left + right >> 1;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return retPos;
    }

    public static boolean isRange(int[] nums, int mid, int target) {

        if (nums[mid + 1] >= target && nums[mid - 1] <= target) {
            return true;
        } else {
            return false;
        }


    }


    public static void main(String[] args) {
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode3, null);

        TreeNode treeNode1 = new TreeNode(1, null, treeNode2);


        System.out.println(new Solution().levelOrder(treeNode1));

    }


}
