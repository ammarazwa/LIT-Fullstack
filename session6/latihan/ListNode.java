package latihan;

public class ListNode {
    int value;
    ListNode next;

    //constructor
     public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }


    public static int count(ListNode node) {
        int size = 0;
        while (node!=null) {
            size++;
            node=node.next;
        }
        return size;
    }

    public static int countRec(ListNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countRec(node.next);
    }


}
