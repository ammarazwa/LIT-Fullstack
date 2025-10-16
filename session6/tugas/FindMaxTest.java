package tugas;
import tugas.ListNode;

public class FindMaxTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(7, new ListNode(3, new ListNode(8, new ListNode(4))));
        System.out.println(ListNode.findMax(head)); // Should print 8
        System.out.println(ListNode.findMaxRec(head)); // Should print 8

        ListNode oneElem = new ListNode(42);
        System.out.println(ListNode.findMax(oneElem)); // Should print 42
        System.out.println(ListNode.findMaxRec(oneElem)); // Should print 42

        try {
            ListNode.findMax(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}