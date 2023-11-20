package com.tc.algorithm.dp;

/**
 * desc dynamic programming
 *
 * @author lvzf 2023年11月20日
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        int [] coins = {1,2,5};
        System.out.println(DynamicProgramming.change(coins, 11));

        int [] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        System.out.println(DynamicProgramming.cutRod(prices, 30));
    }

    public static int change (int [] coins, int amount) {
        int max = Integer.MAX_VALUE;
        //index = 0 represent amount 0
        int [] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = max;
        }
        // 0 amount need 0 coin
        dp[0] = 0;
        // dp[amount] = dp[amount - coins[i]] + 1;
        // dp [amount] = min(dp[amount], dp[amount - coins[i]] + 1)
        for (int n = 0; n < coins.length; n++) {
            int currentCoin = coins[n];
            for (int m = currentCoin; m <= amount; m++) {
                if (dp[m - currentCoin] != max) {
                    dp[m] = Math.min(dp[m], dp[m - currentCoin] +1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    public static int cutRod (int [] prices, int rodLength) {
        int [] dp = new int [rodLength + 1];
        dp [0] = 0;

        for (int n = 1; n <= rodLength; n++) {
            int q = Integer.MIN_VALUE;
            for (int m = 1; m <= n; m++) {
                q = Math.max(q, prices[(m - 1) % prices.length] + dp[n - m]);
            }
            dp[n] = q;
        }
        return dp[rodLength];
    }
}
