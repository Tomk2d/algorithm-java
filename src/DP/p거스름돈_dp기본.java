package DP;

class p거스름돈_dp기본 {
    public int solution(int goal, int[] moneys) {
        int[] dp = new int[goal + 1];
        dp[0] = 1;
        int MOD = 1000000007;

        for (int money : moneys) {
            for (int i = money; i <= goal; i++) {
                dp[i] = (dp[i] + dp[i - money]) % MOD;
            }
        }

        return dp[goal];
    }
}

