package tugas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyArray {
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public static int[] mergeSort(int[] nums) {
        if (nums.length <= 1) return nums;
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private static int[] merge(int[] a, int[] b) {
        int[] out = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) out[k++] = a[i++];
            else out[k++] = b[j++];
        }
        while (i < a.length) out[k++] = a[i++];
        while (j < b.length) out[k++] = b[j++];
        return out;
    }

    public static int firstKTimes(int[] arr, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int newCount = counts.getOrDefault(arr[i], 0) + 1;
            if (newCount == k) return arr[i];
            counts.put(arr[i], newCount);
        }
        return -1;
    }

    public static int numUniqueValues(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        int numUnique = 0;
        for (int val : arr) {
            int newCount = counts.getOrDefault(val, 0) + 1;
            if (newCount == 1) numUnique++;
            if (newCount == 2) numUnique--;
            counts.put(val, newCount);
        }
        return numUnique;
    }

    public static int numDuplicates(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        int numDupes = 0;
        for (int val : arr) {
            int newCount = counts.getOrDefault(val, 0) + 1;
            if (newCount == 2) numDupes++;
            counts.put(val, newCount);
        }
        return numDupes;
    }

    public static int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }
}
