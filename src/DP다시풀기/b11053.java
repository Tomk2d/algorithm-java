package DP다시풀기;

import java.util.*;
import java.io.*;

public class b11053 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] dp = new int[N];
        Arrays.fill(dp,1);

        int max = 1;

        for (int i = 1; i < N; i++) {   // 현재 검사하는 요소
            int n = Integer.parseInt(input[i]);

            for(int j=0;j<i;j++){       // 먼저 검사했던애
               int m = Integer.parseInt(input[j]);
               if (n > m){
                   dp[i] = Math.max(dp[i],dp[j]+1);
               }
            }
            max = Math.max(max,dp[i]);
        }

        System.out.println(max);
    }
}
