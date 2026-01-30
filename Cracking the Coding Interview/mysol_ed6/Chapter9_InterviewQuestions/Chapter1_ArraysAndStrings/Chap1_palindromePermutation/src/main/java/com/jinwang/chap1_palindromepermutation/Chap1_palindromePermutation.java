
package com.jinwang.chap1_palindromepermutation;


public class Chap1_palindromePermutation {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 1.4 – Palindrome Permutation <<<\n");

        Chap1_palindromePermutation solver = new Chap1_palindromePermutation();

        // --------------------------------------------------
        System.out.println("Step 1: isPermutationOfPalindrome (count table)\n");

        test(solver.isPermutationOfPalindrome("Tact Coa"), true, "Tact Coa");
        test(solver.isPermutationOfPalindrome("racecar"), true, "racecar");
        test(solver.isPermutationOfPalindrome("hello"), false, "hello");
        
        // --------------------------------------------------
        System.out.println("\nStep 2: isPermutationOfPalindromeOptimized (count on the fly)\n");

        test(solver.isPermutationOfPalindromeOptimized("Tact Coa"), true, "Tact Coa");
        test(solver.isPermutationOfPalindromeOptimized("racecar"), true, "racecar");
        test(solver.isPermutationOfPalindromeOptimized("hello"), false, "hello");

        // --------------------------------------------------
        System.out.println("\nStep 3: isPermutationOfPalindromeBitVector (bit trick)\n");

        test(solver.isPermutationOfPalindromeBitVector("Tact Coa"), true, "Tact Coa");
        test(solver.isPermutationOfPalindromeBitVector("racecar"), true, "racecar");
        test(solver.isPermutationOfPalindromeBitVector("hello"), false, "hello");

        System.out.println("\n>>> Study Complete: Chapter 1.4 <<<");
    }
    
    // ==================================================
    // Solution 1: Count characters, then check odds
    // ==================================================
    public boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }
    
    private boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) return false;
                foundOdd = true;
            }
        }
        return true;
    }
    
    // ==================================================
    // Solution 2: Count odds while scanning
    // ==================================================
    public boolean isPermutationOfPalindromeOptimized(String phrase) {
        int[] table = new int[26];
        int countOdd = 0;

        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    // ==================================================
    // Solution 3: Bit vector trick
    // ==================================================
    public boolean isPermutationOfPalindromeBitVector(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    private int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                bitVector = toggle(bitVector, x);
            }
        }
        return bitVector;
    }

    private int toggle(int bitVector, int index) {
        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    private boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }
    
    // ==================================================
    // Helpers
    // ==================================================
    private int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[26];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }
    
    private int getCharNumber(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(Character.toLowerCase(c));
        if (val >= a && val <= z) {
            return val - a;
        }
        return -1;
    }

    private static void test(boolean actual, boolean expected, String label) {
        System.out.printf("Test %-15s | Expected: %-5s | Actual: %-5s %s%n",
                label,
                expected,
                actual,
                actual == expected ? "✅" : "❌");
    }
    
}
