package tugas;
import java.util.Arrays;
import tugas.MyArray;

public class InsertionSortTest {
    public static void main(String[] args) {
        int[] arr = {9, 2, 7};
        System.out.println(Arrays.toString(MyArray.insertionSort(arr))); // [2, 7, 9]
    }
}