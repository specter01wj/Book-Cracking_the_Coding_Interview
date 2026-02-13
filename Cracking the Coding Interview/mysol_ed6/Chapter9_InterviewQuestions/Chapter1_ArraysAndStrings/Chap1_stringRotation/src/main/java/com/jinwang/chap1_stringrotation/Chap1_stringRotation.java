
package com.jinwang.chap1_stringrotation;


public class Chap1_stringRotation {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 1.9 – String Rotation <<<\n");

        Chap1_stringRotation solver = new Chap1_stringRotation();

        System.out.println("Step 1: isRotation\n");

        test(solver.isRotation("waterbottle", "erbottlewat"), true);
        test(solver.isRotation("hello", "llohe"), true);
        test(solver.isRotation("hello", "lloeh"), false);
        test(solver.isRotation("abc", "cab"), true);
        test(solver.isRotation("abc", "acb"), false);

        System.out.println("\n>>> Study Complete: Chapter 1.9 <<<");
    }
    
    public boolean isRotation(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        String s1s1 = s1 + s1;

        return isSubstring(s1s1, s2);
    }

    public static boolean isSubstring(String big, String small) {

        if (big.indexOf(small) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private static void test(boolean actual, boolean expected) {

        System.out.printf("Expected: %-5s | Actual: %-5s %s%n",
                expected,
                actual,
                actual == expected ? "✅" : "❌");
    }
    
}
