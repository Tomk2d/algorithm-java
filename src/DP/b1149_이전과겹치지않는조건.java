package DP;

import java.io.*;

// 이문제를 풀 때, 최소값을 pay 를 기준으로 해서 틀렸다.
// 그게 아니라 내가 지금 저장할 위치에 이전 값들중, 최소값을 저장한다.
// 웬만한 최소는 이전것들 사이에서 정한다는것을 잊지말자
public class b1149_이전과겹치지않는조건 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] pay = new int[N][3];

        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            for(int j=0; j<3; j++){
                pay[i][j] = Integer.parseInt(input[j]);
            }
        }

        int[][] dp = new int[N][3];

        dp[0][0] = pay[0][0];
        dp[0][1] = pay[0][1];
        dp[0][2] = pay[0][2];

        for(int i=1; i<N; i++){
            // ex) 현재 집이 빨강일때는, 이전집이 파랑, 초록이어야 함.
            dp[i][0] = pay[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = pay[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = pay[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        int minPay = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            minPay = Math.min(minPay, dp[N-1][i]);
        }
        System.out.println(minPay);
    }
}

