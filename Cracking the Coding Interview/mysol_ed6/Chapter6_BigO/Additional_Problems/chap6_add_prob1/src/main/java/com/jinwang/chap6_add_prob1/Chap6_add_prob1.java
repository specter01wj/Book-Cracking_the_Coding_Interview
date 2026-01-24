
package com.jinwang.chap6_add_prob1;

public class Chap6_add_prob1 {

    public static void main(String[] args) {
        Chap6_add_prob1 testCase1 = new Chap6_add_prob1();

        int a = 5;
        int b = 4;

        int result = testCase1.product(a, b);
        System.out.println(a + " * " + b + " = " + result);
    }
    
    int product(int a, int b) {
        int sum = 0;
        for (int i = 0; i < b; i++) {
            sum += a;
        }
        return sum;
    }
}
