
package com.jinwang.chap6_add_prob4;


public class Chap6_add_prob4 {

    public static void main(String[] args) {
        Chap6_add_prob4 testCase1 = new Chap6_add_prob4();

        int a = 17;
        int b = 5;

        int result = testCase1.div(a, b);
        System.out.println(a + " / " + b + " = " + result);
    }
    
    int div(int a, int b) {
        int count = 0;
        int sum = b;

        while (sum <= a) {
            sum += b;
            count++;
        }
        return count;
    }
}
