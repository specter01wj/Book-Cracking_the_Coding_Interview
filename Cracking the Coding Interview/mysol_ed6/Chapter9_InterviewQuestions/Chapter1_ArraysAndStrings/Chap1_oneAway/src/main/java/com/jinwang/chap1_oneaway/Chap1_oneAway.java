
package com.jinwang.chap1_oneaway;


public class Chap1_oneAway {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 1.5 – One Away <<<\n");

        Chap1_oneAway solver = new Chap1_oneAway();

        // --------------------------------------------------
        System.out.println("Step 1: oneEditAway (merged logic)\n");

        test(solver.oneEditAway("pale", "ple"), true, "remove");
        test(solver.oneEditAway("pales", "pale"), true, "insert");
        test(solver.oneEditAway("pale", "bale"), true, "replace");
        test(solver.oneEditAway("pale", "bake"), false, "two edits");

        System.out.println("\nStep 2: oneEditAwaySeparated (explicit methods)\n");

        test(solver.oneEditAwaySeparated("pale", "ple"), true, "remove");
        test(solver.oneEditAwaySeparated("pales", "pale"), true, "insert");
        test(solver.oneEditAwaySeparated("pale", "bale"), true, "replace");
        test(solver.oneEditAwaySeparated("pale", "bake"), false, "two edits");

        System.out.println("\n>>> Study Complete: Chapter 1.5 <<<");
    }
    
    // ==================================================
    // Solution 2 (Book): merged logic
    // ==================================================
    public boolean oneEditAway(String first, String second) {

        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;

        while (index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (foundDifference) return false;
                foundDifference = true;

                if (s1.length() == s2.length()) {
                    index1++; // replacement
                }
            } else {
                index1++; // matching
            }
            index2++; // always move longer pointer
        }
        return true;
    }

    // ==================================================
    // Solution 1 (Book): separated logic
    // ==================================================
    public boolean oneEditAwaySeparated(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() - 1 == second.length()) {
            return oneEditInsert(second, first);
        }
        return false;
    }

    private boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) return false;
                foundDifference = true;
            }
        }
        return true;
    }

    private boolean oneEditInsert(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;

        while (index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) return false;
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    // ==================================================
    // Test helper
    // ==================================================
    private static void test(boolean actual, boolean expected, String label) {
        System.out.printf("Test %-10s | Expected: %-5s | Actual: %-5s %s%n",
                label,
                expected,
                actual,
                actual == expected ? "✅" : "❌");
    }
}
