package src.test.java;
import com.algorithm.TreeNode;


public class InsertBSTTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(5), new TreeNode(15));
        TreeNode.insertBST(root, 11);
        System.out.println(TreeNode.searchBST(root, 11)); // true
    }
}