
package com.jinwang.chap6_exp16;


public class Chap6_exp16 {

    public static void main(String[] args) {
        Chap6_exp16 testCase1 = new Chap6_exp16();

        int n = 50;
        System.out.println("Powers of 2 up to " + n + ":");
        testCase1.powersOf2(n);
    }
    
    int powersOf2(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            System.out.println(1);
            return 1;
        } else {
            int prev = powersOf2(n / 2);
            int curr = prev * 2;
            System.out.println(curr);
            return curr;
        }
    }
}
