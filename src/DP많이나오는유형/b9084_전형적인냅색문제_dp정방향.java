package DP많이나오는유형;

import java.util.*;
import java.io.*;

public class b9084_전형적인냅색문제_dp정방향 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());
        while(testCase--> 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());

            int[] dp = new int[target+1];
            dp[0] = 1;

            for(int coin : coins){
                for(int idx=coin; idx<=target; idx++){
                    dp[idx] += dp[idx-coin];
                }
            }

            System.out.println(dp[target]);

        }


    }
}

