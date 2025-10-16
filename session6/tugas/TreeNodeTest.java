package tugas;
import tugas.TreeNode;


public class TreeNodeTest {
    public static void main(String[] args) {
        TreeNode leaf = new TreeNode(7);
        TreeNode root = new TreeNode(10, leaf, null);
        System.out.println(root.value == 10); // true
        System.out.println(root.left.value == 7); // true
        System.out.println(root.right == null); // true
    }
}