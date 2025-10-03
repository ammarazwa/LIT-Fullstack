import java.util.ArrayList;
import java.util.Collections;
  
 public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> shoppingList = new ArrayList<>();
        shoppingList.add("Milk");
        shoppingList.add("Bread");
        shoppingList.add("Eggs");
        System.out.println("Original shopping list: " + shoppingList);
        shoppingList.add(1, "Butter"); 
        System.out.println("Updated shopping list: " + shoppingList);
        shoppingList.remove("Bread");
        System.out.println("List after removing Bread: " + shoppingList);
        Collections.sort(shoppingList);
        System.out.println("Sorted shopping list: " + shoppingList);
     }
} 
    

