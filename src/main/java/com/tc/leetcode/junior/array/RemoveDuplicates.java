package com.tc.leetcode.junior.array;

/**
 * @author lvzf
 * @version 1.0
 * @project algorithm
 * @description 去除数组中重复的数字
 * @date 2023/11/25 09:35:35
 */
public class RemoveDuplicates {
    /**
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     * <p>
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那 nums 的前 k 个元素应该保存最终结果。
     * <p>
     * 将最终结果插 nums 的前 k 个位置后返回 k 。
     * <p>
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * tips
     * 1 <= nums.length <= 3 * 10000
     * -10000 <= nums[i] <= 10000
     * nums已生序排列
     */

    public static int removeDuplicates(int[] arr) {
        int left = 0;
        for (int right = 1; right < arr.length; right++) {
            if (arr[left] != arr[right]) {
                left = left + 1;
                arr[left] = arr[right];
            }
        }

        return left + 1;
    }

    public static int removeElement (int [] nums) {
        int slow = 0;
        int fast = 0;

        for (; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                slow = slow + 1;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public static int removeElement (int [] nums, int val) {
        int slow = 0;
        int fast = 0;
        for (; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow += 1;
            }
        }
        return slow;
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                if (max < count) {
                    max = count;
                }
                count = 0;
            }
        }
        if (max < count) {
            max = count;
        }
        return max;
    }

    /**
     * 给定一个含有n个正整数的数组和一个正整数 target 。
     *
     * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;

        int n = 0;
        int m = 0;
        int sum = 0;

        while (n < nums.length) {
            sum += nums[n];
            while (sum >= target) {
                if (min > n - m + 1) {
                    min = n - m + 1;
                }
                sum -= nums[m];
                m++;
            }
            n++;
        }
        return min == Integer.MAX_VALUE ? 0:min;
    }

    public static int minSubArrayLen1(int target, int [] nums) {
        int minLen = 0;

        int currentSum = nums[0];
        int j = 0;
        for (int i = 1; i <= nums.length; ) {
            while (currentSum >= target && j <= i) {
                if (minLen == 0) {
                    minLen = i - j;
                } else if (minLen > i - j) {
                    minLen = i - j;
                }
                currentSum = currentSum - nums[j];
                j++;
            }

            if (currentSum < target) {
                if (i >= nums.length) {
                    break;
                }
                currentSum += nums[i];
                i++;
            }
        }

        return minLen;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 2, 2, 3, 3, 4, 4};
        int k = RemoveDuplicates.removeDuplicates(arr);
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        int[] nums = {0, 0, 1, 2, 2, 3, 3, 4, 4};
        int idx = RemoveDuplicates.removeElement(nums);
        for (int i = 0; i < idx; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        //nums = new int [] {0, 1, 2, 2, 3, 0, 4, 2};
        nums = new int [] {3, 2, 2, 3};
        idx = RemoveDuplicates.removeElement(nums, 2);
        for (int i = 0; i < idx; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        nums = new int [] {1,0,1,1,0,1};

        System.out.println(RemoveDuplicates.findMaxConsecutiveOnes(nums));

        //nums = new int [] {2,3,1,2,4,3};
        nums = new int [] {1,4,4};
        //nums = new int [] {1,1,1,1,1,1,1,1};
        //nums = new int [] {1,2,3,4,5};
        System.out.println(RemoveDuplicates.minSubArrayLen1(4, nums));
    }
}
