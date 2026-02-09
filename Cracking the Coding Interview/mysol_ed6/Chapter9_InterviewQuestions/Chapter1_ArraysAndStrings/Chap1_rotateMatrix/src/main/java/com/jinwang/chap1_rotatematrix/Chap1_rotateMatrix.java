
package com.jinwang.chap1_rotatematrix;

import java.util.Arrays;

public class Chap1_rotateMatrix {

    public static void main(String[] args) {

        System.out.println(">>> CTCI Chapter 1.7 – Rotate Matrix <<<\n");

        Chap1_rotateMatrix solver = new Chap1_rotateMatrix();

        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix4 = {
                { 1,  2,  3,  4},
                { 5,  6,  7,  8},
                { 9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println("Original 3x3:");
        print(matrix3);
        solver.rotate(matrix3);
        System.out.println("Rotated 3x3:");
        print(matrix3);

        System.out.println("\nOriginal 4x4:");
        print(matrix4);
        solver.rotate(matrix4);
        System.out.println("Rotated 4x4:");
        print(matrix4);

        System.out.println("\n>>> Study Complete: Chapter 1.7 <<<");
    }

    // ==================================================
    // Book solution: rotate matrix in place
    // ==================================================
    public boolean rotate(int[][] matrix) {

        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return false;
        }

        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }
        return true;
    }

    // ==================================================
    // Helper
    // ==================================================
    private static void print(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
