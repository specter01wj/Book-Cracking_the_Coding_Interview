
package com.jinwang.chap2_returnkthtolast;

public class Chap2_returnKthToLast {

    public static void main(String[] args) {
        
        Chap2_returnKthToLast test = new Chap2_returnKthToLast();

        System.out.println(">>> CTCI Chapter 2.2 - Return Kth To Last <<<\n");

        LinkedListNode list = test.buildList(10, 20, 30, 40, 50, 60, 70);

        System.out.print("Linked List : ");
        test.printList(list);

        System.out.println("\n========== Solution 1 : printKthToLast ==========\n");

        for (int k = 1; k <= 8; k++) {
            System.out.print("k = " + k + " : ");
            test.printKthToLast(list, k);
        }

        System.out.println("\n========== Solution 2 : kthToLast (Recursive Wrapper) ==========\n");

        for (int k = 1; k <= 8; k++) {
            LinkedListNode node = test.kthToLast(list, k);

            if (node != null) {
                System.out.println("k = " + k + " : " + node.data);
            } else {
                System.out.println("k = " + k + " : null");
            }
        }

        System.out.println("\n========== Solution 3 : nthToLast (Two Pointers) ==========\n");

        for (int k = 1; k <= 8; k++) {
            LinkedListNode node = test.nthToLast(list, k);

            if (node != null) {
                System.out.println("k = " + k + " : " + node.data);
            } else {
                System.out.println("k = " + k + " : null");
            }
        }

        System.out.println("\n========== Edge Cases ==========\n");

        LinkedListNode single = test.buildList(100);

        System.out.print("Single Node : ");
        test.printList(single);

        System.out.println("k = 1 : " + test.nthToLast(single, 1).data);
        System.out.println("k = 2 : " + test.nthToLast(single, 2));

        LinkedListNode empty = test.buildList();

        System.out.print("\nEmpty List : ");
        test.printList(empty);

        System.out.println("k = 1 : " + test.nthToLast(empty, 1));

        System.out.println("\nStudy Complete.");
    }
    
    //====================================================
    // Solution 1 (Book)
    //====================================================
    public int printKthToLast(LinkedListNode head, int k) {

        if (head == null) {
            return 0;
        }

        int index = printKthToLast(head.next, k) + 1;

        if (index == k) {
            System.out.println(head.data);
        }

        return index;
    }

    //====================================================
    // Solution 2 (Book)
    //====================================================
    class Index {
        public int value = 0;
    }

    public LinkedListNode kthToLast(LinkedListNode head, int k) {
        Index idx = new Index();

        return kthToLast(head, k, idx);
    }

    public LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {

        if (head == null) {
            return null;
        }

        LinkedListNode node = kthToLast(head.next, k, idx);

        idx.value++;

        if (idx.value == k) {
            return head;
        }

        return node;
    }

    //====================================================
    // Solution 3 (Book - Optimal)
    //====================================================
    public LinkedListNode nthToLast(LinkedListNode head, int k) {

        if (head == null || k <= 0) {
            return null;
        }

        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        for (int i = 0; i < k; i++) {
            if (p1 == null) {
                return null;
            }
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
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

    class LinkedListNode {
        int data;
        LinkedListNode next;

        LinkedListNode(int data) {
            this.data = data;
        }
    }
}
