
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        // Membuat HashMap dengan key = String, value = Integer
        HashMap<String, Integer> studentGrades = new HashMap<>();

        // Menambahkan data ke HashMap
        studentGrades.put("Alice", 95);
        studentGrades.put("Bob", 82);
        studentGrades.put("Charlie", 88);

        // Mengambil nilai Bob
        System.out.println("Bob's grade: " + studentGrades.get("Bob"));

        // Mengecek apakah David ada di dalam map
        if (studentGrades.containsKey("David")) {
            System.out.println("David's grade: " + studentGrades.get("David"));
        } else {
            System.out.println("David is not in the system.");
        }

        // Menampilkan semua isi HashMap (cara 1: keySet)
        System.out.println("\nAll student grades (pakai keySet):");
        for (String name : studentGrades.keySet()) {
            System.out.println(name + ": " + studentGrades.get(name));
        }

        // Menampilkan semua isi HashMap (cara 2: entrySet, lebih efisien)
        System.out.println("\nAll student grades (pakai entrySet):");
        for (var entry : studentGrades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Tambahkan David hanya kalau belum ada
        studentGrades.putIfAbsent("David", 90);

        // Update nilai Bob
        studentGrades.replace("Bob", 85);

        // Hapus Charlie
        studentGrades.remove("Charlie");

        // Print isi HashMap setelah perubahan
        System.out.println("\nUpdated student grades:");
        for (var entry : studentGrades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
 