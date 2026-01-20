
package com.jinwang.chap6_exp9;


public class Chap6_exp9 {

    public static void main(String[] args) {
        Chap6_exp9 testCase1 = new Chap6_exp9();

        /*
                 4
               /   \
              2     6
             / \   / \
            1   3 5   7
        */

        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(7);

        int result = testCase1.sum(root);
        System.out.println("Sum of BST nodes: " + result);
    }
    
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
    
    int sum(Node node) {
        if (node == null) {
            return 0;
        }
        return sum(node.left) + node.value + sum(node.right);
    }
}
