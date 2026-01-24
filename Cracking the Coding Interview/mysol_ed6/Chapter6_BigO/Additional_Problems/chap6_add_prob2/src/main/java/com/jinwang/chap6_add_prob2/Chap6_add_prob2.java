
package com.jinwang.chap6_add_prob2;


public class Chap6_add_prob2 {

    public static void main(String[] args) {
        Chap6_add_prob2 testCase1 = new Chap6_add_prob2();

        int a = 2;
        int b = 5;

        int result = testCase1.power(a, b);
        System.out.println(a + " ^ " + b + " = " + result);
    }
    
    int power(int a, int b) {
        if (b < 0) {
            return 0; // error case
        } else if (b == 0) {
            return 1;
        } else {
            return a * power(a, b - 1);
        }
    }
}
