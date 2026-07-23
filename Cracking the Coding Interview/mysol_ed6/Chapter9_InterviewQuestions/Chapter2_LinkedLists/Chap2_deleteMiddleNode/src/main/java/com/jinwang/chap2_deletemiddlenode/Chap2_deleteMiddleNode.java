
package com.jinwang.chap2_deletemiddlenode;

public class Chap2_deleteMiddleNode {

    public static void main(String[] args) {
        
        Chap2_deleteMiddleNode test = new Chap2_deleteMiddleNode();

        System.out.println(">>> CTCI Chapter 2.3 - Delete Middle Node <<<\n");

        System.out.println("========== Test 1 : Delete Middle Node ==========\n");

        LinkedListNode list1 = test.buildList('a', 'b', 'c', 'd', 'e', 'f');

        System.out.print("Original : ");
        test.printList(list1);

        LinkedListNode node = test.findNode(list1, 'c');

        System.out.println("Delete Node : " + node.data);

        System.out.println("Success : " + test.deleteNode(node));

        System.out.print("Result   : ");
        test.printList(list1);

        System.out.println("\n========== Test 2 : Delete Another Middle Node ==========\n");

        LinkedListNode list2 = test.buildList('a', 'b', 'c', 'd', 'e', 'f');

        System.out.print("Original : ");
        test.printList(list2);

        node = test.findNode(list2, 'e');

        System.out.println("Delete Node : " + node.data);

        System.out.println("Success : " + test.deleteNode(node));

        System.out.print("Result   : ");
        test.printList(list2);

        System.out.println("\n========== Test 3 : Last Node ==========\n");

        LinkedListNode list3 = test.buildList('a', 'b', 'c');

        System.out.print("Original : ");
        test.printList(list3);

        node = test.findNode(list3, 'c');

        System.out.println("Delete Node : " + node.data);

        System.out.println("Success : " + test.deleteNode(node));

        System.out.print("Result   : ");
        test.printList(list3);

        System.out.println("\n========== Test 4 : Single Node ==========\n");

        LinkedListNode list4 = test.buildList('x');

        System.out.print("Original : ");
        test.printList(list4);

        System.out.println("Success : " + test.deleteNode(list4));

        System.out.print("Result   : ");
        test.printList(list4);

        System.out.println("\n========== Test 5 : Null ==========\n");

        System.out.println("Success : " + test.deleteNode(null));

        System.out.println("\nStudy Complete.");
    }
    
    //====================================================
    // Book Solution
    //====================================================
    public boolean deleteNode(LinkedListNode n) {

        if (n == null || n.next == null) {
            return false;
        }

        LinkedListNode next = n.next;

        n.data = next.data;
        n.next = next.next;

        return true;
    }

    //====================================================
    // Helpers
    //====================================================
    public LinkedListNode buildList(char... values) {

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

    public LinkedListNode findNode(LinkedListNode head, char value) {

        while (head != null) {
            if (head.data == value) {
                return head;
            }
            head = head.next;
        }

        return null;
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
        char data;
        LinkedListNode next;
        
        LinkedListNode(char data) {
            this.data = data;
        }
    }
}
