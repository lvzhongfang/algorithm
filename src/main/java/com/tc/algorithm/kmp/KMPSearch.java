package com.tc.algorithm.kmp;

/**
 * desc KMP字符串匹配算法
 *
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

    public int change(int amount, int[] coins) {
        int max = Integer.MAX_VALUE;
        //递推表达式
        int[] dp = new int[amount + 1];
        //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        //初始化dp数组为最大值
        for (int j = 0; j < dp.length; j++) {
            dp[j] = max;
        }
        //当金额为0时需要的硬币数目为0
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            //正序遍历：完全背包每个硬币可以选择多次
            for (int j = coins[i]; j <= amount; j++) {
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j - coins[i]] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        String pat = "abba";
        String txt = "abbcabaabbaabc";
        KMPSearch kmp = new KMPSearch(pat);

        System.out.println(kmp.search(txt));
        txt.contains(pat);

        int [] coins = {1,2,5};
        System.out.println(kmp.change(11, coins));
    }
}
