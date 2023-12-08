package com.tc.algorithm.easy;

import java.util.ArrayList;
import java.util.List;

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

    public static void setZeroes (int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        for (int n = 0; n < matrix.length; n++) {
            for (int m = 0; m < matrix[0].length; m++) {
                if (matrix[n][m] == 0) {
                    list.add(new int [] {n,m});
                }
            }
        }

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                int [] temp = list.get(i);
                for (int n = 0; n < matrix[0].length; n++) {
                    matrix[temp[0]][n] = 0;
                }

                for (int m = 0; m < matrix.length; m++) {
                    matrix[m][temp[1]] = 0;
                }
            }
        }
    }

    public static int [] findDiagonalOrder (int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int [] result = new int[n * m];

        for (int i = 0, idx = 0; i < n + m -1; i++) {
            //偶数对角线 ，从下往上遍历
            if (i % 2 == 0) {
                for (int x = Math.min(i, n - 1); x >= Math.max(0, i - m + 1); x--) {
                    result[idx++] = matrix[x][i - x];
                }
            } else {
                for(int x = Math.max(0,i- m +1);x <= Math.min(i,n-1); x++){
                    result[idx++] = matrix[x][i - x];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        /*int [][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
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
        }*/

        /*int [][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
        Matrix.setZeroes(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }*/

        int [][] matrix = {{1,2,3},{4,5,6},{7,8,9}};

        int [] result = Matrix.findDiagonalOrder(matrix);

        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + ",");
        }
        System.out.println();
    }
}
