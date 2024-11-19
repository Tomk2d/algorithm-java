package DP다시풀기;

import java.io.*;

public class b10870_피보나치 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        dp[1] = 1;
        if(N == 0) {
            System.out.println(0);
        }
        else{
            for(int i=2;i<=N;i++){
                dp[i] = dp[i-2] + dp[i-1];
            }
            System.out.println(dp[N]);
        }

    }
}
