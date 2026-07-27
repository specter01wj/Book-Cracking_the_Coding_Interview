
package com.jinwang.chap2_loopdetection;

public class Chap2_loopDetection {

    public static void main(String[] args) {
        Chap2_loopDetection solution = new Chap2_loopDetection();

        System.out.println(">>> CTCI Chapter 2.8 - Loop Detection <<<\n");

        // Test 1: Book example
        // A -> B -> C -> D -> E -> C
        LinkedListNode a = new LinkedListNode("A");
        LinkedListNode b = new LinkedListNode("B");
        LinkedListNode c = new LinkedListNode("C");
        LinkedListNode d = new LinkedListNode("D");
        LinkedListNode e = new LinkedListNode("E");

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c;

        solution.runTest(
                "Test 1: Book Example",
                a,
                c
        );

        // Test 2: No loop
        LinkedListNode list2 = solution.createList(1, 2, 3, 4, 5);

        solution.runTest(
                "Test 2: No Loop",
                list2,
                null
        );

        // Test 3: Loop starts at head
        LinkedListNode head3 = solution.createList(1, 2, 3, 4);

        solution.getLastNode(head3).next = head3;

        solution.runTest(
                "Test 3: Loop Starts at Head",
                head3,
                head3
        );

        // Test 4: Single node, no loop
        LinkedListNode single = new LinkedListNode(10);

        solution.runTest(
                "Test 4: Single Node",
                single,
                null
        );

        // Test 5: Single node loops to itself
        LinkedListNode selfLoop = new LinkedListNode(99);
        selfLoop.next = selfLoop;

        solution.runTest(
                "Test 5: Self Loop",
                selfLoop,
                selfLoop
        );

        // Test 6: Two-node loop
        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(2);

        n1.next = n2;
        n2.next = n1;

        solution.runTest(
                "Test 6: Two Node Loop",
                n1,
                n1
        );

        // Test 7: Loop in middle
        LinkedListNode list7 = solution.createList(1, 2, 3, 4, 5, 6);

        LinkedListNode loopStart = solution.getNode(list7, 3); // value = 4

        solution.getLastNode(list7).next = loopStart;

        solution.runTest(
                "Test 7: Loop in Middle",
                list7,
                loopStart
        );

        // Test 8: Empty list
        solution.runTest(
                "Test 8: Empty List",
                null,
                null
        );
    }
    
    LinkedListNode findBeginning(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    LinkedListNode createList(int... values) {
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

    LinkedListNode getLastNode(LinkedListNode head) {
        if (head == null) {
            return null;
        }

        LinkedListNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        return current;
    }

    LinkedListNode getNode(LinkedListNode head, int index) {
        LinkedListNode current = head;

        while (index > 0 && current != null) {
            current = current.next;
            index--;
        }

        return current;
    }

    void runTest(
            String testName,
            LinkedListNode head,
            LinkedListNode expected) {

        LinkedListNode actual = findBeginning(head);

        System.out.println(testName);
        System.out.println("Expected: " + nodeValue(expected));
        System.out.println("Actual:   " + nodeValue(actual));
        System.out.println(
                "Result:   " + (actual == expected ? "PASS" : "FAIL")
        );
        System.out.println();
    }

    String nodeValue(LinkedListNode node) {
        if (node == null) {
            return "null";
        }

        return String.valueOf(node.data);
    }
}



class LinkedListNode {
    Object data;
    LinkedListNode next;

    LinkedListNode(Object data) {
        this.data = data;
    }
}
