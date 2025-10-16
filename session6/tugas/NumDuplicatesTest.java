package tugas;
import tugas.MyArray;

import java.util.*;
public class NumDuplicatesTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        System.out.println(MyArray.numDuplicates(arr)); // 2 (2, 3)
        System.out.println(MyArray.numDuplicates(new int[]{1,2,3})); // 0
    }
}