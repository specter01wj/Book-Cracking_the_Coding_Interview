
package com.jinwang.chap3_stackmin;

import java.util.EmptyStackException;
import java.util.Stack;

public class Chap3_stackMin {

    public static void main(String[] args) {
        Chap3_stackMin test = new Chap3_stackMin();

        System.out.println(">>> CTCI Chapter 3.2 - Stack Min <<<\n");

        //====================================================
        // Solution 1
        //====================================================
        System.out.println("========== Solution 1 : Min in Every Node ==========\n");

        StackWithMin stack1 = test.new StackWithMin();

        System.out.println("Push 5:");
        stack1.push(5);
        System.out.println("Stack : " + stack1);
        System.out.println("Min   : " + stack1.min());

        System.out.println();

        System.out.println("Push 6:");
        stack1.push(6);
        System.out.println("Stack : " + stack1);
        System.out.println("Min   : " + stack1.min());

        System.out.println();

        System.out.println("Push 3:");
        stack1.push(3);
        System.out.println("Stack : " + stack1);
        System.out.println("Min   : " + stack1.min());

        System.out.println();

        System.out.println("Push 7:");
        stack1.push(7);
        System.out.println("Stack : " + stack1);
        System.out.println("Min   : " + stack1.min());

        System.out.println();

        System.out.println("Pop : " + stack1.pop().value);
        System.out.println("Stack : " + stack1);
        System.out.println("Min   : " + stack1.min());

        System.out.println();

        System.out.println("Pop : " + stack1.pop().value);
        System.out.println("Stack : " + stack1);
        System.out.println("Min   : " + stack1.min());

        System.out.println();

        System.out.println("Test duplicate minimums:");

        stack1.push(3);
        stack1.push(3);

        System.out.println("Stack : " + stack1);
        System.out.println("Min   : " + stack1.min());

        System.out.println();

        System.out.println("Pop : " + stack1.pop().value);
        System.out.println("Min : " + stack1.min());

        System.out.println();

        System.out.println("Pop : " + stack1.pop().value);
        System.out.println("Min : " + stack1.min());

        System.out.println();

        //====================================================
        // Solution 2
        //====================================================
        System.out.println("========== Solution 2 : Auxiliary Min Stack ==========\n");

        StackWithMin2 stack2 = test.new StackWithMin2();

        System.out.println("Push 5:");
        stack2.push(5);
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Push 6:");
        stack2.push(6);
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Push 3:");
        stack2.push(3);
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Push 7:");
        stack2.push(7);
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Pop : " + stack2.pop());
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Pop : " + stack2.pop());
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Test duplicate minimums:");

        stack2.push(3);
        stack2.push(3);

        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Pop : " + stack2.pop());
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        System.out.println("Pop : " + stack2.pop());
        System.out.println("Stack     : " + stack2);
        System.out.println("Min Stack : " + stack2.minStackToString());
        System.out.println("Min       : " + stack2.min());

        System.out.println();

        //====================================================
        // Empty Stack Test
        //====================================================
        System.out.println("========== Empty Stack Test ==========\n");

        StackWithMin2 emptyStack = test.new StackWithMin2();

        try {
            emptyStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Pop from empty stack -> EmptyStackException");
        }

        System.out.println("Min of empty stack : " + emptyStack.min());

        System.out.println();

        System.out.println("Study Complete.");
    }
    
    //====================================================
    // Solution 1 (Book)
    // Store Minimum in Every Node
    //====================================================

    class StackWithMin extends Stack<NodeWithMin> {

        public void push(int value) {

            int newMin = Math.min(
                    value,
                    min()
            );

            super.push(
                    new NodeWithMin(
                            value,
                            newMin
                    )
            );
        }

        @Override
        public synchronized NodeWithMin pop() {

            if (isEmpty()) {
                throw new EmptyStackException();
            }

            return super.pop();
        }

        public int min() {

            if (isEmpty()) {
                return Integer.MAX_VALUE;
            }

            return peek().min;
        }

        @Override
        public String toString() {

            StringBuilder result =
                    new StringBuilder("[");

            for (int i = 0; i < size(); i++) {

                result.append(get(i).value);

                if (i < size() - 1) {
                    result.append(", ");
                }
            }

            result.append("]");

            return result.toString();
        }
    }


    //====================================================
    // Solution 1 Helper
    // NodeWithMin
    //====================================================

    class NodeWithMin {

        public int value;
        public int min;

        public NodeWithMin(
                int value,
                int min) {

            this.value = value;
            this.min = min;
        }
    }


    //====================================================
    // Solution 2 (Book)
    // Auxiliary Stack for Minimums
    //====================================================

    class StackWithMin2 extends Stack<Integer> {

        private final Stack<Integer> minStack;

        public StackWithMin2() {

            minStack =
                    new Stack<>();
        }

        @Override
        public Integer push(Integer value) {

            /*
             * <= is important.
             *
             * If the new value equals the current minimum,
             * we must store the duplicate minimum too.
             */
            if (value <= min()) {
                minStack.push(value);
            }

            return super.push(value);
        }

        @Override
        public synchronized Integer pop() {

            if (isEmpty()) {
                throw new EmptyStackException();
            }

            int value =
                    super.pop();

            /*
             * If the value being removed is the
             * current minimum, remove it from
             * the min stack as well.
             */
            if (value == min()) {
                minStack.pop();
            }

            return value;
        }

        public int min() {

            if (minStack.isEmpty()) {
                return Integer.MAX_VALUE;
            }

            return minStack.peek();
        }

        public String minStackToString() {

            return minStack.toString();
        }
    }
}
