package com.tc.algorithm.subarray.sum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * desc 查找数组中连续字串和
 *
 * @author lvzf 2023年11月18日
 */
public class SubArraySum {

    public static void main(String[] args) {
        //int [] arr = {135, 101, 170, 125, 79, 159, 163, 65, 106, 146, 82, 28, 162, 92, 196, 143, 28, 37, 192, 5, 103, 154, 93, 183, 22, 117, 119, 96, 48, 127, 172, 139, 70, 113, 68, 100, 36, 95, 104, 12, 123, 134};
        //int [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int [] arr = {10, 10, 10};
        ArrayList<Integer> list = SubArraySum.slidingWindow(arr, arr.length, 15);

        if (list.size() == 1) {
            System.out.println(list.get(0));
        } else {
            System.out.println(list.get(0) + ", " + list.get(1));
        }
    }

    /**
     * 最基本的查找方式
     * @param arr 待查找的数组
     * @param n 数组长度
     * @param s 子数组的和
     * @return 子数组下标范围，开始与结束下标，不存在返回-1，下标从1开始(base on 1)
     */
    public static ArrayList<Integer> basic (int [] arr, int n, int s) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            int sum = arr[i];
            if (sum == s) {
                result.add(i + 1);
                result.add(i + 1);
                return result;
            }
            for (int m = i + 1; m< n; m++) {
                sum += arr[m];
                if (sum == s) {
                    result.add(i+1);
                    result.add(m+1);
                    return result;
                } else if (sum > s) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 滑动窗口
     * @param arr 待查找的数组
     * @param n 数组长度
     * @param s 子数组的和
     * @return 子数组下标范围，开始与结束下标，不存在返回-1，下标从1开始(base on 1)
     */
    public static ArrayList<Integer> slidingWindow (int [] arr, int n, int s) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        int left = 0;
        int sum = arr[0];
        for (int i = 1; i <= n; i++) {
            while (sum > s && left < i - 1) {
                sum = sum - arr[left];
                left++;
            }

            if (sum == s) {
                result.add(left + 1);
                result.add(i);
            }

            if (i < n) {
                sum = sum + arr[i];
            }
        }

        if (result.size() == 0) {
            result.add(-1);
        }
        return result;
    }

    /**
     * 动态规划-dynamic programming
     * @param arr 待查找的数组
     * @param n 数组长度
     * @param s 子数组的和
     * @return 子数组下标范围，开始与结束下标，不存在返回-1，下标从1开始(base on 1)
     */
    public static ArrayList<Integer> dp (int [] arr, int n, int s) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == s) {
                result.add(1);
                result.add(i + 1);
                break;
            }
            if (map.containsKey(sum - s)) {
                result.add(map.get(sum - s) + 2);
                result.add(i + 1);
                break;
            }
            map.put(sum, i);
        }

        if (result.size() == 0) {
            result.add(-1);
        }
        return result;
    }
}
