package com.tc.algorithm.kmp;

/**
 * desc KMP字符串匹配算法
 * KMP 算法（Knuth-Morris-Pratt 算法）是一个著名的字符串匹配算法
 * @author lvzf 2023年11月20日
 */
public class KMPSearch {

    private int [][] dp;

    //子字符串，需要查找的串
    private String pat;

    public KMPSearch (String pat) {
        this.pat = pat;
        int M = pat.length();
        // dp [状态][字符] = 下一个状态 (DFA)
        this.dp = new int[M][256];
        // 影子状态X，初始化为0, 如果当前状态读取下一个字符后不匹配则转移到X，否则转移到下一个状态
        int X = 0;
        for (int i = 0; i < M; i++) {
            for (int c = 0; c < 256; c++) {
                if (pat.charAt(i) == c) {
                    dp[i][c] = i + 1;
                } else {
                    dp[i][c] = dp[X][c];
                }
            }
            X = dp[X][pat.charAt(i)];
        }
    }

    public int search (String txt) {
        int M = pat.length();
        int N = txt.length();
        if (N < M) {
            return -1;
        }

        int n = 0;
        for (int i = 0; i < N; i++) {
            n = dp[n][txt.charAt(i)];
            if (n == M) {
                return i - M +1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String pat = "abba";
        String txt = "abbcabaabbaabc";
        KMPSearch kmp = new KMPSearch(pat);
    }
}
