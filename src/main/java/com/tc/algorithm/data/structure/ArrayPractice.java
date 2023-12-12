package com.tc.algorithm.data.structure;

import com.tc.algorithm.sort.InsertionSort;

import java.util.ArrayList;
import java.util.List;

/**
 * desc 寻找数组的中心索引
 *
 * @author lvzf 2023年11月28日
 */
public class ArrayPractice {
    /**
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     *
     * 示例 1：
     * 输入：nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 中心下标是 3 。
     * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
     * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
     *
     * 示例 2：
     * 输入：nums = [1, 2, 3]
     * 输出：-1
     * 解释：
     * 数组中不存在满足此条件的中心下标。
     *
     * 示例 3：
     * 输入：nums = [2, 1, -1]
     * 输出：0
     * 解释：
     * 中心下标是 0 。
     * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
     * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
     *
     * 提示：
     * 1 <= nums.length <= 104
     * -1000 <= nums[i] <= 1000
     *
     */

    /**
     * 1. 左侧数据的和 * 2 + 中位数 = 总和 sum == 2 * leftSum + nums[n]
     * 2. 总和减去左侧数据和再减去中位数等于左侧数据和，leftSum == sum - leftSum - nums[n]
     */

    public static int pivotIndex (int [] nums) {

        int sum = 0;
        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        for (int n = 0; n < nums.length; n++) {
            if (leftSum == sum - leftSum - nums[n]) {
                return n;
            }
            leftSum += nums[n];
        }
        return -1;
    }

    /**
     *给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     *
     * 示例 1:
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     *
     * 示例2:
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     *
     * 示例 3:
     * 输入: nums = [1,3,5,6], target = 7
     * 输出: 4
     *
     * 提示:
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 为无重复元素的升序排列数组
     * -104 <= target <= 104

     */

    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int middle = 0;
        int ans = nums.length;
        while (low <= high) {
            middle = ((high - low) >> 1) + low;
            if (target <= nums[middle]) {
                ans = middle;
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return ans;
    }

    public int searchInsertMy(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int middle = 0;
        while (low <= high) {
            middle = (low + high) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        if (nums[middle] > target) {
            if (middle != 0) {
                if (nums[middle - 1] < target) {
                    return middle;
                } else {
                    return middle - 1;
                }
            } else {
                if (nums[middle] < target) {
                    return middle;
                } else {
                    return 0;
                }
            }
        } else {
            return middle + 1;
        }
    }

    /**
     *以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * 示例 1：
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 示例2：
     *
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 提示：
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     * @param intervals
     * @return
     */

    public static int[][] merge(int[][] intervals) {

        int count = 0;
        int start = 0;
        int max = -1;

        for (int i = 0; i < intervals.length; i++) {
            if (max < intervals[i][1]) {
                max = intervals[i][1];
            }
        }

        int [] starts = new int[max + 1];
        int [] end = new int[max + 1];

        for (int i = 0; i < intervals.length; i++) {
            starts[intervals[i][0]] += 1;
            end[intervals[i][1]] += 1;
        }
        List<int []> list = new ArrayList<>();

        for (int i = 0 ; i < max + 1; i++) {
            if (starts[i] > 0) {
                if (count == 0) {
                    start = i;
                }
                count += starts[i];
            }

            if (end[i] > 0) {
                count -= end[i];
                if (count == 0) {
                    list.add(new int [] {start, i});
                }
            }
        }

        int [][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 杨辉三角
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            if (i == 1) {
                List<Integer> l = new ArrayList<>();
                l.add(1);
                result.add(l);
            } else {
                List<Integer> preL = result.get(i - 2);
                List<Integer> l = new ArrayList<>();
                for (int n = 0; n < i; n++) {
                    if (n == 0 || n == i - 1) {
                        l.add(1);
                    } else {
                        l.add(preL.get(n - 1) + preL.get(n));
                    }
                }
                result.add(l);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //int [] arr = {1, 7, 3, 6, 5, 6};
        //int [] arr = {-1,-1,-1,-1,-1,-1};
        int [] arr = {-1,1,2};
        System.out.println(ArrayPractice.pivotIndex(arr));

        //int [][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        //int [][] intervals = {{1,4},{2,3}};
        //int [][] intervals = {{1,4},{4,5}};
        //int [][] intervals = {{1,4},{1,4}};
        //int [][] intervals = {{1,4},{0,4}};
        int [][] intervals = {{1,4},{1,5}};
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0]);
            System.out.print(",");
            System.out.print(intervals[i][1] + "],");
        }

        System.out.println();

        int [][] result = ArrayPractice.merge(intervals);
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            System.out.print("[" + result[i][0]);
            System.out.print(",");
            System.out.print(result[i][1] + "],");
        }
        System.out.print("]\n");

        List<List<Integer>> l = ArrayPractice.generate(5);

        for (int i = 0; i < l.size(); i++) {
            List<Integer> list = l.get(i);
            System.out.print("[");
            for (int n = 0; n < list.size(); n++) {
                System.out.print(list.get(n));
                if (n < list.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("] ");
        }
    }
}
