package com.tc.algorithm.search;

/**
 * desc binary search
 *
 * @author lvzf 2023年11月23日
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println(BinarySearch.search(arr, 16));
    }

    public static int search(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;

        while (low <= high) {
            middle = (low + high) / 2;
            if (arr[middle] == value) {
                return middle;
            } else if (arr[middle] > value) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
