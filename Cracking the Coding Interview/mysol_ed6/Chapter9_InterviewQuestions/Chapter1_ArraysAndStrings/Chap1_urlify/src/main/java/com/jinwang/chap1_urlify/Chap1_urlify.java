
package com.jinwang.chap1_urlify;


public class Chap1_urlify {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 1.3 – URLify <<<\n");

        Chap1_urlify solver = new Chap1_urlify();

        // --------------------------------------------------
        System.out.println("Step 1: urlify (in-place char array)\n");

        test("Mr John Smith    ", 13, "Mr%20John%20Smith");
        test("Hello World  ", 11, "Hello%20World");
        test("NoSpace", 7, "NoSpace");
        test(" a b   ", 4, "%20a%20b");

        System.out.println("\n✅ Observation: O(n) time, O(1) extra space.");
        System.out.println("\n>>> Study Complete: Chapter 1.3 <<<");
    }
    
    // ==================================================
    // Book solution: in-place URLify
    // ==================================================
    public void urlify(char[] str, int trueLength) {
        int spaceCount = 0;
        for (int i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        
        int index = trueLength + spaceCount * 2;
        
        if (trueLength < str.length) {
            str[trueLength] = '\0';
        }
        
        for (int i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index -= 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }
    
    // ==================================================
    // Test helper
    // ==================================================
    private static void test(String input, int trueLength, String expected) {
        char[] chars = input.toCharArray();

        Chap1_urlify solver = new Chap1_urlify();
        solver.urlify(chars, trueLength);

        String actual = new String(chars).trim();

        System.out.printf(
            "Input: %-18s | Expected: %-20s | Actual: %-20s %s%n",
            "\"" + input + "\"",
            expected,
            actual,
            expected.equals(actual) ? "✅" : "❌"
        );
    }
    
}
