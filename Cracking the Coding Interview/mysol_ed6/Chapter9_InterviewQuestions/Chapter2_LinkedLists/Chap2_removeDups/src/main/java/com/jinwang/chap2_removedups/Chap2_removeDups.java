
package com.jinwang.chap2_removedups;

import java.util.HashSet;

public class Chap2_removeDups {

    public static void main(String[] args) {
        
        Chap2_removeDups test = new Chap2_removeDups();

        System.out.println(">>> CTCI Chapter 2.1 - Remove Dups <<<\n");

        System.out.println("========== Solution 1 : deleteDups (HashSet) ==========\n");

        LinkedListNode list1 = test.buildList(1, 2, 3, 2, 4, 3, 5, 1);
        System.out.print("Original : ");
        test.printList(list1);
        test.deleteDups(list1);
        System.out.print("Result   : ");
        test.printList(list1);

        LinkedListNode list2 = test.buildList(1, 1, 1, 1, 1);
        System.out.print("\nOriginal : ");
        test.printList(list2);
        test.deleteDups(list2);
        System.out.print("Result   : ");
        test.printList(list2);

        LinkedListNode list3 = test.buildList(1, 2, 3, 4, 5);
        System.out.print("\nOriginal : ");
        test.printList(list3);
        test.deleteDups(list3);
        System.out.print("Result   : ");
        test.printList(list3);

        LinkedListNode list4 = test.buildList(5);
        System.out.print("\nOriginal : ");
        test.printList(list4);
        test.deleteDups(list4);
        System.out.print("Result   : ");
        test.printList(list4);
        
        LinkedListNode list5 = test.buildList();
        System.out.print("\nOriginal : ");
        test.printList(list5);
        test.deleteDups(list5);
        System.out.print("Result   : ");
        test.printList(list5);
        
        LinkedListNode list6 = test.buildList(-1, 3, -1, 4, 3, 5, 5);
        System.out.print("\nOriginal : ");
        test.printList(list6);
        test.deleteDups(list6);
        System.out.print("Result   : ");
        test.printList(list6);

        System.out.println("\n========== Solution 2 : deleteDupsNoBuffer ==========\n");

        LinkedListNode list7 = test.buildList(1, 2, 3, 2, 4, 3, 5, 1);
        System.out.print("Original : ");
        test.printList(list7);
        test.deleteDupsNoBuffer(list7);
        System.out.print("Result   : ");
        test.printList(list7);

        LinkedListNode list8 = test.buildList(1, 1, 1, 1, 1);
        System.out.print("\nOriginal : ");
        test.printList(list8);
        test.deleteDupsNoBuffer(list8);
        System.out.print("Result   : ");
        test.printList(list8);

        LinkedListNode list9 = test.buildList(1, 2, 3, 4, 5);
        System.out.print("\nOriginal : ");
        test.printList(list9);
        test.deleteDupsNoBuffer(list9);
        System.out.print("Result   : ");
        test.printList(list9);

        System.out.println("\nStudy Complete.");
    }
    
    // Book solution
    public void deleteDups(LinkedListNode n) {
        HashSet<Integer> set = new HashSet<>();
        LinkedListNode previous = null;

        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    // Follow-up (No Buffer)
    public void deleteDupsNoBuffer(LinkedListNode head) {
        LinkedListNode current = head;

        while (current != null) {
            LinkedListNode runner = current;

            while (runner.next != null) {

                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

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
