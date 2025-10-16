package tugas;
import tugas.ListNode;

public class RemoveFirstTest {
    public static void main(String[] args) {
        ListNode sorted = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(4))));
        sorted = ListNode.removeFirst(sorted, 2);
        sorted.print(); // 1 -> 2 -> 4

        sorted = ListNode.removeFirstRec(sorted, 1);
        sorted.print(); // 2 -> 4

        sorted = ListNode.removeFirstRec(sorted, 99);
        sorted.print(); // 2 -> 4
    }
}