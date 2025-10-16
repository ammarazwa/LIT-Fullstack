package tugas;
import tugas.TreeNode;

public class TreeHeightTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(TreeNode.treeHeight(root)); // 1
        System.out.println(TreeNode.treeHeight(null)); // -1
    }
}