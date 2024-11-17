package DP;

import java.util.*;
import java.io.*;

public class b10870 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];


        for(int i=0; i < dp.length; i++){
            if(i == 0) dp[i] = 0;
            else if(i == 1) dp[i] = 1;
            else dp[i] = dp[i-2] + dp[i-1];
        }

        System.out.println(dp[N]);
    }
}
