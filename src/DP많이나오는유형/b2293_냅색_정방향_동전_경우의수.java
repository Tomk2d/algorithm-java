package DP많이나오는유형;

import java.util.*;
import java.io.*;

public class b2293_냅색_정방향_동전_경우의수
{
    private static int N, target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            coins[i] = n;
        }

        int[] dp = new int[target+1];
        dp[0] = 1;

        for(int coin : coins){
            for(int i=coin; i<=target; i++){
                dp[i] += dp[i - coin];
            }
        }

        System.out.println(dp[target]);

    }
}

