
package com.jinwang.chap6_exp1;


public class Chap6_exp1 {

    public static void main(String[] args) {
        Chap6_exp1 testCase1 = new Chap6_exp1();

        int[] array = {1, 2, 3, 4};
        testCase1.foo(array);
    }
    
    void foo(int[] array) {
        int sum = 0;
        int product = 1;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        for (int i = 0; i < array.length; i++) {
            product *= array[i];
        }

        System.out.println(sum + ", " + product);
    }
}
