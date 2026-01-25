
package com.jinwang.chap6_add_prob10;


public class Chap6_add_prob10 {

    public static void main(String[] args) {
        Chap6_add_prob10 testCase1 = new Chap6_add_prob10();

        int n = 12345;
        int result = testCase1.sumDigits(n);
        System.out.println("Sum of digits of " + n + " = " + result);
    }
    
    int sumDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
