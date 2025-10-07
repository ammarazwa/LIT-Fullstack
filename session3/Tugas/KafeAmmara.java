import java.util.ArrayList;

record Item(String product, int harga) { } 

public class KafeAmmara {
    private final String namaKafe = "Kafe Ammara";
    private final ArrayList<Item> items = new ArrayList<>();

    public void add(String product, int harga) {                
        items.add(new Item(product, harga));
    }

    public void addFirst(String product, int harga) {          
        items.add(0, new Item(product, harga));  
    }

    public void addLast(String product, int harga) {            
        items.add(new Item(product, harga));  
    }

    public Item getFirst() {                                  
        return items.get(0); 
    }

    public Item getLast() {                                     
        return items.get(items.size() - 1);  
    }

    public Item removeFirst() {                                 
        return items.remove(0); 
    }

    public Item removeLast() {                                 
        return items.remove(items.size() - 1);  
    }

    public boolean removeByName(String name) {                  
        return items.removeIf(i -> i.product().equalsIgnoreCase(name));
    }

    public int getTotal() {
        int sum = 0;
        for (Item i : items) sum += i.harga();
        return sum;
    }

    public void printDetail() {
        System.out.println("==== " + namaKafe + " - Order ====");
        if (items.isEmpty()) {
            System.out.println("(Belum ada item)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                var it = items.get(i);
                System.out.printf("%d. %s - %d%n", i + 1, it.product(), it.harga());
            }
        }
        System.out.println("Total: " + getTotal());
        System.out.println();
    }

    public static void main(String[] args) {
        var order = new KafeAmmara();

        order.add("Kopi Susu", 18000);
        order.addLast("Roti Bakar", 15000);
        order.addLast("Es Teh", 8000);
        order.printDetail();

        order.addFirst("Air Mineral", 6000);
        System.out.println("Setelah addFirst(\"Air Mineral\"): ");
        order.printDetail();

        System.out.println("Item pertama  : " + order.getFirst());
        System.out.println("Item terakhir : " + order.getLast());
        System.out.println();

        order.removeByName("Es Teh");
        System.out.println("Setelah removeByName(\"Es Teh\"): ");
        order.printDetail();

        order.removeFirst();
        order.removeLast();
        System.out.println("Setelah removeFirst() & removeLast(): ");
        order.printDetail();

        order.add("Mie Goreng", 22000);
        var itemKe0 = order.items().get(0); 
        System.out.println("Elemen index 0 sekarang: " + itemKe0);
        order.items().set(0, new Item(itemKe0.product(), 20000));
        System.out.println("Setelah update harga index 0: ");
        order.printDetail();
    }

    public ArrayList<Item> items() { return items; }
}
