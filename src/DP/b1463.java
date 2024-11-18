package DP;

import java.util.*;
import java.io.*;

public class b1463 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++){
            if (i==2) dp[2] = 1;
            if (i==3) dp[3] = 1;

            int min_num = 10000000;
            if (dp[i-1] != 0){
                min_num = Math.min(dp[i-1] +1, min_num);
            }
            if (i % 2 == 0){
                min_num = Math.min(dp[i/2] +1, min_num);
            }
            if (i % 3 == 0){
                min_num = Math.min(dp[i/3] +1, min_num);
            }
            if (i<4){
                continue;
            }else if (min_num != 10000000){
                dp[i] = min_num;
            }
        }
        System.out.println(dp[N]);
    }
}
