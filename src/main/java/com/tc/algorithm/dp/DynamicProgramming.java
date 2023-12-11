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

        prices = new int []{7, 1, 5, 3, 6, 4};

        System.out.println(DynamicProgramming.maxProfit(prices));
    }

    /**
     * change coins
     * @param coins coin array
     * @param amount
     * @return
     */
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

    /**
     * 贪心算法解决股票最大收益
     * @param prices
     * @return
     */
    public static int maxProfit (int [] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int total = 0;
        int index = 0;
        int length = prices.length;

        while (index < length) {
            //如果股票一直下跌就一直找，找到股票开始涨为止
            while (index < length - 1 && prices[index] >= prices[index + 1]) {
                index++;
            }
            int min = prices[index];
            //找到股票上涨的最大值
            while (index < length - 1 && prices[index] <= prices[index + 1]) {
                index++;
            }
            //计算当前段的收益
            total += prices[index++] - min;
        }
        return total;
    }

    /**
     * 定义dp[i][0]表示第i天交易完之后手里没有股票的最大利润，dp[i][1]表示第i天交易完之后手里持有股票的最大利润。
     *
     * 当天交易完之后手里没有股票可能有两种情况，一种是当天没有进行任何交易，又因为当天手里没有股票，所以当天没有股票的利润只能取前一天手里没有股票的利润。一种是把当天手里的股票给卖了，既然能卖，说明手里是有股票的，
     * 所以这个时候当天没有股票的利润要取前一天手里有股票的利润加上当天股票能卖的价格。这两种情况我们取利润最大的即可，所以可以得到
     *
     * dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i]);
     *
     * 当天交易完之后手里持有股票也有两种情况，一种是当天没有任何交易，又因为当天手里持有股票，所以当天手里持有的股票其实前一天就已经持有了。还一种是当天买入了股票，当天能买股票，说明前一天手里肯定是没有股票的，我们取这两者的最大值，所以可以得到
     *
     * dp[i][1]=max(dp[i-1][1],dp[i-1][0]-prices[i]);
     *
     * 动态规划的递推公式有了，那么边界条件是什么，就是第一天
     *
     * 如果买入：dp[0][1]=-prices[0];
     *
     * 如果没买：dp[0][0]=0;
     * @param prices
     * @return
     */
    public static int maxProfitDp (int [] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int [][] dp = new int [prices.length][2];
        //第一天手里没有股票的收益
        dp[0][0] = 0;
        //第一天手里有股票的收益
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //第i天手里没有股票的收益
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //第i天手里有股票的收益
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //最后一天肯定是手里没有股票的时候，利润才会最大，只需要返回dp[length - 1][0]即可
        return dp[prices.length - 1][0];
    }
}
