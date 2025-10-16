package tugas;
import tugas.ListNode;

public class InsertTest {
    public static void main(String[] args) {
        ListNode sorted = new ListNode(2, new ListNode(4));
        sorted = ListNode.insert(sorted, 3);
        sorted.print(); // 2 -> 3 -> 4

        ListNode sortedRec = new ListNode(6, new ListNode(8));
        sortedRec = ListNode.insertRec(sortedRec, 7);
        sortedRec.print(); // 6 -> 7 -> 8

        sortedRec = ListNode.insertRec(sortedRec, 2);
        sortedRec.print(); // 2 -> 6 -> 7 -> 8
    }
}