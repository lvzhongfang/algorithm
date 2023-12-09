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

    /*public static int [] buildNext (String pat) {
        int m = pat.length();
        int j = 0;
        int [] N = new int[m];

        int t = -1;
        N[0] = -1;
        char [] P = pat.toCharArray();

        while (j < m - 1) {
            if (0 > t || P[j] == P[t]) {
                j++;
                t++;
                N[j] = t;
            } else {
                t = N[t];
            }
        }
        return N;
    }*/

    public static int [] buildNext (String pat) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        int M = pat.length();
        int [] lps = new int[M];
        // lps[0] is always 0
        lps[0] = 0;
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else { // (pat[i] != pat[len])
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void kmpSearch (String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int [] lps = new int [M];
        int j = 0; //index of pat []

        lps = KMPSearch.buildNext(pat);

        int i = 0; // index of txt []

        while ((N - i) >= (M - j)) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)){
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        String pat = "abba";
        String txt = "abbcabaabbaabc";
        KMPSearch kmp = new KMPSearch(pat);

        KMPSearch.kmpSearch(pat, txt);
    }
}
