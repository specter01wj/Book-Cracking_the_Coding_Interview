
package com.jinwang.chap6_add_prob3;


public class Chap6_add_prob3 {

    public static void main(String[] args) {
        Chap6_add_prob3 testCase1 = new Chap6_add_prob3();

        int a = 17;
        int b = 5;

        int result = testCase1.mod(a, b);
        System.out.println(a + " % " + b + " = " + result);
    }
    
    int mod(int a, int b) {
        if (b <= 0) {
            return -1; // error case
        }

        int div = a / b;
        return a - div * b;
    }
}
