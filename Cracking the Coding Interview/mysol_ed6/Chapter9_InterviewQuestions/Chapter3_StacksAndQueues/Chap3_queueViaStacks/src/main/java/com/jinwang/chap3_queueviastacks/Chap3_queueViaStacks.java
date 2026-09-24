
package com.jinwang.chap3_queueviastacks;

import java.util.EmptyStackException;
import java.util.Stack;

public class Chap3_queueViaStacks {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 3.4 - Queue via Stacks <<<");
        System.out.println();

        //====================================================
        // Test 1: Basic FIFO behavior
        //====================================================

        System.out.println("Test 1: Basic FIFO behavior");

        MyQueue<Integer> queue1 = new MyQueue<>();

        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        queue1.add(4);
        queue1.add(5);

        System.out.println("Queue size: " + queue1.size());
        System.out.println("Peek: " + queue1.peek());

        System.out.println("Remove: " + queue1.remove());
        System.out.println("Remove: " + queue1.remove());
        System.out.println("Remove: " + queue1.remove());

        System.out.println("Queue size: " + queue1.size());
        System.out.println();


        //====================================================
        // Test 2: Add after remove
        //====================================================

        System.out.println("Test 2: Add after remove");

        MyQueue<Integer> queue2 = new MyQueue<>();

        queue2.add(10);
        queue2.add(20);
        queue2.add(30);

        System.out.println("Remove: " + queue2.remove()); // 10

        queue2.add(40);
        queue2.add(50);

        System.out.println("Remove: " + queue2.remove()); // 20
        System.out.println("Remove: " + queue2.remove()); // 30
        System.out.println("Remove: " + queue2.remove()); // 40
        System.out.println("Remove: " + queue2.remove()); // 50

        System.out.println();


        //====================================================
        // Test 3: Peek should not remove
        //====================================================

        System.out.println("Test 3: Peek should not remove");

        MyQueue<Integer> queue3 = new MyQueue<>();

        queue3.add(100);
        queue3.add(200);
        queue3.add(300);

        System.out.println("Size before peek: " + queue3.size());
        System.out.println("Peek: " + queue3.peek());
        System.out.println("Peek again: " + queue3.peek());
        System.out.println("Size after peek: " + queue3.size());

        System.out.println("Remove: " + queue3.remove());

        System.out.println();


        //====================================================
        // Test 4: Interleaved operations
        //====================================================

        System.out.println("Test 4: Interleaved operations");

        MyQueue<Integer> queue4 = new MyQueue<>();

        queue4.add(1);
        queue4.add(2);

        System.out.println("Remove: " + queue4.remove()); // 1

        queue4.add(3);
        queue4.add(4);

        System.out.println("Remove: " + queue4.remove()); // 2

        queue4.add(5);

        System.out.println("Remove: " + queue4.remove()); // 3
        System.out.println("Remove: " + queue4.remove()); // 4
        System.out.println("Remove: " + queue4.remove()); // 5

        System.out.println();


        //====================================================
        // Test 5: Generic type
        //====================================================

        System.out.println("Test 5: Generic type");

        MyQueue<String> queue5 = new MyQueue<>();

        queue5.add("A");
        queue5.add("B");
        queue5.add("C");

        System.out.println("Remove: " + queue5.remove());
        System.out.println("Remove: " + queue5.remove());
        System.out.println("Remove: " + queue5.remove());

        System.out.println();


        //====================================================
        // Test 6: Remove from empty queue
        //====================================================

        System.out.println("Test 6: Remove from empty queue");

        MyQueue<Integer> queue6 = new MyQueue<>();

        try {
            queue6.remove();
        } catch (EmptyStackException e) {
            System.out.println("Caught expected EmptyStackException.");
        }

        System.out.println();


        //====================================================
        // Test 7: Peek empty queue
        //====================================================

        System.out.println("Test 7: Peek empty queue");

        MyQueue<Integer> queue7 = new MyQueue<>();

        try {
            queue7.peek();
        } catch (EmptyStackException e) {
            System.out.println("Caught expected EmptyStackException.");
        }

        System.out.println();

        System.out.println("Study Complete.");
    }
    
    //====================================================
    // Solution
    //====================================================

    static class MyQueue<T> {

        private final Stack<T> stackNewest;
        private final Stack<T> stackOldest;

        public MyQueue() {
            stackNewest = new Stack<>();
            stackOldest = new Stack<>();
        }

        public int size() {
            return stackNewest.size() + stackOldest.size();
        }

        public void add(T value) {

            /*
             * Push onto stackNewest, which always has
             * the newest elements on top.
             */
            stackNewest.push(value);
        }

        /*
         * Move elements from stackNewest into stackOldest.
         *
         * This is done only when stackOldest is empty.
         * Moving the elements reverses their order, placing
         * the oldest element on top of stackOldest.
         */
        private void shiftStacks() {

            if (stackOldest.isEmpty()) {

                while (!stackNewest.isEmpty()) {
                    stackOldest.push(stackNewest.pop());
                }
            }
        }

        public T peek() {

            // Ensure stackOldest has the oldest element on top.
            shiftStacks();

            return stackOldest.peek();
        }

        public T remove() {

            // Ensure stackOldest has the oldest element on top.
            shiftStacks();

            return stackOldest.pop();
        }
    }
}
