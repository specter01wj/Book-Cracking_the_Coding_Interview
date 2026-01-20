
package com.jinwang.chap6_exp4;


public class Chap6_exp4 {

    public static void main(String[] args) {
        Chap6_exp4 testCase1 = new Chap6_exp4();

        // Equal-size arrays
        int[] array1 = {1, 2, 3};
        int[] array2 = {2, 3, 4};
        System.out.println("Equal size arrays:");
        testCase1.printUnorderedPairs(array1, array2);

        System.out.println("----");

        // Unequal-size arrays
        int[] array3 = {1, 2};
        int[] array4 = {3, 4, 5, 6, 7};
        System.out.println("Unequal size arrays:");
        testCase1.printUnorderedPairs(array3, array4);
    }
    
    void printUnorderedPairs(int[] arrayA, int[] arrayB) {
        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                if (arrayA[i] < arrayB[j]) {
                    System.out.println(arrayA[i] + ", " + arrayB[j]);
                }
            }
        }
    }
}
