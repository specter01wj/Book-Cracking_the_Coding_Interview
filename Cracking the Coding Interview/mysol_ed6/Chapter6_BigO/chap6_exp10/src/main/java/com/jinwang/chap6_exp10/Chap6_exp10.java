
package com.jinwang.chap6_exp10;


public class Chap6_exp10 {

    public static void main(String[] args) {
        Chap6_exp10 testCase1 = new Chap6_exp10();

        int[] testNumbers = {1, 2, 3, 4, 9, 17, 33, 97};

        for (int n : testNumbers) {
            System.out.println(n + " is prime? " + testCase1.isPrime(n));
        }
    }
    
    boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int x = 2; x * x <= n; x++) {
            if (n % x == 0) {
                return false;
            }
        }
        return true;
    }
}
