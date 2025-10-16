package tugas;
import tugas.ListNode;

public class FindMiddleTest {
    public static void main(String[] args) {
        ListNode odd = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(ListNode.findMiddle(odd)); // 2

        ListNode even = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(ListNode.findMiddle(even)); // 2

        System.out.println(ListNode.findMiddle(null)); // null
    }
}