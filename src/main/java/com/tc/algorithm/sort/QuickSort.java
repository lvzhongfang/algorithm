package com.tc.algorithm.sort;

/**
 * description: 快速排序
 *
 * @author: Leo Lv
 * @date: 2022/10/12 09:47:18
 * @version: 1.0.0
 */
public class QuickSort {

    public static void quickSort (int [] array, int left, int right) {
        int header = left;
        int end = right;

        if (left >= right) {
            return;
        }
        int basic = array[left];

        while (right > left) {
            //从右开始找到小于基数的第一个位置
            while (array[right] >= basic && right > left) {
                right--;
            }

            //从左开始找到大于基数的第一个位置
            while (array[left] <= basic && left < right) {
                left++;
            }

            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        //将基数放到正确位置，并将基数正确位置的当前值放到基数原来的位置
        array[header] = array[left];
        array[left] = basic;

        quickSort(array, header, left - 1);
        quickSort(array, right + 1, end);
    }

    public static void main (String [] args) {
        int [] array = {6,1,2,7,9,3,4,5,10,8};
        System.out.println("Quick sort begin.");
        System.out.println("Before sort ");
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println("");
        QuickSort.quickSort(array, 0, array.length - 1);
        System.out.println("After sort ");
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println("");
        System.out.println("Quick sort end.");
    }
}
