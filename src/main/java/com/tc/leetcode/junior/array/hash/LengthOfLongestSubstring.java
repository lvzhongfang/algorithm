package com.tc.leetcode.junior.array.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * desc 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * @author lvzf
 * @date 2024/1/19
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring lols = new LengthOfLongestSubstring();

        String s = "pwwkew";
        System.out.println(lols.lengthOfLongestSubstring(s));
    }
    public int lengthOfLongestSubstring1(String s) {

        if (s == null || "".equals(s)) {
            return 0;
        }

        int maxLength = 1;
        String sub = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = sub.indexOf(c);
            if (index >= 0) {
                if (sub.length() > maxLength) {
                    maxLength = sub.length();
                }
                sub = sub.substring(index + 1);
            }
            sub = sub + String.valueOf(c);
        }

        if (sub.length() > maxLength) {
            maxLength = sub.length();
        }

        return maxLength;
    }

    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int n = s.length();
        int rk = -1;
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }

            while (rk + 1 < n && !set.contains(s.charAt(rk + 1))) {
                set.add(s.charAt(rk + 1));
                rk++;
            }
            ans = Math.max(ans, rk + 1 - i);
        }
        return ans;
    }

}
