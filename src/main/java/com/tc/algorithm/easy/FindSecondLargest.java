package com.tc.algorithm.easy;

/**
 * desc 找到数组中第二大的数，如果都相等则返回null
 *
 * @author lvzf 2023年11月18日
 */
public class FindSecondLargest {

    public static Integer findSecondLargest (int [] arr) {
        int largest = arr[0];
        int secondLargest = 0;
        boolean allSame = true;
        for (int i = 1; i < arr.length; i++) {
            if (largest != arr[i]) {
                allSame = false;
            }
            if (i > largest) {
                largest = i;
            }
        }
        if (allSame) {
            return null;
        }
        for (int i : arr) {
            if (i > secondLargest && i < largest) {
                secondLargest = i;
            }
        }
        return secondLargest;
    }
}
