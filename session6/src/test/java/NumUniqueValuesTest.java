package src.test.java;
import com.algorithm.MyArray;

import java.util.*;
public class NumUniqueValuesTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4};
        System.out.println(MyArray.numUniqueValues(arr)); // 3 (1, 3, 4)
        System.out.println(MyArray.numUniqueValues(new int[]{1,1,1,1})); // 0
    }
}