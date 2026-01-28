
package com.jinwang.chap1_permutation;

import java.util.Arrays;

public class Chap1_permutation {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 1.2 – Check Permutation <<<\n");

        Chap1_permutation solver = new Chap1_permutation();

        // --------------------------------------------------
        System.out.println("Step 1: permutationBySort (sort and compare)\n");

        test(solver.permutationBySort("abc", "bca"), true, "abc vs bca");
        test(solver.permutationBySort("abc", "abcd"), false, "length mismatch");
        test(solver.permutationBySort("God", "dog"), false, "case-sensitive");
        test(solver.permutationBySort("a b", "b a"), true, "whitespace");

        System.out.println("\n✅ Observation: O(n log n) time, O(n) space.");
        
        // --------------------------------------------------
        System.out.println("\nStep 2: permutationByCount (character count)\n");

        test(solver.permutationByCount("abc", "bca"), true, "abc vs bca");
        test(solver.permutationByCount("abc", "abcd"), false, "length mismatch");
        test(solver.permutationByCount("God", "dog"), false, "case-sensitive");
        test(solver.permutationByCount("a b", "b a"), true, "whitespace");

        System.out.println("\n✅ Observation: O(n) time, O(1) space.");

        System.out.println("\n>>> Study Complete: Chapter 1.2 <<<");
    }
    
    // ==================================================
    // Solution 1: Sort strings and compare
    // ==================================================
    public boolean permutationBySort(String s, String t) {
        if (s.length() != t.length()) return false;
        
        return sort(s).equals(sort(t));
    }
    
    private String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        
        return new String(content);
    }
    
    // ==================================================
    // Solution 2: Character count (ASCII)
    // ==================================================
    public boolean permutationByCount(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] letters = new int[128];

        for (char c : s.toCharArray()) {
            letters[c]++;
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }
        return true;
    }
    
    // ==================================================
    // Test helper
    // ==================================================
    private static void test(boolean actual, boolean expected, String label) {
        System.out.printf("Test %-20s | Expected: %-5s | Actual: %-5s %s%n",
                label,
                expected,
                actual,
                actual == expected ? "✅" : "❌");
    }
    
}
