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

        System.out.println(DynamicProgramming.change1(coins, 11));

        int [] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        System.out.println(DynamicProgramming.cutRod(prices, 30));

        prices = new int []{7, 1, 5, 3, 6, 4};

        System.out.println(DynamicProgramming.maxProfit(prices));

        int [] cost = {2, 5, 20};
        System.out.println(DynamicProgramming.minCostClimbingStairs(cost));

        System.out.println(DynamicProgramming.fibonacci(4));

        //int [][] obstacleGrid = {{0,0,0}, {0,1,0}, {0,0,0}};
        int [][] obstacleGrid = {{0,1}, {0,0}};
        System.out.println(DynamicProgramming.uniquePathsWithObstacles(obstacleGrid));

        int [][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(DynamicProgramming.minPathSum(grid));

        DynamicProgramming.printMinPath(grid);
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

    /**
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     * 题目数据保证结果符合 32 位带符号整数。
     *
     * dp[i] 保存金额为i的组合数
     * dp[i] = dp[i] + dp[i - coins]
     * @param coins
     * @param amount
     * @return
     */
    public static int change1 (int [] coins, int amount) {
        int [] dp = new int [amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
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

    /**
     * 给定一个整数数组cost  ，其中 cost[i] 是从楼梯第
     * i个台阶向上爬需要支付的费用，下标从0开始。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     *
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     *
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     * 数据范围：数组长度满足 1≤n≤10^5，数组中的值满足 1≤costi≤10^4
     *
     * 输入：
     * [2,5,20]
     * 返回值：5
     * 说明：
     * 你将从下标为1的台阶开始，支付5 ，向上爬两个台阶，到达楼梯顶部。总花费为5
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs (int[] cost) {
        // write code here
        int [] dp = new int [cost.length + 1];
        for (int i = 2; i <= cost.length; i++) {
            dp [i] = Math.min(dp [i - 1] + cost [i - 1], dp [i - 2] + cost [i - 2]);
        }

        return dp [cost.length];
    }

    /**
     * 斐波那契数列
     * @param n
     * @return
     */
    public static int fibonacci (int n) {
        // write code here
        // f(i) = f(i - 1) + f(i - 2);
        /*if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return DynamicProgramming.fibonacci(n - 1) + DynamicProgramming.fibonacci(n - 2);
        }*/
        if (n == 1 || n == 2) {
            return 1;
        }

        int [] dp = new int [n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）
     * @param number
     * @return
     */
    public static int jumpFloor (int number) {
        if (number == 1) {
            return 1;
        }

        int [] dp = new int[number + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= number; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[number];
    }

    /**
     * L62
     * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     *            | dp[i][j - 1] i == 0 and j > 0
     * dp[i][j] = | dp[i - 1][j] i > 0 and j == 0
     *            | dp[i - 1][j] + dp[i][j - 1] i > 0 and j > 0
     * @param m row
     * @param n column
     * @return path count
     */
    public static int uniquePaths(int m, int n) {
        int [][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (i > 0 && j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i > 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * L63
     * 一个机器人位于一个m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 有障碍的位置不能到达，所以如果obstacleGrid[i][j] == 1 那么 dp[i][j] = 0
     *            | dp[i][j - 1] i == 0 and j > 0, 当obstacleGrid[i][j - 1] == 0 时dp[i][j] = dp[i][j - 1] 当obstacleGrid[i][j - 1] == 1时i,j位置不能到达，所以dp[i][j] = 0
     * dp[i][j] = | dp[i - 1][j] i > 0 and j == 0, 当obstacleGrid[i - 1][j] == 0 时dp[i][j] = dp[i - 1][j] 当obstacleGrid[i - 1][j] == 1时i,j位置不能到达，所以dp[i][j] = 0
     *            | dp[i - 1][j] + dp[i][j - 1] i > 0 and j > 0, 当obstacleGrid[i - 1][j] == 0 and obstacleGrid[i][j - 1] == 1说明i,j位置的左方有障碍，所以dp[i][j] = dp[i - 1][j]，
     *            当obstacleGrid[i][j - 1] == 0 and obstacleGrid[i - 1][j] == 1说明i,j位置的上方有障碍，所以dp[i][j] = dp[i][j - 1]，当obstacleGrid[i - 1][j] == 1 and obstacleGrid[i][j - 1] == 1
     *            说明i,j位置的左方与上方都有障碍，所以当前位置无法到达dp[i][j] = 0，当obstacleGrid[i - 1][j] == 0 and obstacleGrid[i][j - 1] == 0说明左方与上方都没有障碍dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * @param obstacleGrid 地图，i,j == 1表示障碍，i,j == 0表示无障碍
     * @return 到达终点的路径数
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int [][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 && j > 0) {
                        if (obstacleGrid[i][j - 1] == 1) {
                            dp[i][j] = 0;
                        } else {
                            dp[i][j] = dp[i][j - 1];
                        }
                    } else if (i > 0 && j == 0) {
                        if (obstacleGrid[i - 1][j] == 1) {
                            dp[i][j] = 0;
                        } else {
                            dp[i][j] = dp[i - 1][j];
                        }
                    } else if (i > 0) {
                        if (obstacleGrid[i][j - 1] == 1 && obstacleGrid[i - 1][j] == 0) {
                            dp[i][j] = dp[i - 1][j];
                        } else if (obstacleGrid[i][j - 1] == 0 && obstacleGrid[i - 1][j] == 1){
                            dp[i][j] = dp[i][j - 1];
                        } else if (obstacleGrid[i][j - 1] == 0 && obstacleGrid[i - 1][j] == 0) {
                            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                        } else {
                            dp[i][j] = 0;
                        }
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int [][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (i > 0 && j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 打印最小路径
     * @param grid
     */
    public static void printMinPath (int [][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int [][] dp = new int[m][n];
        int [] g = new int[m * n];

        dp[0][0] = grid[0][0];
        g[0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                    g[DynamicProgramming.getIdx(i, j, n)] = DynamicProgramming.getIdx(i, j - 1, n);
                } else if (i > 0 && j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                    g[DynamicProgramming.getIdx(i, j, n)] = DynamicProgramming.getIdx(i - 1, j, n);
                } else if (i > 0) {
                    int top = dp[i - 1][j];
                    int left = dp[i][j - 1];
                    dp[i][j] = Math.min(top, left) + grid[i][j];
                    //记录上一步的坐标
                    if (top < left) {
                        g[DynamicProgramming.getIdx(i, j, n)] = DynamicProgramming.getIdx(i - 1, j, n);
                    } else {
                        g[DynamicProgramming.getIdx(i, j, n)] = DynamicProgramming.getIdx(i, j - 1, n);
                    }
                }
            }
        }

        //从「结尾」开始，在 g[] 数组中找「上一步」
        int idx = DynamicProgramming.getIdx(m - 1, n - 1, n);
        int [][] path = new int [m + n][2];
        path[m + n - 1] = new int[]{m - 1, n - 1};
        for (int i = 1; i < m + n; i++) {
            path[m + n - 1 - i] = DynamicProgramming.parseIdx(g[idx], n);
            idx = g[idx];
        }

        // 顺序输出位置
        for (int i = 1; i < m + n; i++) {
            int x = path[i][0], y = path[i][1];
            System.out.print("(" + x + "," + y + ") ");
        }
        System.out.println(" ");
    }

    /**
     * 二维数组坐标转换为一维数组下标
     * @param x 二维数组行坐标
     * @param y 二维数组列坐标
     * @param len 二维数组行长度
     * @return 对应的一维数组index
     */
    public static int getIdx (int x, int y, int len) {
        return x * len + y;
    }

    /**
     * 一维数组index转二维数组坐标
     * @param idx 一维数组index
     * @param len 二维数组行长度
     * @return 二维数组坐标
     */
    public static int [] parseIdx (int idx, int len) {
        return new int []{idx / len, idx % len};
    }
}
