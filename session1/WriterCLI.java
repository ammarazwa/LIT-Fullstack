import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

class MyClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama: ");
        String name = scanner.nextLine(); 

        System.out.print("Masukkan Tinggi: ");
        int height = scanner.nextInt(); 

        PrintWriter writer2 = new PrintWriter(System.out); 
        writer2.println("Nama: " + name);
        writer2.println("Tinggi Badan: " + height + " cm");
        writer2.close();

        try (PrintWriter writer = new PrintWriter("output.txt")) {
            writer.println("Nama: " + name);
            writer.println("Tinggi Badan: " + height + " cm");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }
}
