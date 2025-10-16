package tugas;
import tugas.ListNode;

public class AppendTest {
    public static void main(String[] args) {
        ListNode list1 = null;
        list1 = ListNode.append(list1, 5);
        list1 = ListNode.append(list1, 10);
        list1 = ListNode.append(list1, 20);
        list1.print(); // Should print 5 -> 10 -> 20

        ListNode list2 = null;
        list2 = ListNode.appendRec(list2, 1);
        list2 = ListNode.appendRec(list2, 2);
        list2 = ListNode.appendRec(list2, 3);
        list2.print(); // Should print 1 -> 2 -> 3
    }
}