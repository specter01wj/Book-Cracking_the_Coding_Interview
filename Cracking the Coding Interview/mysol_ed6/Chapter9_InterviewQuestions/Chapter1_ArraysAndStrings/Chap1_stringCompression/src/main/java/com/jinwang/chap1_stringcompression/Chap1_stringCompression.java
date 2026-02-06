
package com.jinwang.chap1_stringcompression;


public class Chap1_stringCompression {

    public static void main(String[] args) {

        System.out.println(">>> CTCI Chapter 1.6 – String Compression <<<\n");

        Chap1_stringCompression solver = new Chap1_stringCompression();

        // --------------------------------------------------
        System.out.println("Step 1: compressBad (naive string concatenation)\n");

        test(solver.compressBad("aabcccccaaa"), "a2b1c5a3");
        test(solver.compressBad("abcdef"), "abcdef");

        // --------------------------------------------------
        System.out.println("\nStep 2: compress (StringBuilder)\n");

        test(solver.compress("aabcccccaaa"), "a2b1c5a3");
        test(solver.compress("abcdef"), "abcdef");

        // --------------------------------------------------
        System.out.println("\nStep 3: compressOptimized (count first)\n");

        test(solver.compressOptimized("aabcccccaaa"), "a2b1c5a3");
        test(solver.compressOptimized("abcdef"), "abcdef");

        System.out.println("\n>>> Study Complete: Chapter 1.6 <<<");
    }

    // ==================================================
    // Solution 1 (BAD): String concatenation
    // Time: O(N^2)
    // ==================================================
    public String compressBad(String str) {
        String compressed = "";
        int countConsecutive = 0;

        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed += "" + str.charAt(i) + countConsecutive;
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed : str;
    }

    // ==================================================
    // Solution 2: StringBuilder
    // Time: O(N)
    // ==================================================
    public String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;

        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    // ==================================================
    // Solution 3: Optimized (count final length first)
    // Time: O(N)
    // ==================================================
    public String compressOptimized(String str) {
        int finalLength = countCompression(str);
        if (finalLength >= str.length()) return str;

        StringBuilder compressed = new StringBuilder(finalLength);
        int countConsecutive = 0;

        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.toString();
    }

    private int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;

        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }

    // ==================================================
    // Test helper
    // ==================================================
    private static void test(String actual, String expected) {
        System.out.printf("Expected: %-12s | Actual: %-12s %s%n",
                expected,
                actual,
                actual.equals(expected) ? "✅" : "❌");
    }
}
