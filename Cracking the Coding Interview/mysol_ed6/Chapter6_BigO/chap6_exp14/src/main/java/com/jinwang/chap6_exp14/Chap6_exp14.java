
package com.jinwang.chap6_exp14;


public class Chap6_exp14 {

    public static void main(String[] args) {
        Chap6_exp14 testCase1 = new Chap6_exp14();

        int n = 10;
        System.out.println("Fibonacci numbers from 0 to " + n + ":");
        testCase1.allFib(n);
    }
    
    void allFib(int n) {
        for (int i = 0; i <= n; i++) {
            System.out.println(i + ": " + fib(i));
        }
    }

    int fib(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
