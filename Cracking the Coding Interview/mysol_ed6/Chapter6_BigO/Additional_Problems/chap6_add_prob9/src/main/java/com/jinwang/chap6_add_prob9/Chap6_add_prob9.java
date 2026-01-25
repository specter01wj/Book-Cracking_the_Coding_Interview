
package com.jinwang.chap6_add_prob9;

import java.util.Arrays;

public class Chap6_add_prob9 {

    public static void main(String[] args) {
        Chap6_add_prob9 testCase1 = new Chap6_add_prob9();

        int[] original = {1, 2, 3, 4, 5};
        int[] copied = testCase1.copyArray(original);

        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Copied:   " + Arrays.toString(copied));
    }
    
    int[] copyArray(int[] array) {
        int[] copy = new int[0];
        for (int value : array) {
            copy = appendToNew(copy, value);
        }
        return copy;
    }

    int[] appendToNew(int[] array, int value) {
        int[] bigger = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            bigger[i] = array[i];
        }
        bigger[bigger.length - 1] = value;
        return bigger;
    }
}
