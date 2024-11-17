package DP;

import java.util.*;
import java.io.*;

public class b2839 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];

        Arrays.fill(dp, -1);

        if (n >= 3) dp[3] = 1;
        if (n >= 5) dp[5] = 1;

        for (int i = 6; i <= n; i++) {
            if (dp[i - 3] != -1 && dp[i] == -1) {
                dp[i] = dp[i - 3] + 1;
            } else if (dp[i - 3] != -1) {
                dp[i] = Math.min(dp[i], dp[i - 3] + 1);
            }

            if (dp[i - 5] != -1 && dp[i] == -1) {
                dp[i] = dp[i - 5] + 1;
            } else if (dp[i - 5] != -1) {
                dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
