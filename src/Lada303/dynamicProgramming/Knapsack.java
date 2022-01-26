package Lada303.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
    public void run() {
        Scanner sc = new Scanner(System.in);
        int knapsackW = sc.nextInt();
        int n = sc.nextInt();
        int[] arrWi = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arrWi[i] = sc.nextInt();
        }
        //System.out.println(Arrays.toString(arrWi));
        System.out.println(knapsackWithoutReps(knapsackW, n, arrWi));
    }

    private int knapsackWithoutReps(int knapsackW, int n, int[] arrWi) {
        int[][] dp = new int[n + 1][knapsackW + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < knapsackW + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (arrWi[i] < knapsackW && j >= arrWi[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - arrWi[i]] + arrWi[i]);
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        return dp[n][knapsackW];
    }
}
