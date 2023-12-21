package com.tc.algorithm.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * desc 最长公共子序列
 *
 * @author lvzf
 * @date 2023/12/18
 */
public class LCS {

    public static String LCS (String s1, String s2) {
        // write code here
        if (s1.length() == 0 || s2.length() == 0) {
            return "-1";
        }

        int len1 = s1.length();
        int len2 = s2.length();

        int [][] dp = new int [len1 + 1][len2 + 1];
        int [][] b = new int [len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    //来自于左上方
                    b[i][j] = 1;
                } else {
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        //来自于左方
                        b[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        //来自于上方
                        b[i][j] = 3;
                    }
                }
            }
        }

        String res = LCS.ans(len1, len2, b, s1, s2);

        if (res.isEmpty()) {
            return "-1";
        }

        return res;
    }

    public static String ans(int i, int j, int[][] b, String s1, String s2) {
        String res = "";
        if (i == 0 || j == 0) {
            return res;
        }
        //左上
        if (b[i][j] == 1) {
            res += ans(i - 1, j - 1, b, s1, s2);
            res += s1.charAt(i - 1);
        } else if (b[i][j] == 2) {
            //上方
            res += ans(i - 1, j, b, s1, s2);
        } else if (b[i][j] == 3) {
            //左
            res += ans(i, j - 1, b, s1, s2);
        }

        return res;
    }

    /**
     * 最长公共子序列，序列不要求在原始字符串中连续
     * @param s1 字符串
     * @param s2 字符串
     * @return s1与s2中公共的最长子序列
     */
    public static String LCS1 (String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return "-1";
        }

        int len1 = s1.length();
        int len2 = s2.length();
        //记录字符串s1中第i个位置与字符串s2中第j个位置公共序列的最大长度
        //           | 0  i = 0或j = 0
        //dp[i][j] = | dp[i - 1][j - 1] + 1  i,j > 0 and s1.charAt(i) == s2.charAt(j)
        //           | Max(dp[i - 1][j], dp[i][j - 1])  i,j > 0 and s1.charAt(i) != s2.charAt(j)

        int [][] dp = new int [len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }

        Stack<Character> stack = new Stack<>();
        int n = len1;
        int m = len2;
        while (dp[n][m] != 0) {
            //来自于左方向
            if (dp[n][m] == dp[n - 1][m]) {
                n--;
            } else if (dp[n][m] == dp[n][m - 1]) {
                //来自于上方向
                m--;
            } else if (dp[n][m] > dp[n -1][m - 1]) {
                //来自于左上方向
                n--;
                m--;
                //只有左上方向才是字符相等的情况，入栈，逆序使用
                stack.push(s1.charAt(n));
            }
        }
        String res = "";
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return !res.isEmpty() ? res : "-1";
    }

    /**
     * 最长公共子串
     * @param str1
     * @param str2
     * @return
     */
    public static String LCS2 (String str1, String str2) {
        // write code here
        int len1 = str1.length();
        int len2 = str2.length();
        String shortStr = "";
        String longStr = "";
        if (len1 > len2) {
            longStr = str1;
            shortStr = str2;
        } else {
            shortStr = str1;
            longStr = str2;
        }
        int n = 1;
        List<String> list = new ArrayList<>();

        for (int i = 0; i <= shortStr.length() - n; ) {
            String temp = shortStr.substring(i, i + n);
            for (int j = 0; j <= longStr.length() - n; j++) {
                String t = longStr.substring(j, j + n);
                if (temp.equals(t)) {
                    list.add(temp);
                    n += 1;
                    break;
                } else if (j == longStr.length() - n - 1){
                    i++;
                }
            }
        }

        String res = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() > res.length()) {
                res = list.get(i);
            }
        }
        return res;
    }

    public static String LCS3 (String str1, String str2) {
        int maxLength = 0;
        int maxLengthIndex = 0;

        int [][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;

                    if (dp[i + 1][j + 1] > maxLength) {
                        maxLength = dp[i + 1][j + 1];
                        maxLengthIndex = i;
                    }
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }

        return str1.substring(maxLengthIndex - maxLength + 1, maxLengthIndex + 1);
    }

    public static void main(String[] args) {
        String s1 = "1A2C3D4B56";
        String s2 = "B1D23A456A";
        System.out.println(LCS.LCS1(s1, s2));

        s1 = "22222";
        s2 = "22222";
        System.out.println(LCS.LCS2(s1, s2));

        System.out.println(LCS.LCS3(s1, s2));
    }
}
