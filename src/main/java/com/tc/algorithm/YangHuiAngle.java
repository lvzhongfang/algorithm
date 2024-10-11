package com.tc.algorithm;

/**
 * desc 杨辉三角
 * f (i, j)函数表示杨辉三角第i行第j列的数字
 * f(i, j) = f(i - 1, j - 1) + f(i - 1, j) 当j != 1 and j != i
 * 当 j == 1 or j == i f(i, j) = 1
 * @author lvzf
 * @date 2024/10/11
 */
public class YangHuiAngle {

    public static final int N = 10;

    public static void main(String[] args) {
        int [][] array = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= N - i; k++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= i; j++) {
                System.out.print(array[i][j] + " ");
            }

            System.out.println();
        }
    }
}
