package src.test.java;
import com.algorithm.ListNode;

public class SumLLTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(ListNode.sumLL(head));     // Should print 6
        System.out.println(ListNode.sumLLRec(head));  // Should print 6
        System.out.println(ListNode.sumLL(null));     // Should print 0
        System.out.println(ListNode.sumLLRec(null));  // Should print 0
    }
}