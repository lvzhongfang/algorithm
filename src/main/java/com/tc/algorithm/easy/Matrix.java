package com.tc.algorithm.easy;

/**
 * desc 矩阵
 *
 * @author lvzf
 * @date 2023/12/8
 */
public class Matrix {

    /**
     * 矩阵顺时针旋转90度，使用额外的空间 ，空间复杂度O(N)
     * @param matrix 待旋转的矩阵
     * @return 旋转后的矩阵
     */
    public static int[][] rotateWithTemp (int [][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int [][] newMatrix = new int[column][row];
        int maxColumn = column - 1;
        for (int n = 0; n < row; n++) {
            for (int m = 0; m < column; m++) {
                newMatrix[m][maxColumn] = matrix[n][m];
            }
            maxColumn--;
        }
        return newMatrix;
    }

    /**
     * 矩阵顺时针旋转90度，不使用额外的空间 ，空间复杂度O(1)
     * matrix[i][j] ----------> matrix[j][n-i]
     *       |                          |
     *       |                          |
     * matrix[n-j][i] <---------- matrix[n-i][n-j]
     * @param matrix
     * @return
     */
    public static void rotate (int [][] matrix) {

        int temp = 0;
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {

        int [][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }

        Matrix.rotate(matrix);
        System.out.println("after rotate: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
    }
}
