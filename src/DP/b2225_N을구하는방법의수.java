package DP;

import java.io.*;

// 이런 경우의 수나 이전 값들을 포함하는 경우를 구해야하면
// DP 로 표를 그려보고 하자.

public class b2225_N을구하는방법의수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N과 K 입력
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        // 1,000,000,000으로 나눈 나머지를 출력해야 하므로 나누는 값을 미리 정의
        final long MOD = 1000000000L;

        // dp[i][j]는 j개의 수로 i를 만드는 경우의 수 (long 타입 사용)
        long[][] dp = new long[N + 1][K + 1];

        // dp[0][0] = 1: 0을 0개로 만드는 방법은 1가지
        dp[0][0] = 1;

        // DP 배열 채우기
        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (i > 0) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD; // i-1을 j개의 수로 만들고, 여기에 1을 더한 경우
                }
                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i][j-1]) % MOD; // i를 j-1개의 수로 만들고, 마지막 수를 추가한 경우
                }
            }
        }

        // 결과 출력
        System.out.println(dp[N][K]);
    }
}

