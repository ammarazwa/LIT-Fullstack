package src.test.java;
import com.algorithm.ListNode;

public class KthFromLastTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(ListNode.kthFromLast(head, 1)); // 4
        System.out.println(ListNode.kthFromLast(head, 2)); // 3
        System.out.println(ListNode.kthFromLast(head, 4)); // 1
        System.out.println(ListNode.kthFromLast(head, 5)); // null
    }
}