
package com.jinwang.chap2_sumlists;

public class Chap2_sumLists {

    public static void main(String[] args) {
        
        Chap2_sumLists test = new Chap2_sumLists();

        System.out.println(">>> CTCI Chapter 2.5 - Sum Lists <<<\n");

        //==================================================
        // Solution 1
        //==================================================

        System.out.println("========== Solution 1 : Reverse Order ==========\n");

        LinkedListNode l1 = test.buildList(7, 1, 6);
        LinkedListNode l2 = test.buildList(5, 9, 2);

        System.out.print("List1 : ");
        test.printList(l1);

        System.out.print("List2 : ");
        test.printList(l2);

        LinkedListNode result = test.addLists(l1, l2, 0);

        System.out.print("Result: ");
        test.printList(result);

        System.out.println();

        l1 = test.buildList(9, 9, 9);
        l2 = test.buildList(1);

        System.out.print("List1 : ");
        test.printList(l1);

        System.out.print("List2 : ");
        test.printList(l2);

        result = test.addLists(l1, l2, 0);

        System.out.print("Result: ");
        test.printList(result);

        System.out.println();

        l1 = test.buildList(0);
        l2 = test.buildList(0);

        result = test.addLists(l1, l2, 0);

        System.out.print("0 + 0 : ");
        test.printList(result);

        System.out.println();

        l1 = test.buildList(1, 8);
        l2 = test.buildList(0);

        result = test.addLists(l1, l2, 0);

        System.out.print("18 + 0 : ");
        test.printList(result);

        System.out.println();

        l1 = null;
        l2 = test.buildList(5, 4);

        result = test.addLists(l1, l2, 0);

        System.out.print("Empty + List : ");
        test.printList(result);

        //==================================================
        // Solution 2
        //==================================================

        System.out.println("\n========== Solution 2 : Forward Order ==========\n");

        l1 = test.buildList(6, 1, 7);
        l2 = test.buildList(2, 9, 5);

        System.out.print("List1 : ");
        test.printList(l1);

        System.out.print("List2 : ");
        test.printList(l2);

        result = test.addLists(l1, l2);

        System.out.print("Result: ");
        test.printList(result);

        System.out.println();

        l1 = test.buildList(9, 9, 9);
        l2 = test.buildList(1);

        result = test.addLists(l1, l2);

        System.out.print("999 + 1 : ");
        test.printList(result);

        System.out.println();

        l1 = test.buildList(1, 2, 3, 4);
        l2 = test.buildList(5, 6, 7);

        result = test.addLists(l1, l2);

        System.out.print("1234 + 567 : ");
        test.printList(result);

        System.out.println();

        l1 = test.buildList(0);
        l2 = test.buildList(0);

        result = test.addLists(l1, l2);

        System.out.print("0 + 0 : ");
        test.printList(result);

        System.out.println();

        l1 = null;
        l2 = test.buildList(5, 4);

        result = test.addLists(l1, l2);

        System.out.print("Empty + List : ");
        test.printList(result);

        System.out.println("\nStudy Complete.");
    }
    
    //====================================================
    // Node
    //====================================================
    class LinkedListNode {
        int data;
        LinkedListNode next;

        LinkedListNode(int data) {
            this.data = data;
        }
    }

    //====================================================
    // Partial Sum (Book)
    //====================================================
    class PartialSum {
        LinkedListNode sum = null;
        int carry = 0;
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

    public void printList(LinkedListNode head) {
        if (head == null) {
            System.out.println("Empty");
            return;
        }

        while (head != null) {
            System.out.print(head.data);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
    }
    
    //====================================================
    // Solution 1 (Book)
    // Digits stored in reverse order
    //====================================================

    public LinkedListNode addLists(LinkedListNode l1,
                                   LinkedListNode l2,
                                   int carry) {

        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int value = carry;

        if (l1 != null) {
            value += l1.data;
        }

        if (l2 != null) {
            value += l2.data;
        }

        LinkedListNode result = new LinkedListNode(value % 10);

        if (l1 != null || l2 != null) {

            LinkedListNode more = addLists(
                    l1 == null ? null : l1.next,
                    l2 == null ? null : l2.next,
                    value >= 10 ? 1 : 0);

            result.next = more;
        }

        return result;
    }
}
