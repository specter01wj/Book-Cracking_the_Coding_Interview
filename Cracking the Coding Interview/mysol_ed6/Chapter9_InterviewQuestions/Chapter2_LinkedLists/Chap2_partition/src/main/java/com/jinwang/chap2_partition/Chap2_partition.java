
package com.jinwang.chap2_partition;

public class Chap2_partition {

    public static void main(String[] args) {
        
        Chap2_partition test = new Chap2_partition();

        System.out.println(">>> CTCI Chapter 2.4 - Partition <<<\n");

        System.out.println("========== Solution 1 : Stable Partition ==========\n");

        LinkedListNode list1 = test.buildList(3, 5, 8, 5, 10, 2, 1);

        System.out.print("Original : ");
        test.printList(list1);

        list1 = test.partition(list1, 5);

        System.out.println("Partition = 5");

        System.out.print("Result   : ");
        test.printList(list1);

        System.out.println("\n========== Solution 2 : Head / Tail ==========\n");

        LinkedListNode list2 = test.buildList(3, 5, 8, 5, 10, 2, 1);

        System.out.print("Original : ");
        test.printList(list2);

        list2 = test.partition2(list2, 5);

        System.out.println("Partition = 5");

        System.out.print("Result   : ");
        test.printList(list2);

        System.out.println("\n========== More Tests ==========\n");

        LinkedListNode list3 = test.buildList(1, 2, 3, 4);

        System.out.print("Original : ");
        test.printList(list3);

        list3 = test.partition(list3, 5);

        System.out.print("Result   : ");
        test.printList(list3);

        System.out.println();

        LinkedListNode list4 = test.buildList(9, 8, 7, 6);

        System.out.print("Original : ");
        test.printList(list4);

        list4 = test.partition(list4, 5);

        System.out.print("Result   : ");
        test.printList(list4);

        System.out.println();

        LinkedListNode list5 = test.buildList(5, 5, 5, 5);

        System.out.print("Original : ");
        test.printList(list5);

        list5 = test.partition(list5, 5);

        System.out.print("Result   : ");
        test.printList(list5);

        System.out.println();

        LinkedListNode list6 = test.buildList(2);

        System.out.print("Original : ");
        test.printList(list6);

        list6 = test.partition(list6, 5);

        System.out.print("Result   : ");
        test.printList(list6);

        System.out.println();

        LinkedListNode list7 = test.buildList();

        System.out.print("Original : ");
        test.printList(list7);

        list7 = test.partition(list7, 5);

        System.out.print("Result   : ");
        test.printList(list7);

        System.out.println("\nStudy Complete.");
    }
    
    //====================================================
    // Solution 1 (Book)
    //====================================================
    public LinkedListNode partition(LinkedListNode node, int x) {

        LinkedListNode beforeStart = null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

        while (node != null) {
            LinkedListNode next = node.next;
            node.next = null;

            if (node.data < x) {
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else {
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }

            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        }

        beforeEnd.next = afterStart;

        return beforeStart;
    }

    //====================================================
    // Solution 2 (Book)
    //====================================================
    public LinkedListNode partition2(LinkedListNode node, int x) {
        if (node == null) {
            return null;
        }

        LinkedListNode head = node;
        LinkedListNode tail = node;

        while (node != null) {
            LinkedListNode next = node.next;

            if (node.data < x) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }

            node = next;
        }

        tail.next = null;

        return head;
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
