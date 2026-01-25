
package com.jinwang.chap6_add_prob6;


public class Chap6_add_prob6 {

    public static void main(String[] args) {
        Chap6_add_prob6 testCase1 = new Chap6_add_prob6();

        int n1 = 100;
        int n2 = 50;

        System.out.println("sqrt(" + n1 + ") = " + testCase1.sqrt(n1));
        System.out.println("sqrt(" + n2 + ") = " + testCase1.sqrt(n2));
    }
    
    int sqrt(int n) {
        for (int guess = 1; guess * guess <= n; guess++) {
            if (guess * guess == n) {
                return guess;
            }
        }
        return -1;
    }
}
