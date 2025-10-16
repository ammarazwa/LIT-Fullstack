package tugas;
import tugas.MyArray;

import java.util.*;
public class FirstKTimesTest {
    public static void main(String[] args) {
        int[] arr = {2, 1, 2, 3, 1, 2};
        System.out.println(MyArray.firstKTimes(arr, 2)); // 2
        System.out.println(MyArray.firstKTimes(arr, 3)); // 2
        System.out.println(MyArray.firstKTimes(arr, 4)); // -1
    }
}