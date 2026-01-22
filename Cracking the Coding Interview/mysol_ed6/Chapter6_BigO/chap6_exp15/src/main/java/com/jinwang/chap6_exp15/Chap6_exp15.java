
package com.jinwang.chap6_exp15;


public class Chap6_exp15 {

    public static void main(String[] args) {
        Chap6_exp15 testCase1 = new Chap6_exp15();

        int n = 10;
        System.out.println("Fibonacci numbers from 0 to " + n + " (with memoization):");
        testCase1.allFib(n);
    }
    
    void allFib(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            System.out.println(i + ": " + fib(i, memo));
        }
    }

    int fib(int n, int[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        if (memo[n] > 0) {
            return memo[n];
        }

        memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        return memo[n];
    }
}
