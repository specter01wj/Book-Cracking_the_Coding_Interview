
package com.jinwang.chap3_stackofplates;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Chap3_stackOfPlates {

    public static void main(String[] args) {
        Chap3_stackOfPlates test =
                new Chap3_stackOfPlates();

        System.out.println(
                ">>> CTCI Chapter 3.3 - Stack of Plates <<<\n"
        );


        //====================================================
        // Test 1
        // Push - Create Multiple Sub-Stacks
        //====================================================

        System.out.println(
                "========== Test 1 : Push / Multiple Stacks ==========\n"
        );

        SetOfStacks stacks =
                test.new SetOfStacks(3);

        for (int i = 1; i <= 10; i++) {

            System.out.println("Push : " + i);

            stacks.push(i);

            System.out.println(
                    "Stacks : " + stacks
            );
        }

        System.out.println();

        System.out.println(
                "Number of sub-stacks : "
                        + stacks.numberOfStacks()
        );

        System.out.println();


        //====================================================
        // Test 2
        // Normal Pop
        //====================================================

        System.out.println(
                "========== Test 2 : Normal Pop ==========\n"
        );

        System.out.println(
                "Before : " + stacks
        );

        System.out.println(
                "Pop : " + stacks.pop()
        );

        System.out.println(
                "After  : " + stacks
        );

        System.out.println();

        System.out.println(
                "Pop : " + stacks.pop()
        );

        System.out.println(
                "After  : " + stacks
        );

        System.out.println();


        //====================================================
        // Test 3
        // popAt(index) - Book Follow Up
        //====================================================

        System.out.println(
                "========== Test 3 : popAt(index) / Rollover ==========\n"
        );

        SetOfStacks rolloverStacks =
                test.new SetOfStacks(3);

        for (int i = 1; i <= 10; i++) {
            rolloverStacks.push(i);
        }

        System.out.println(
                "Before         : " + rolloverStacks
        );

        int removed =
                rolloverStacks.popAt(0);

        System.out.println(
                "popAt(0)       : " + removed
        );

        System.out.println(
                "After rollover : " + rolloverStacks
        );

        System.out.println();


        //====================================================
        // Test 4
        // popAt Middle Stack
        //====================================================

        System.out.println(
                "========== Test 4 : popAt Middle Stack ==========\n"
        );

        SetOfStacks middleStacks =
                test.new SetOfStacks(3);

        for (int i = 1; i <= 10; i++) {
            middleStacks.push(i);
        }

        System.out.println(
                "Before         : " + middleStacks
        );

        System.out.println(
                "popAt(1)       : "
                        + middleStacks.popAt(1)
        );

        System.out.println(
                "After rollover : " + middleStacks
        );

        System.out.println();


        //====================================================
        // Test 5
        // Pop Removes Empty Last Stack
        //====================================================

        System.out.println(
                "========== Test 5 : Remove Empty Last Stack ==========\n"
        );

        SetOfStacks smallStacks =
                test.new SetOfStacks(2);

        smallStacks.push(10);
        smallStacks.push(20);
        smallStacks.push(30);

        System.out.println(
                "Before : " + smallStacks
        );

        System.out.println(
                "Pop : " + smallStacks.pop()
        );

        System.out.println(
                "After  : " + smallStacks
        );

        System.out.println(
                "Number of sub-stacks : "
                        + smallStacks.numberOfStacks()
        );

        System.out.println();


        //====================================================
        // Test 6
        // Empty Stack
        //====================================================

        System.out.println(
                "========== Test 6 : Empty Stack ==========\n"
        );

        SetOfStacks emptyStacks =
                test.new SetOfStacks(3);

        try {

            emptyStacks.pop();

        } catch (EmptyStackException e) {

            System.out.println(
                    "Pop from empty SetOfStacks -> EmptyStackException"
            );
        }

        System.out.println();


        //====================================================
        // Test 7
        // Invalid popAt Index
        //====================================================

        System.out.println(
                "========== Test 7 : Invalid popAt Index ==========\n"
        );

        SetOfStacks invalidStacks =
                test.new SetOfStacks(3);

        invalidStacks.push(1);
        invalidStacks.push(2);
        invalidStacks.push(3);

        try {

            invalidStacks.popAt(5);

        } catch (IndexOutOfBoundsException e) {

            System.out.println(
                    "popAt(5) -> IndexOutOfBoundsException"
            );
        }

        System.out.println();

        System.out.println("Study Complete.");
    }
    
    //====================================================
    // Solution (Book)
    // SetOfStacks
    //====================================================

    class SetOfStacks {

        private final ArrayList<PlateStack> stacks;

        private final int capacity;


        public SetOfStacks(int capacity) {

            if (capacity <= 0) {

                throw new IllegalArgumentException(
                        "Capacity must be greater than 0."
                );
            }

            this.capacity = capacity;

            this.stacks =
                    new ArrayList<>();
        }


        //====================================================
        // getLastStack
        //====================================================

        private PlateStack getLastStack() {

            if (stacks.isEmpty()) {
                return null;
            }

            return stacks.get(
                    stacks.size() - 1
            );
        }


        //====================================================
        // push
        //====================================================

        public void push(int value) {

            PlateStack last =
                    getLastStack();

            /*
             * Book logic:
             *
             * Add to the last stack if it has room.
             * Otherwise create a new sub-stack.
             */
            if (last != null
                    && !last.isFull()) {

                last.push(value);

            } else {

                PlateStack stack =
                        new PlateStack(capacity);

                stack.push(value);

                stacks.add(stack);
            }
        }


        //====================================================
        // pop
        //====================================================

        public int pop() {

            PlateStack last =
                    getLastStack();

            if (last == null) {
                throw new EmptyStackException();
            }

            int value =
                    last.pop();

            /*
             * Remove the last sub-stack when
             * it becomes empty.
             */
            if (last.isEmpty()) {

                stacks.remove(
                        stacks.size() - 1
                );
            }

            return value;
        }


        //====================================================
        // popAt
        // Book Follow Up
        //====================================================

        public int popAt(int index) {

            if (index < 0
                    || index >= stacks.size()) {

                throw new IndexOutOfBoundsException(
                        "Invalid stack index: " + index
                );
            }

            return leftShift(
                    index,
                    true
            );
        }


        //====================================================
        // leftShift
        // Book Rollover Algorithm
        //====================================================

        private int leftShift(
                int index,
                boolean removeTop) {

            PlateStack stack =
                    stacks.get(index);

            int removedItem;

            /*
             * For the requested stack,
             * remove its top.
             *
             * For later stacks,
             * remove their bottom.
             */
            if (removeTop) {

                removedItem =
                        stack.pop();

            } else {

                removedItem =
                        stack.removeBottom();
            }


            /*
             * If the current sub-stack becomes empty,
             * remove it completely.
             */
            if (stack.isEmpty()) {

                stacks.remove(index);

            } else if (stacks.size() > index + 1) {

                /*
                 * Rollover:
                 *
                 * Remove the bottom element from
                 * the next stack and push it onto
                 * this stack.
                 */
                int value =
                        leftShift(
                                index + 1,
                                false
                        );

                stack.push(value);
            }

            return removedItem;
        }


        //====================================================
        // Helpers
        //====================================================

        public int numberOfStacks() {

            return stacks.size();
        }


        @Override
        public String toString() {

            return stacks.toString();
        }
    }


    //====================================================
    // Solution Helper (Book)
    // Individual Sub-Stack
    //====================================================

    class PlateStack {

        private final int capacity;

        private Node top;
        private Node bottom;

        private int size;


        public PlateStack(int capacity) {

            this.capacity = capacity;

            this.size = 0;
        }


        //====================================================
        // isFull
        //====================================================

        public boolean isFull() {

            return size == capacity;
        }


        //====================================================
        // isEmpty
        //====================================================

        public boolean isEmpty() {

            return size == 0;
        }


        //====================================================
        // join
        //====================================================

        private void join(
                Node above,
                Node below) {

            if (below != null) {
                below.above = above;
            }

            if (above != null) {
                above.below = below;
            }
        }


        //====================================================
        // push
        //====================================================

        public boolean push(int value) {

            if (size >= capacity) {
                return false;
            }

            size++;

            Node node =
                    new Node(value);

            if (size == 1) {
                bottom = node;
            }

            join(
                    node,
                    top
            );

            top = node;

            return true;
        }


        //====================================================
        // pop
        //====================================================

        public int pop() {

            if (top == null) {
                throw new EmptyStackException();
            }

            Node oldTop =
                    top;

            top =
                    top.below;

            if (top != null) {

                top.above = null;

            } else {

                bottom = null;
            }

            size--;

            return oldTop.value;
        }


        //====================================================
        // removeBottom
        //====================================================

        public int removeBottom() {

            if (bottom == null) {
                throw new EmptyStackException();
            }

            Node oldBottom =
                    bottom;

            bottom =
                    bottom.above;

            if (bottom != null) {

                bottom.below = null;

            } else {

                top = null;
            }

            size--;

            return oldBottom.value;
        }


        //====================================================
        // Display Helper
        //====================================================

        @Override
        public String toString() {

            StringBuilder result =
                    new StringBuilder("[");

            Node current =
                    bottom;

            while (current != null) {

                result.append(
                        current.value
                );

                if (current.above != null) {
                    result.append(", ");
                }

                current =
                        current.above;
            }

            result.append("]");

            return result.toString();
        }
    }


    //====================================================
    // Solution Helper (Book)
    // Doubly Linked Node
    //====================================================

    class Node {

        private final int value;

        private Node above;
        private Node below;


        public Node(int value) {

            this.value = value;
        }
    }
}
