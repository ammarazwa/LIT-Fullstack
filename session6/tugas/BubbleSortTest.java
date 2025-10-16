package tugas;
import tugas.MyArray;

import java.util.Arrays;
public class BubbleSortTest {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 1};
        System.out.println(Arrays.toString(MyArray.bubbleSort(arr))); // [1, 2, 5, 7]
        int[] empty = {};
        System.out.println(Arrays.toString(MyArray.bubbleSort(empty))); // []
    }
}