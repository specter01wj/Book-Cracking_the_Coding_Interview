
package com.jinwang.chap6_exp12;


public class Chap6_exp12 {

    public static void main(String[] args) {
        Chap6_exp12 testCase1 = new Chap6_exp12();

        String input = "abc";
        System.out.println("Permutations of \"" + input + "\":");
        testCase1.permutation(input);
    }
    
    void permutation(String str) {
        permutation(str, "");
    }

    void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }
}
