package DP다시풀기;

import java.io.*;

public class b9095 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] dp = new int[N+1];

            if (N >= 1) dp[1] = 1;
            if (N >= 2) dp[2] = 2;
            if (N >= 3) dp[3] = 4;

            for (int j = 4; j <= N; j++){
                dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
            }
            System.out.println(dp[N]);
        }
    }
}
