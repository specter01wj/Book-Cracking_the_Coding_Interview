
package com.jinwang.chap6_exp2;


public class Chap6_exp2 {

    public static void main(String[] args) {
        Chap6_exp2 testCase1 = new Chap6_exp2();

        int[] array = {1, 2, 3};
        testCase1.printPairs(array);
    }
    
    void printPairs(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.println(array[i] + ", " + array[j]);
            }
        }
    }
}
