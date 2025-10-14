package src.test.java;
import com.algorithm.TreeNode;

import java.util.*;
public class SumTreeTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(TreeNode.sumTree(root));      // 6
        System.out.println(TreeNode.sumTreeRec(root));   // 6
        System.out.println(TreeNode.sumTree(null));      // 0
    }
}