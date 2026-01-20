
package com.jinwang.chap6_exp6;

import java.util.Arrays;

public class Chap6_exp6 {

    public static void main(String[] args) {
        Chap6_exp6 testCase1 = new Chap6_exp6();

        int[] array = {1, 2, 3, 4, 5};

        System.out.println("Before reverse: " + Arrays.toString(array));
        testCase1.reverse(array);
        System.out.println("After reverse:  " + Arrays.toString(array));
    }
    
    void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int other = array.length - i - 1;

            int temp = array[i];
            array[i] = array[other];
            array[other] = temp;
        }
    }
}
