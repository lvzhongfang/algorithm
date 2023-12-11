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

        int [] nums = {6,2,6,5,1,2};
        System.out.println(SubarraySum.arrayPairSum(nums));

        int [] numbers = {2, 7, 11,15};
        int [] twoSum = SubarraySum.twoSum1(numbers, 9);
        System.out.println("[" + twoSum[0] + "," + twoSum[1] + "]");
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

    public static int arrayPairSum (int [] nums) {
        int key = 0;
        for (int n = 1; n < nums.length; n++) {
            key = nums[n];
            int m = n - 1;
            while (m >= 0 && key < nums[m]) {
                nums[m + 1] = nums[m];
                m--;
            }
            nums[m + 1] = key;
        }

        int result = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            result += nums[2 * i];
        }

        return result;
    }

    public static int[] twoSum (int[] numbers, int target) {
        int [] result = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            for (int n = i + 1; n < numbers.length; n++) {
                if (numbers[i] + numbers[n] == target) {
                    result[0] = i + 1;
                    result[1] = n + 1;
                    return result;
                }

                if (numbers[i] + numbers[n] > target) {
                    break;
                }
            }
        }

        return result;
    }

    public static int[] twoSum1 (int[] numbers, int target) {

        int [] result = new int[2];

        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int middle = (low + high) >>> 1;
            if (numbers[low] + numbers[middle] > target) {
                high = middle - 1;
            } else if (numbers[low] + numbers[high] > target){
                high--;
            } else if (numbers[low] + numbers[high] < target) {
                low++;
            } else {
                result[0] = low + 1;
                result[1] = high + 1;
                break;
            }
        }

        return result;
    }

}
