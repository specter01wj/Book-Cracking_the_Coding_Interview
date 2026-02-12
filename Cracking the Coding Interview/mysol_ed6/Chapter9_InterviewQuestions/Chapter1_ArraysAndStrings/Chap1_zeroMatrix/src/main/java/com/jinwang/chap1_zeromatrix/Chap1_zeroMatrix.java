
package com.jinwang.chap1_zeromatrix;

import java.util.Arrays;

public class Chap1_zeroMatrix {

    public static void main(String[] args) {
        System.out.println(">>> CTCI Chapter 1.8 – Zero Matrix <<<\n");

        Chap1_zeroMatrix solver = new Chap1_zeroMatrix();

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        print(matrix1);

        System.out.println("Step 1: setZeros (extra arrays)\n");
        solver.setZeros(matrix1);
        print(matrix1);

        System.out.println("Step 2: setZerosOptimized (O(1) space)\n");
        solver.setZerosOptimized(matrix2);
        print(matrix2);

        System.out.println(">>> Study Complete: Chapter 1.8 <<<");
    }
    
    // ==================================================
    // Solution 1: Use boolean row & column arrays
    // ==================================================
    public void setZeros(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        // Mark rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // Nullify rows
        for (int i = 0; i < m; i++) {
            if (row[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Nullify columns
        for (int j = 0; j < n; j++) {
            if (col[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // ==================================================
    // Solution 2: Use first row/column as markers
    // ==================================================
    public void setZerosOptimized(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        boolean rowHasZero = false;
        boolean colHasZero = false;

        // Check first row
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                rowHasZero = true;
                break;
            }
        }

        // Check first column
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }

        // Use first row/col as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Nullify rows
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Nullify columns
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Nullify first row
        if (rowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Nullify first column
        if (colHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    private static void print(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
