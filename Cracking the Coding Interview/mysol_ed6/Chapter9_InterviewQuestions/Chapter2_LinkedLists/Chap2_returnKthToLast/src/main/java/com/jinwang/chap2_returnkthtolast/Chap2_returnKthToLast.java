
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
}
