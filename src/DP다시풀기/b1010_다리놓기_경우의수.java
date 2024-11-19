package DP다시풀기;

import java.util.*;
import java.io.*;

public class b1010_다리놓기_경우의수 {

    public static int solution(int n, int m){
        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= m; i++){
            dp[0][i] = 1;
        }

        for (int i=1; i<=n; i++){
            for (int j=i; j<=m; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            System.out.println(solution(n,m));
        }
    }
}
