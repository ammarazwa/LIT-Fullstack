package tugas;
import tugas.TreeNode;

public class SearchBSTTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(5), new TreeNode(15));
        System.out.println(TreeNode.searchBST(root, 5)); // true
        System.out.println(TreeNode.searchBST(root, 11)); // false
    }
}