package tugas;
import tugas.TreeNode;
public class ValidateBSTTest {
    public static void main(String[] args) {
        TreeNode bst = new TreeNode(10, new TreeNode(5), new TreeNode(15));
        System.out.println(TreeNode.validateBST(bst)); // true
        TreeNode bad = new TreeNode(10, new TreeNode(12), new TreeNode(15));
        System.out.println(TreeNode.validateBST(bad)); // false
    }
}