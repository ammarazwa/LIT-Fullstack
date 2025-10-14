package src.test.java;
import com.algorithm.ListNode;

public class SearchTest {
    public static void main(String[] args) {
        ListNode sorted = new ListNode(1, new ListNode(3, new ListNode(5)));
        System.out.println(ListNode.search(sorted, 3));    // true
        System.out.println(ListNode.searchRec(sorted, 3)); // true
        System.out.println(ListNode.search(sorted, 2));    // false
        System.out.println(ListNode.searchRec(sorted, 2)); // false
        System.out.println(ListNode.search(null, 1));      // false
    }
}