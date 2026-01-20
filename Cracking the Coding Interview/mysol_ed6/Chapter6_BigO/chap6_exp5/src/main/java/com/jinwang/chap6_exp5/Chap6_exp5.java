
package com.jinwang.chap6_exp5;


public class Chap6_exp5 {

    public static void main(String[] args) {
        Chap6_exp5 testCase1 = new Chap6_exp5();

        int[] arrayA = {1, 2, 3};
        int[] arrayB = {4, 5};

        testCase1.printUnorderedPairs(arrayA, arrayB);
    }
    
    void printUnorderedPairs(int[] arrayA, int[] arrayB) {
        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                for (int k = 0; k < 16; k++) {
                    System.out.println(arrayA[i] + ", " + arrayB[j]);
                }
            }
        }
    }
}
