package com.tc.algorithm.sort;

/**
 * desc insertion sort
 *
 * @author lvzf 2023年11月21日
 */
public class InsertionSort {

    // descend
    public static final int DESC = 1;

    //ascend
    public static final int ASC = 0;

    public static void insertionSort (int [] arr, int order) {
        for (int n = 1; n < arr.length; n++) {
            int key = arr[n];
            int m = n - 1;
            if (InsertionSort.DESC == order) {
                while (m >= 0 && key > arr[m]) {
                    arr[m + 1] = arr[m];
                    m = m - 1;
                }
            } else {
                while (m >= 0 && key < arr[m]) {
                    arr[m + 1] = arr[m];
                    m = m - 1;
                }
            }
            arr [m + 1] = key;
        }
    }

    public static void insertionSort (int [][] arr, int order) {
        for (int n = 1; n < arr.length; n++) {
            int [] key = arr[n];
            int m = n - 1;
            if (InsertionSort.DESC == order) {
                while (m >= 0 && key[0] >= arr[m][0] && key[1] >= arr[m][1]) {
                    arr[m + 1] = arr[m];
                    m = m - 1;
                }
            } else {
                while (m >= 0 && key[0] <= arr[m][0] && key[1] <= arr[m][1]) {
                    arr[m + 1] = arr[m];
                    m = m - 1;
                }
            }
            arr [m + 1] = key;
        }
    }

    public static void main(String[] args) {
        int [] arr = {4, 5, 3, 1, 6, 2, 7, 9, 8, 0};
        InsertionSort.insertionSort(arr, InsertionSort.DESC);
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + ",");
            } else {
                System.out.println(arr[i]);
            }
        }
    }
}
