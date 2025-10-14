package src.test.java;
import com.algorithm.TreeNode;

import java.util.*;
public class FindBFSTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(5), new TreeNode(15));
        System.out.println(TreeNode.findBFS(root, 5));   // true
        System.out.println(TreeNode.findBFS(root, 12));  // false
    }
}