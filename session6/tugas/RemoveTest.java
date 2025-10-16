package tugas;
import tugas.ListNode;

public class RemoveTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(2, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(2)))));
        ListNode removed = ListNode.remove(head, 2);
        removed.print(); // Should print 1 -> 3

        ListNode head2 = new ListNode(5, new ListNode(5, new ListNode(5)));
        ListNode removed2 = ListNode.removeRec(head2, 5);
        if (removed2 == null) System.out.println("All removed"); // Should print All removed
    }
}