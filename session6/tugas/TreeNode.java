package tugas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        left = null; right = null;
    }
    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static int countTree(TreeNode node) {
        if (node == null) return 0;
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            count++;
            TreeNode curr = queue.poll();
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        return count;
    }

    public static int countTreeRec(TreeNode node) {
        if (node == null) return 0;
        return 1 + countTreeRec(node.left) + countTreeRec(node.right);
    }

    public static int sumTree(TreeNode node) {
        if (node == null) return 0;
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            sum += curr.value;
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        return sum;
    }

    public static int sumTreeRec(TreeNode node) {
        if (node == null) return 0;
        return node.value + sumTreeRec(node.left) + sumTreeRec(node.right);
    }

    public static int treeMax(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        int maxValue = node.value;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            maxValue = Math.max(maxValue, curr.value);
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        return maxValue;
    }

    public static int treeMaxRec(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        return Math.max(node.value, Math.max(treeMaxRec(node.left), treeMaxRec(node.right)));
    }

    public static int treeHeight(TreeNode root) {
        if (root == null) return -1;
        return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }

    public static boolean findBFS(TreeNode node, int target) {
        if (node == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.value == target) return true;
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        return false;
    }

    public static boolean findDFS(TreeNode node, int target) {
        if (node == null) return false;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.value == target) return true;
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        return false;
    }

    public static boolean findDFSRec(TreeNode node, int target) {
        if (node == null) return false;
        if (node.value == target) return true;
        return findDFSRec(node.left, target) || findDFSRec(node.right, target);
    }

    public static boolean searchBST(TreeNode node, int target) {
        TreeNode curr = node;
        while (curr != null) {
            if (curr.value == target) return true;
            if (curr.value < target) curr = curr.right;
            else curr = curr.left;
        }
        return false;
    }

    public static TreeNode insertBST(TreeNode root, int target) {
        if (root == null) return new TreeNode(target);
        TreeNode curr = root;
        while (true) {
            if (target < curr.value) {
                if (curr.left == null) {
                    curr.left = new TreeNode(target);
                    break;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new TreeNode(target);
                    break;
                }
                curr = curr.right;
            }
        }
        return root;
    }

    public static boolean validateBST(TreeNode node) {
        return helper(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean helper(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value > max) return false;
        return helper(node.left, min, node.value) && helper(node.right, node.value, max);
    }
}