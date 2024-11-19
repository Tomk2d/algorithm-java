package DP다시풀기;

import java.util.*;
import java.io.*;

public class b2839_최소나누기_누적합개념 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        Arrays.fill(dp,-1);

        if (N >= 3) dp[3] = 1;
        if (N >= 5) dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            int min_num = 5001;

            if (dp[i-3] != -1) {
                min_num = Math.min(min_num, dp[i-3] + 1);
            }
            if (dp[i-5] != -1) {
                min_num = Math.min(min_num, dp[i-5] + 1);
            }
            if (min_num != 5001) {
                dp[i] = min_num;
            }
        }
        System.out.println(dp[N]);
    }
}
