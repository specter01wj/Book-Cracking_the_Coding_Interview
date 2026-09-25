
package com.jinwang.chap3_sortstack;

import java.util.Stack;

public class Chap3_sortStack {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 3.5 - Sort Stack <<<");
        System.out.println();


        //====================================================
        // Test 1: Book-style example
        //====================================================

        System.out.println("Test 1: Book-style example");

        Stack<Integer> stack1 = new Stack<>();

        stack1.push(7);
        stack1.push(10);
        stack1.push(5);
        stack1.push(8);
        stack1.push(12);
        stack1.push(3);
        stack1.push(1);

        System.out.println("Before sort: " + stack1);

        sort(stack1);

        System.out.println("After sort:  " + stack1);
        System.out.println("Top: " + stack1.peek());

        System.out.print("Pop order: ");
        printPopOrder(stack1);

        System.out.println();


        //====================================================
        // Test 2: Reverse order
        //====================================================

        System.out.println("Test 2: Reverse order");

        Stack<Integer> stack2 = new Stack<>();

        stack2.push(5);
        stack2.push(4);
        stack2.push(3);
        stack2.push(2);
        stack2.push(1);

        System.out.println("Before sort: " + stack2);

        sort(stack2);

        System.out.println("After sort:  " + stack2);

        System.out.print("Pop order: ");
        printPopOrder(stack2);

        System.out.println();


        //====================================================
        // Test 3: Already sorted
        //====================================================

        System.out.println("Test 3: Already sorted");

        Stack<Integer> stack3 = new Stack<>();

        // Smallest item is already on top.
        stack3.push(1);
        stack3.push(2);
        stack3.push(3);
        stack3.push(4);
        stack3.push(5);

        System.out.println("Before sort: " + stack3);

        sort(stack3);

        System.out.println("After sort:  " + stack3);

        System.out.print("Pop order: ");
        printPopOrder(stack3);

        System.out.println();


        //====================================================
        // Test 4: Duplicate values
        //====================================================

        System.out.println("Test 4: Duplicate values");

        Stack<Integer> stack4 = new Stack<>();

        stack4.push(4);
        stack4.push(2);
        stack4.push(5);
        stack4.push(2);
        stack4.push(3);
        stack4.push(4);
        stack4.push(1);

        System.out.println("Before sort: " + stack4);

        sort(stack4);

        System.out.println("After sort:  " + stack4);

        System.out.print("Pop order: ");
        printPopOrder(stack4);

        System.out.println();


        //====================================================
        // Test 5: Negative numbers
        //====================================================

        System.out.println("Test 5: Negative numbers");

        Stack<Integer> stack5 = new Stack<>();

        stack5.push(3);
        stack5.push(-2);
        stack5.push(7);
        stack5.push(0);
        stack5.push(-5);
        stack5.push(4);

        System.out.println("Before sort: " + stack5);

        sort(stack5);

        System.out.println("After sort:  " + stack5);

        System.out.print("Pop order: ");
        printPopOrder(stack5);

        System.out.println();


        //====================================================
        // Test 6: Single element
        //====================================================

        System.out.println("Test 6: Single element");

        Stack<Integer> stack6 = new Stack<>();

        stack6.push(42);

        System.out.println("Before sort: " + stack6);

        sort(stack6);

        System.out.println("After sort:  " + stack6);

        System.out.print("Pop order: ");
        printPopOrder(stack6);

        System.out.println();


        //====================================================
        // Test 7: Empty stack
        //====================================================

        System.out.println("Test 7: Empty stack");

        Stack<Integer> stack7 = new Stack<>();

        System.out.println("Before sort: " + stack7);

        sort(stack7);

        System.out.println("After sort:  " + stack7);

        System.out.println();

        System.out.println("Study Complete.");
    }
    
    //====================================================
    // Solution
    //====================================================

    public static void sort(Stack<Integer> s) {

        Stack<Integer> r = new Stack<>();

        while (!s.isEmpty()) {

            /*
             * Insert each element in s in sorted order into r.
             */
            int tmp = s.pop();

            while (!r.isEmpty() && r.peek() > tmp) {
                s.push(r.pop());
            }

            r.push(tmp);
        }

        /*
         * Copy the elements from r back into s.
         */
        while (!r.isEmpty()) {
            s.push(r.pop());
        }
    }


    //====================================================
    // Test Helper
    //====================================================

    private static void printPopOrder(Stack<Integer> stack) {

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());

            if (!stack.isEmpty()) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }
}
