package src.test.java;
import com.algorithm.TreeNode;

import java.util.*;
public class TreeMaxTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(8));
        System.out.println(TreeNode.treeMax(root));     // 8
        System.out.println(TreeNode.treeMaxRec(root));  // 8
        System.out.println(TreeNode.treeMax(null));     // Integer.MIN_VALUE
    }
}