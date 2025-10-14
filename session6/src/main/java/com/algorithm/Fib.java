package com.algorithm;

public class Fib {
    public static int fib(int k) {
        if (k <= 1) return 1;
        int prev = 1, curr = 1;
        for (int i = 2; i <= k; i++) {
            int temp = curr;
            curr = prev + curr;
            prev = temp;
        }
        return curr;
    }
}
