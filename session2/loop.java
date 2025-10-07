 public class loop {
    public static void main(String[] args) {
        // Standard for loop
         System.out.println("Countdown:");
        for (int i = 5; i > 0; i--) {
            System.out.println(i);
        }

         // For-each loop for iterating over an array
        String[] fruits = {"Apple", "Banana", "Orange"};
        System.out.println("\nAvailable fruits:");
         for (String fruit : fruits) {
           System.out.println(fruit);
        }
      }
}