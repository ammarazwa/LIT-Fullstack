package latihan;

public class LNode {
   int value;
   LNode next;

   public void setNext (LNode next){
    this.next = next;
   }

   public LNode getNext(){
    return next;
   }

   public LNode(int value, LNode next){
    this.value = value;
    this.next = next;
   }

   public void print(){
    LNode current = this;
    while (current.getNext() != null){
        System.out.print(current.value);
        System.out.print("->");
        current = current.next;
    }
    System.out.println("null");
   }



}
