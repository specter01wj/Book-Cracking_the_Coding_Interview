
package com.jinwang.chap2_palindrome;

import java.util.Stack;

public class Chap2_palindrome {

    public static void main(String[] args) {
        
        Chap2_palindrome test = new Chap2_palindrome();

        LinkedListNode[] tests = {

                test.buildList(),

                test.buildList(1),

                test.buildList(1, 1),

                test.buildList(1, 2),

                test.buildList(1, 2, 1),

                test.buildList(1, 2, 2, 1),

                test.buildList(1, 2, 3, 2, 1),

                test.buildList(1, 2, 3, 4, 1),

                test.buildList(0, 1, 2, 1, 0),

                test.buildList(5, 4, 4, 5),

                test.buildList(9, 8, 7, 8, 9),

                test.buildList(1, 2, 3, 4, 5)

        };

        System.out.println(">>> CTCI Chapter 2.6 - Palindrome <<<\n");

        //====================================================
        // Solution 1
        //====================================================
        System.out.println("========== Solution 1 : Reverse and Compare ==========\n");

        for (LinkedListNode head : tests) {
            System.out.println("List   : " + test.listToString(head));
            System.out.println("Result : " + test.isPalindrome(head));
            System.out.println();
        }

        //====================================================
        // Solution 2
        //====================================================
        System.out.println("========== Solution 2 : Stack ==========\n");

        for (LinkedListNode head : tests) {
            System.out.println("List   : " + test.listToString(head));
            System.out.println("Result : " + test.isPalindrome2(head));
            System.out.println();
        }

        //====================================================
        // Solution 3
        //====================================================
        System.out.println("========== Solution 3 : Recursive ==========\n");

        for (LinkedListNode head : tests) {
            System.out.println("List   : " + test.listToString(head));
            System.out.println("Result : " + test.isPalindrome3(head));
            System.out.println();
        }

        System.out.println("Study Complete.");
    }
    
    static class LinkedListNode {
        int data;
        LinkedListNode next;

        LinkedListNode(int data) {
            this.data = data;
        }
    }

    static class Result {
        LinkedListNode node;
        boolean result;

        Result(LinkedListNode node, boolean result) {
            this.node = node;
            this.result = result;
        }
    }

    //====================================================
    // Helpers
    //====================================================
    public LinkedListNode buildList(int... values) {
        if (values.length == 0) {
            return null;
        }

        LinkedListNode head = new LinkedListNode(values[0]);
        LinkedListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new LinkedListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    public String listToString(LinkedListNode head) {
        if (head == null) {
            return "Empty";
        }

        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append(head.data);

            if (head.next != null) {
                sb.append(" -> ");
            }

            head = head.next;
        }

        return sb.toString();
    }

    //====================================================
    // Solution 1 (Book)
    // Reverse and Compare
    //====================================================
    public boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);

        return isEqual(head, reversed);
    }

    private LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;

        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }

        return head;
    }

    private boolean isEqual(
            LinkedListNode one,
            LinkedListNode two) {

        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }

            one = one.next;
            two = two.next;
        }

        return one == null && two == null;
    }
    
        //====================================================
    // Solution 2 (Book)
    // Iterative Using Stack
    //====================================================

    public boolean isPalindrome2(LinkedListNode head) {

        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack<>();

        while (fast != null && fast.next != null) {

            stack.push(slow.data);

            slow = slow.next;
            fast = fast.next.next;
        }

        // Odd number of nodes, skip the middle node.
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {

            int top = stack.pop();

            if (top != slow.data) {
                return false;
            }

            slow = slow.next;
        }

        return true;
    }

    //====================================================
    // Solution 3 (Book)
    // Recursive
    //====================================================

    public boolean isPalindrome3(LinkedListNode head) {

        int length = lengthOfList(head);

        Result p = isPalindromeRecurse(head, length);

        return p.result;
    }

    private Result isPalindromeRecurse(
            LinkedListNode head,
            int length) {

        if (head == null || length <= 0) {

            // Even number of nodes.
            return new Result(head, true);

        } else if (length == 1) {

            // Odd number of nodes.
            return new Result(head.next, true);
        }

        Result res = isPalindromeRecurse(head.next, length - 2);

        if (!res.result || res.node == null) {
            return res;
        }

        res.result = (head.data == res.node.data);

        res.node = res.node.next;

        return res;
    }

    private int lengthOfList(LinkedListNode head) {

        int size = 0;

        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }
    
}
