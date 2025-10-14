package latihan;
import latihan.LNode;

public class LNodeTest {
    public static void main(String[] args) {
        LNode node1 = new LNode(10, null);
        LNode node2 = new LNode(11, null);
        LNode node3 = new LNode(13, null);

        node1.setNext(node2);
        node2.setNext(node3);
        node1.print();
    }
}
