package com.tc.algorithm.easy;

/**
 * desc 将数组中所有为0的项放到最后
 *
 * @author lvzf 2023年11月18日
 */
public class PushZeroToEnd {

    public void pushZero2End (int [] arr) {
        int n = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[n];
                arr[n] = temp;
                n++;
            }
        }
    }

    public static void pushZero2End1 (int [] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }

        while (count < arr.length) {
            arr[count++] = 0;
        }
    }
}
