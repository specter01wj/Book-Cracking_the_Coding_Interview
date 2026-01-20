
package com.jinwang.chap6_exp3;


public class Chap6_exp3 {

    public static void main(String[] args) {
        Chap6_exp3 testCase1 = new Chap6_exp3();

        int[] array = {1, 2, 3};
        testCase1.printUnorderedPairs(array);
    }
    
    void printUnorderedPairs(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                System.out.println(array[i] + ", " + array[j]);
            }
        }
    }
}
