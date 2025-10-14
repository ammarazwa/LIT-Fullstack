package src.test.java;
import com.algorithm.MyArray;

import java.util.Arrays;
public class SelectionSortTest {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1};
        System.out.println(Arrays.toString(MyArray.selectionSort(arr))); // [1, 1, 3, 4]
    }
}