
package com.jinwang.chap1_isunique;


public class Chap1_isUnique {

    public static void main(String[] args) {
        Chap1_isUnique testCase1 = new Chap1_isUnique();
        
        System.out.println(testCase1.isUniqueChars("abcdef"));
        System.out.println(testCase1.isUniqueChars("hello")); 
        System.out.println(testCase1.isUniqueChars("")); 
        
        System.out.println(testCase1.isUniqueNoDS("abcdef")); 
        System.out.println(testCase1.isUniqueNoDS("apple")); 
    }
    
    // Follow-up: no additional data structures
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
    
}
