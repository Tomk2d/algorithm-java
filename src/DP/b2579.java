package DP;

import java.util.*;
import java.io.*;

public class b2579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] steps = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            steps[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(steps[1]);
            return;
        } else if (N == 2) {
            System.out.println(steps[1] + steps[2]);
            return;
        }

        int[] dp = new int[N + 1];
        dp[1] = steps[1];
        dp[2] = steps[1] + steps[2];
        dp[3] = Math.max(steps[1] + steps[3], steps[2] + steps[3]);

        for (int i = 4; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2] + steps[i], dp[i - 3] + steps[i - 1] + steps[i]);
        }

        System.out.println(dp[N]);
    }
}
