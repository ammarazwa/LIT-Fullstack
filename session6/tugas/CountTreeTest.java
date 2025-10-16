package tugas;
import tugas.TreeNode;

import java.util.*;
public class CountTreeTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(TreeNode.countTree(root));     // 3
        System.out.println(TreeNode.countTreeRec(root));  // 3
        System.out.println(TreeNode.countTree(null));     // 0
    }
}