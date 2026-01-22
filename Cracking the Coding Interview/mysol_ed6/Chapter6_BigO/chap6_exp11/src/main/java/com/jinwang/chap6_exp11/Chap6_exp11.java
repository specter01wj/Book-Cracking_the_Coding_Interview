
package com.jinwang.chap6_exp11;


public class Chap6_exp11 {

    public static void main(String[] args) {
        Chap6_exp11 testCase1 = new Chap6_exp11();

        int[] testValues = {0, 1, 3, 5, 7};

        for (int n : testValues) {
            System.out.println(n + "! = " + testCase1.factorial(n));
        }
    }
    
    int factorial(int n) {
        if (n < 0) {
            return -1; // error case
        } else if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
