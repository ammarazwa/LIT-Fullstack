import java.util.Arrays;

public class ArrayStringExample {
    public static void main(String[] args) {
        String[] items = {
            "Terimakasih",
            "Kopi Nako",
            "Content",
            "Jumlah Bayar",
            "Footer"
        };

        System.out.println("Isi Array: " + Arrays.toString(items));

        System.out.println("Jumlah elemen: " + items.length);

        System.out.println("Daftar Elemen:");
            int no = 1;
            for (String item : items) {
                System.out.println(no + ". " + item);
                no++;
        }

    }
}
