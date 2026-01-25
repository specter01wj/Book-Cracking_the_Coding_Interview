
package com.jinwang.chap6_add_prob12;

import java.util.Arrays;

public class Chap6_add_prob12 {

    public static void main(String[] args) {
        Chap6_add_prob12 testCase1 = new Chap6_add_prob12();

        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 3, 5, 8, 10};

        int result = testCase1.intersection(a, b);
        System.out.println("Intersection count = " + result);
    }
    
    int intersection(int[] a, int[] b) {
        Arrays.sort(b); // mergesort / quicksort under the hood

        int intersect = 0;
        for (int x : a) {
            if (Arrays.binarySearch(b, x) >= 0) {
                intersect++;
            }
        }
        return intersect;
    }
}
