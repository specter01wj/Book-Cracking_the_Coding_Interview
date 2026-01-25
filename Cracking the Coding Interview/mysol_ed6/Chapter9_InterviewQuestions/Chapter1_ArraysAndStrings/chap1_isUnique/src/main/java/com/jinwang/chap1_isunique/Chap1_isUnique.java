
package com.jinwang.chap1_isunique;

import java.util.HashMap;

public class Chap1_isUnique {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 1.1 – Is Unique <<<\n");

        Chap1_isUnique solver = new Chap1_isUnique();

        // --------------------------------------------------
        System.out.println("Step 1: Test isUniqueChars (ASCII boolean array)\n");

        test(solver.isUniqueChars("abcdef"), true, "abcdef");
        test(solver.isUniqueChars("hello"), false, "hello");
        test(solver.isUniqueChars(""), true, "empty string");
        test(solver.isUniqueChars("AaBbCc"), true, "case-sensitive ASCII");

        System.out.println("\n✅ Observation: O(n) time, O(1) space using fixed ASCII set.");

        // --------------------------------------------------
        System.out.println("\nStep 2: Test isUniqueNoDS (no additional data structures)\n");

        test(solver.isUniqueNoDS("abcdef"), true, "abcdef");
        test(solver.isUniqueNoDS("apple"), false, "apple");
        test(solver.isUniqueNoDS("a"), true, "single char");
        test(solver.isUniqueNoDS(""), true, "empty string");

        System.out.println("\n✅ Observation: O(n²) time, O(1) space.");

        // --------------------------------------------------
        System.out.println("\nStep 3: Test isUniqueBitVector (a–z only)\n");

        test(solver.isUniqueBitVector("abcdef"), true, "abcdef");
        test(solver.isUniqueBitVector("hello"), false, "hello");
        test(solver.isUniqueBitVector("xyz"), true, "xyz");

        System.out.println("\n⚠️ Note: Only valid for lowercase letters a–z.");

        // --------------------------------------------------
        System.out.println("\nStep 4: Test isUniqueHashMap (general-purpose)\n");

        test(solver.isUniqueHashMap("abcdef"), true, "abcdef");
        test(solver.isUniqueHashMap("hello"), false, "hello");
        test(solver.isUniqueHashMap("AaBbAa"), false, "mixed case duplicate");
        test(solver.isUniqueHashMap(""), true, "empty string");

        System.out.println("\n✅ Observation: O(n) time, O(n) space.");

        // --------------------------------------------------
        System.out.println("\n>>> Study Complete: Chapter 1.1 <<<");
    }
    
    // ==================================================
    // Book solution: ASCII boolean array
    // ==================================================
    public boolean isUniqueChars(String str) {
        if (str.length() > 128) return false;
        
        boolean[] seen = new boolean[128];
        
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (seen[c]) {
                return false;
            }
            seen[c] = true;
        }
        
        return true;
    }
    
    // ==================================================
    // Follow-up: no additional data structures
    // ==================================================
    public boolean isUniqueNoDS(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // ==================================================
    // Optimized: bit vector (a–z only)
    // ==================================================
    public static boolean isUniqueBitVector(String str) {
        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) != 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }
    
    // ==================================================
    // HashMap approach (general-purpose)
    // ==================================================
    public static boolean isUniqueHashMap(String str) {
        HashMap<Character, Boolean> map = new HashMap<>();

        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                return false;
            }
            map.put(c, true);
        }
        return true;
    }
    
    // ==================================================
    // Helpers
    // ==================================================
    private static void test(boolean actual, boolean expected, String input) {
        System.out.printf("Input: %-20s | Expected: %-5s | Actual: %-5s %s%n",
                input,
                expected,
                actual,
                actual == expected ? "✅" : "❌");
    }
    
}
