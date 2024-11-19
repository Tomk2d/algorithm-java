package DP다시풀기;

import java.util.*;
import java.io.*;

public class b1463_누적합나누기개념 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        if (N > 1) dp[2] = 1;

        for (int i = 3; i <= N; i++) {
            int min_num = 10000001;
            min_num = Math.min(min_num, dp[i-1]+1);
            if (i % 2 == 0) min_num = Math.min(min_num, dp[i/2] +1);
            if (i % 3 == 0) min_num = Math.min(min_num, dp[i/3] +1);
            dp[i] = min_num;
        }
        System.out.println(dp[N]);
    }
}
