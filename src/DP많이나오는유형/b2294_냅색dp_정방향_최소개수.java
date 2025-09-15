package DP많이나오는유형;

import java.util.*;
import java.io.*;

public class b2294_냅색dp_정방향_최소개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, target;
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;      // 0원을 만들기 위해 필요한 개수 0개

        for (int coin : coins) {
            for (int idx = coin; idx <= target; idx++) {
                if (dp[idx - coin] != Integer.MAX_VALUE) {
                    dp[idx] = Math.min(dp[idx], dp[idx - coin] + 1);
                }
            }
        }

        System.out.println(dp[target] == Integer.MAX_VALUE ? -1 : dp[target]);
    }
}

