package src.test.java;
import com.algorithm.MyArray;

import java.util.Arrays;
public class MergeSortTest {
    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 1};
        System.out.println(Arrays.toString(MyArray.mergeSort(arr))); // [1, 3, 4, 5]
    }
}