
package com.jinwang.chap6_add_prob11;


public class Chap6_add_prob11 {

    int numChars = 26;
    
    public static void main(String[] args) {
        Chap6_add_prob11 testCase1 = new Chap6_add_prob11();

        int k = 2;
        System.out.println("Sorted strings of length " + k + ":");
        testCase1.printSortedStrings(k);
    }
    
    void printSortedStrings(int remaining) {
        printSortedStrings(remaining, "");
    }

    void printSortedStrings(int remaining, String prefix) {
        if (remaining == 0) {
            if (isInOrder(prefix)) {
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < numChars; i++) {
                char c = ithLetter(i);
                printSortedStrings(remaining - 1, prefix + c);
            }
        }
    }

    boolean isInOrder(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    char ithLetter(int i) {
        return (char) ('a' + i);
    }
}
