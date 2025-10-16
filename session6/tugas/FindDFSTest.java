package tugas;
import tugas.TreeNode;

import java.util.*;
public class FindDFSTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(5), new TreeNode(15));
        System.out.println(TreeNode.findDFS(root, 15));    // true
        System.out.println(TreeNode.findDFSRec(root, 100)); // false
    }
}