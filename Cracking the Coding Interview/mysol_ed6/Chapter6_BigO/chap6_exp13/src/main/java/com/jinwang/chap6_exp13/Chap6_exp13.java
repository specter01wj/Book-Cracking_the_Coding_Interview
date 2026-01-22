
package com.jinwang.chap6_exp13;


public class Chap6_exp13 {

    public static void main(String[] args) {
        Chap6_exp13 testCase1 = new Chap6_exp13();

        int[] testValues = {0, 1, 2, 3, 5, 10};

        for (int n : testValues) {
            System.out.println("fib(" + n + ") = " + testCase1.fib(n));
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
