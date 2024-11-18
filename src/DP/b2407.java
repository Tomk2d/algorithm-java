package DP;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class b2407 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        BigInteger[][] dp = new BigInteger[N + 1][M + 1];

        // 초기화
        for (int n = 0; n <= Math.min(N,M); n++) {
            dp[n][0] = BigInteger.ONE; // 아무 것도 선택하지 않는 경우
            dp[n][n] = BigInteger.ONE; // 모두 선택하는 경우
        }

        // dp 점화식 계산
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= Math.min(n, M); m++) { // m의 범위를 제한
                dp[n][m] = dp[n - 1][m - 1].add(dp[n - 1][m]);
            }
        }
        System.out.println(dp[N][M]);
    }
}
