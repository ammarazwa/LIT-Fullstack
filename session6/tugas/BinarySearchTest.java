package tugas;
import tugas.MyArray;

public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 7, 8};
        System.out.println(MyArray.binarySearch(arr, 4)); // 2
        System.out.println(MyArray.binarySearch(arr, 1)); // 0
        System.out.println(MyArray.binarySearch(arr, 10)); // -1
    }
}