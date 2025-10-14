package src.test.java;
import com.algorithm.ListNode;

public class ReverseTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode rev = ListNode.reverse(head);
        rev.print(); // 3 -> 2 -> 1

        ListNode single = new ListNode(42);
        ListNode.reverse(single).print(); // 42

        System.out.println(ListNode.reverse(null)); // null
    }
}