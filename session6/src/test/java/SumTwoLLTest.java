package src.test.java;
import com.algorithm.ListNode;

public class SumTwoLLTest {
    public static void main(String[] args) {
        ListNode h1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode h2 = new ListNode(4, new ListNode(5, new ListNode(6)));
        ListNode result = ListNode.sumTwoLL(h1, h2);
        result.print(); // Should print 5 -> 7 -> 9

        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(10, new ListNode(1));
        ListNode.sumTwoLL(l1, l2).print(); // Should print 15 -> 1
    }
}