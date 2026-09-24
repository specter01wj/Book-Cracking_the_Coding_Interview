
package com.jinwang.chap3_threeinone;

import java.util.EmptyStackException;

public class Chap3_threeInOne {

    public static void main(String[] args) {
        Chap3_threeInOne test = new Chap3_threeInOne();

        System.out.println(">>> CTCI Chapter 3.1 - Three in One <<<\n");

        //====================================================
        // Solution 1
        //====================================================
        System.out.println("========== Solution 1 : Fixed Division ==========\n");

        FixedMultiStack fixedStacks = test.new FixedMultiStack(3);

        System.out.println("Push values into all three stacks:");

        fixedStacks.push(0, 10);
        fixedStacks.push(0, 20);
        fixedStacks.push(0, 30);

        fixedStacks.push(1, 100);
        fixedStacks.push(1, 200);

        fixedStacks.push(2, 1000);

        System.out.println("Stack 0 top : " + fixedStacks.peek(0));
        System.out.println("Stack 1 top : " + fixedStacks.peek(1));
        System.out.println("Stack 2 top : " + fixedStacks.peek(2));

        System.out.println();

        System.out.println("Pop Stack 0 : " + fixedStacks.pop(0));
        System.out.println("Pop Stack 0 : " + fixedStacks.pop(0));

        System.out.println("Stack 0 top : " + fixedStacks.peek(0));

        System.out.println();

        System.out.println("Stack 0 size : " + fixedStacks.size(0));
        System.out.println("Stack 1 size : " + fixedStacks.size(1));
        System.out.println("Stack 2 size : " + fixedStacks.size(2));

        System.out.println();

        System.out.println("Fill Stack 0 again:");

        fixedStacks.push(0, 40);
        fixedStacks.push(0, 50);

        System.out.println("Stack 0 top : " + fixedStacks.peek(0));
        System.out.println("Stack 0 full: " + fixedStacks.isFull(0));

        System.out.println();

        System.out.println("Try to push another value into full Stack 0:");

        try {
            fixedStacks.push(0, 60);
        } catch (IllegalStateException e) {
            System.out.println("Exception : " + e.getMessage());
        }

        System.out.println();

        //====================================================
        // Solution 2
        //====================================================
        System.out.println("========== Solution 2 : Flexible Division ==========\n");

        FlexibleMultiStack flexibleStacks =
                test.new FlexibleMultiStack(3, 2);

        System.out.println("Initial capacity:");
        flexibleStacks.printState();

        System.out.println();

        System.out.println("Fill Stack 0:");

        flexibleStacks.push(0, 10);
        flexibleStacks.push(0, 20);

        flexibleStacks.printState();

        System.out.println();

        System.out.println("Push 30 into full Stack 0.");
        System.out.println("Stack 0 expands by shifting another stack.");

        flexibleStacks.push(0, 30);

        flexibleStacks.printState();

        System.out.println();

        System.out.println("Push more values:");

        flexibleStacks.push(1, 100);
        flexibleStacks.push(1, 200);

        flexibleStacks.push(2, 1000);

        flexibleStacks.printState();

        System.out.println();

        System.out.println("Stack 0 top : " + flexibleStacks.peek(0));
        System.out.println("Stack 1 top : " + flexibleStacks.peek(1));
        System.out.println("Stack 2 top : " + flexibleStacks.peek(2));

        System.out.println();

        System.out.println("Pop Stack 0 : " + flexibleStacks.pop(0));
        System.out.println("Stack 0 top : " + flexibleStacks.peek(0));

        System.out.println();

        flexibleStacks.printState();

        System.out.println();

        System.out.println("Try to fill the remaining shared space:");

        flexibleStacks.push(2, 2000);

        flexibleStacks.printState();

        System.out.println();

        System.out.println("Try to push when entire array is full:");

        try {
            flexibleStacks.push(2, 3000);
        } catch (IllegalStateException e) {
            System.out.println("Exception : " + e.getMessage());
        }

        System.out.println();

        System.out.println("Final tops:");

        System.out.println("Stack 0 : " + flexibleStacks.peek(0));
        System.out.println("Stack 1 : " + flexibleStacks.peek(1));
        System.out.println("Stack 2 : " + flexibleStacks.peek(2));

        System.out.println();

        System.out.println("Study Complete.");
    }
    
    //====================================================
    // Solution 1 (Book)
    // Fixed Division
    //====================================================

    class FixedMultiStack {

        private static final int NUMBER_OF_STACKS = 3;

        private final int stackCapacity;

        private final int[] values;
        private final int[] sizes;

        public FixedMultiStack(int stackSize) {

            if (stackSize <= 0) {
                throw new IllegalArgumentException(
                        "Stack size must be greater than 0."
                );
            }

            stackCapacity = stackSize;

            values = new int[stackSize * NUMBER_OF_STACKS];
            sizes = new int[NUMBER_OF_STACKS];
        }

        public void push(int stackNum, int value) {

            validateStackNum(stackNum);

            if (isFull(stackNum)) {
                throw new IllegalStateException(
                        "Stack " + stackNum + " is full."
                );
            }

            sizes[stackNum]++;

            values[indexOfTop(stackNum)] = value;
        }

        public int pop(int stackNum) {

            validateStackNum(stackNum);

            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }

            int topIndex = indexOfTop(stackNum);

            int value = values[topIndex];

            values[topIndex] = 0;

            sizes[stackNum]--;

            return value;
        }

        public int peek(int stackNum) {

            validateStackNum(stackNum);

            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }

            return values[indexOfTop(stackNum)];
        }

        public boolean isEmpty(int stackNum) {

            validateStackNum(stackNum);

            return sizes[stackNum] == 0;
        }

        public boolean isFull(int stackNum) {

            validateStackNum(stackNum);

            return sizes[stackNum] == stackCapacity;
        }

        public int size(int stackNum) {

            validateStackNum(stackNum);

            return sizes[stackNum];
        }

        private int indexOfTop(int stackNum) {

            int offset = stackNum * stackCapacity;

            int size = sizes[stackNum];

            return offset + size - 1;
        }

        private void validateStackNum(int stackNum) {

            if (stackNum < 0 || stackNum >= NUMBER_OF_STACKS) {

                throw new IllegalArgumentException(
                        "Stack number must be 0, 1, or 2."
                );
            }
        }
    }


    //====================================================
    // Solution 2 (Book)
    // Flexible Division
    //====================================================

    class FlexibleMultiStack {

        class StackInfo {

            int start;
            int size;
            int capacity;

            StackInfo(int start, int capacity) {

                this.start = start;
                this.capacity = capacity;
            }

            public boolean isWithinStackCapacity(int index) {

                if (index < 0 || index >= values.length) {
                    return false;
                }

                int contiguousIndex =
                        index < start
                                ? index + values.length
                                : index;

                int end = start + capacity;

                return start <= contiguousIndex
                        && contiguousIndex < end;
            }

            public int lastCapacityIndex() {

                return adjustIndex(
                        start + capacity - 1
                );
            }

            public int lastElementIndex() {

                return adjustIndex(
                        start + size - 1
                );
            }

            public boolean isFull() {

                return size == capacity;
            }

            public boolean isEmpty() {

                return size == 0;
            }
        }


        private final StackInfo[] info;

        private final int[] values;


        public FlexibleMultiStack(
                int numberOfStacks,
                int defaultSize) {

            if (numberOfStacks <= 0 || defaultSize <= 0) {

                throw new IllegalArgumentException(
                        "Number of stacks and stack size must be greater than 0."
                );
            }

            info = new StackInfo[numberOfStacks];

            for (int i = 0; i < numberOfStacks; i++) {

                info[i] = new StackInfo(
                        defaultSize * i,
                        defaultSize
                );
            }

            values = new int[numberOfStacks * defaultSize];
        }


        public void push(int stackNum, int value) {

            validateStackNum(stackNum);

            if (allStacksAreFull()) {

                throw new IllegalStateException(
                        "All stacks are full."
                );
            }

            StackInfo stack = info[stackNum];

            if (stack.isFull()) {
                expand(stackNum);
            }

            stack.size++;

            values[stack.lastElementIndex()] = value;
        }


        public int pop(int stackNum) {

            validateStackNum(stackNum);

            StackInfo stack = info[stackNum];

            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }

            int topIndex = stack.lastElementIndex();

            int value = values[topIndex];

            values[topIndex] = 0;

            stack.size--;

            return value;
        }


        public int peek(int stackNum) {

            validateStackNum(stackNum);

            StackInfo stack = info[stackNum];

            if (stack.isEmpty()) {
                throw new EmptyStackException();
            }

            return values[stack.lastElementIndex()];
        }


        public boolean isEmpty(int stackNum) {

            validateStackNum(stackNum);

            return info[stackNum].isEmpty();
        }


        public int size(int stackNum) {

            validateStackNum(stackNum);

            return info[stackNum].size;
        }


        //====================================================
        // Expand
        //====================================================

        private void expand(int stackNum) {

            shift((stackNum + 1) % info.length);

            info[stackNum].capacity++;
        }


        //====================================================
        // Shift
        //====================================================

        private void shift(int stackNum) {

            StackInfo stack = info[stackNum];

            /*
             * If this stack is full, shift the next stack
             * first so this stack can gain one position.
             */
            if (stack.size >= stack.capacity) {

                int nextStack =
                        (stackNum + 1) % info.length;

                shift(nextStack);

                stack.capacity++;
            }

            /*
             * Shift all elements in this stack
             * one position to the right.
             */
            int index = stack.lastCapacityIndex();

            while (stack.isWithinStackCapacity(index)) {

                values[index] =
                        values[previousIndex(index)];

                index = previousIndex(index);
            }

            /*
             * Clear the old starting position.
             */
            values[stack.start] = 0;

            /*
             * Move the start of the stack
             * one position forward.
             */
            stack.start =
                    nextIndex(stack.start);

            /*
             * This stack gives one capacity position
             * to the previous stack.
             */
            stack.capacity--;
        }


        //====================================================
        // Helpers
        //====================================================

        public int numberOfElements() {

            int size = 0;

            for (StackInfo stack : info) {
                size += stack.size;
            }

            return size;
        }


        public boolean allStacksAreFull() {

            return numberOfElements() == values.length;
        }


        private int adjustIndex(int index) {

            int max = values.length;

            return ((index % max) + max) % max;
        }


        private int nextIndex(int index) {

            return adjustIndex(index + 1);
        }


        private int previousIndex(int index) {

            return adjustIndex(index - 1);
        }


        private void validateStackNum(int stackNum) {

            if (stackNum < 0 || stackNum >= info.length) {

                throw new IllegalArgumentException(
                        "Invalid stack number: " + stackNum
                );
            }
        }


        //====================================================
        // Display Helper
        //====================================================

        public void printState() {

            System.out.print("Array  : [");

            for (int i = 0; i < values.length; i++) {

                System.out.print(values[i]);

                if (i < values.length - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println("]");

            for (int i = 0; i < info.length; i++) {

                StackInfo stack = info[i];

                System.out.println(
                        "Stack " + i
                                + " -> start: " + stack.start
                                + ", size: " + stack.size
                                + ", capacity: " + stack.capacity
                );
            }
        }
    }
    
}
