package com.tc.leetcode.junior.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvzf
 * @version 1.0
 * @project algorithm
 * @description subarray with given sum
 * @date 2023/11/11 05:00:57
 */
public class SubarraySum {

    public static void main(String[] args) {
        SubarraySum subarraySum = new SubarraySum();
        int [] arr = {15, 2, 4, 8, 9, 5, 10, 23};
        int n = arr.length;
        int sum = 23;
        //List<Integer> result = subarraySum.slidingWindow(arr, n, sum);
        List<Integer> result = subarraySum.dp(arr, n, sum);
        if (result.size() == 1) {
            System.out.println(result.get(0));
        } else {
            System.out.println(result.get(0) + "," + result.get(1));
        }
    }

    /**
     * dynamic programming 动态规划
     * @param arr
     * @param n
     * @param sum
     * @return
     */
    public List<Integer> dp(int arr[], int n, int sum) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum = currentSum + arr[i];

            if (map.containsKey(currentSum - sum)) {
                result.add(map.get(currentSum - sum) + 2);
                result.add(i + 1);
                break;
            }
            map.put(currentSum, i);
        }
        return result;
    }
    public List<Integer> slidingWindow (int [] arr, int n, int sum)
    {
        List<Integer> result = new ArrayList<>();
        int currentSum = arr[0];
        int start = 0;
        int i = 1;
        for (; i < n; i++) {
            while (currentSum > sum && start < i - 1) {
                currentSum = currentSum - arr[start];
                start++;
            }

            if (currentSum == sum) {
                result.add(start + 1);
                result.add(i);
            }

            if (currentSum < sum) {
                currentSum = currentSum + arr[i];
            }
        }

        if (result.size() == 0) {
            result.add(-1);
        }
        return result;
    }
}
