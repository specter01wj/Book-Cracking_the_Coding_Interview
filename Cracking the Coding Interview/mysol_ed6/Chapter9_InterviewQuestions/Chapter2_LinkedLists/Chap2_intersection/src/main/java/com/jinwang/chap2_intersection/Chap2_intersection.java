
package com.jinwang.chap2_intersection;

public class Chap2_intersection {

    public static void main(String[] args) {
        Chap2_intersection solution = new Chap2_intersection();

        System.out.println(">>> CTCI Chapter 2.7 - Intersection <<<\n");

        // Test 1: Different lengths, intersection in the middle
        LinkedListNode shared1 = solution.createList(7, 2, 1);

        LinkedListNode list1 = solution.createList(3, 1, 5, 9);
        solution.getLastNode(list1).next = shared1;

        LinkedListNode list2 = solution.createList(4, 6);
        solution.getLastNode(list2).next = shared1;

        solution.runTest(
                "Test 1: Different lengths",
                list1,
                list2,
                shared1
        );

        // Test 2: Equal lengths, intersection in the middle
        LinkedListNode shared2 = solution.createList(8, 10);

        LinkedListNode list3 = solution.createList(1, 2);
        solution.getLastNode(list3).next = shared2;

        LinkedListNode list4 = solution.createList(3, 4);
        solution.getLastNode(list4).next = shared2;

        solution.runTest(
                "Test 2: Equal lengths",
                list3,
                list4,
                shared2
        );

        // Test 3: Intersection at the first node
        LinkedListNode sameList = solution.createList(11, 12, 13);

        solution.runTest(
                "Test 3: Same head",
                sameList,
                sameList,
                sameList
        );

        // Test 4: Intersection only at the tail
        LinkedListNode sharedTail = new LinkedListNode(99);

        LinkedListNode list5 = solution.createList(1, 2, 3);
        solution.getLastNode(list5).next = sharedTail;

        LinkedListNode list6 = solution.createList(4, 5);
        solution.getLastNode(list6).next = sharedTail;

        solution.runTest(
                "Test 4: Intersection at tail",
                list5,
                list6,
                sharedTail
        );

        // Test 5: Same values, but different node references
        LinkedListNode list7 = solution.createList(1, 2, 3);
        LinkedListNode list8 = solution.createList(1, 2, 3);

        solution.runTest(
                "Test 5: Same values, different references",
                list7,
                list8,
                null
        );

        // Test 6: No intersection
        LinkedListNode list9 = solution.createList(1, 2, 3);
        LinkedListNode list10 = solution.createList(4, 5, 6);

        solution.runTest(
                "Test 6: No intersection",
                list9,
                list10,
                null
        );

        // Test 7: First list is null
        LinkedListNode list11 = solution.createList(1, 2, 3);

        solution.runTest(
                "Test 7: First list is null",
                null,
                list11,
                null
        );

        // Test 8: Both lists are null
        solution.runTest(
                "Test 8: Both lists are null",
                null,
                null,
                null
        );
    }
    
    
}
