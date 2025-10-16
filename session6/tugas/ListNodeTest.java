package tugas;
import tugas.ListNode;

public class ListNodeTest {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2, n1);
        ListNode n3 = new ListNode(3, n2);
        n3.print(); // should print 3 -> 2 -> 1

        ListNode single = new ListNode(42);
        single.print(); // should print 42
    }
}