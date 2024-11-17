package DP;

import java.util.*;
import java.io.*;

public class b1010 {
    private static final int MAX = 31;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[MAX + 1][MAX + 1];  // 배열 크기를 MAX+1로 수정

        for (int j = 0; j <= MAX; j++) {
            dp[0][j] = 1;  // 0번 행 초기화
        }

        for (int i = 1; i <= MAX; i++) {  // 행 범위도 MAX까지
            for (int j = i; j <= MAX; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            if (i != N-1) {
                sb.append(dp[n][m]).append("\n");  // n, m 값 그대로 사용
            } else {
                sb.append(dp[n][m]);
            }
        }

        System.out.println(sb.toString());
    }
}
