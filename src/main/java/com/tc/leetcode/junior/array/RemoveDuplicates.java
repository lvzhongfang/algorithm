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

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 2, 2, 3, 3, 4, 4};
        int k = RemoveDuplicates.removeDuplicates(arr);
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
