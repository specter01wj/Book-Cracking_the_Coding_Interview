
package com.jinwang.chap6_add_prob5;


public class Chap6_add_prob5 {

    public static void main(String[] args) {
        Chap6_add_prob5 testCase1 = new Chap6_add_prob5();

        int n1 = 100;
        int n2 = 50;

        System.out.println("sqrt(" + n1 + ") = " + testCase1.sqrt(n1));
        System.out.println("sqrt(" + n2 + ") = " + testCase1.sqrt(n2));
    }
    
    int sqrt(int n) {
        return sqrt_helper(n, 1, n);
    }

    int sqrt_helper(int n, int min, int max) {
        if (max < min) {
            return -1; // no square root
        }

        int guess = (min + max) / 2;

        if (guess * guess == n) {
            return guess;
        } else if (guess * guess < n) {
            return sqrt_helper(n, guess + 1, max); // try higher
        } else {
            return sqrt_helper(n, min, guess - 1); // try lower
        }
    }
}
