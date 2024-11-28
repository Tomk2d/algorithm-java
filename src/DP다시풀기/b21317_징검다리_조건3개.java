package DP다시풀기;

import java.util.*;
import java.io.*;

public class b21317_징검다리_조건3개 {

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] stones = new int[N][2];

        for (int i = 1; i < N; i++) {
            String[] input = br.readLine().split(" ");
            stones[i][0] = Integer.parseInt(input[0]);
            stones[i][1] = Integer.parseInt(input[1]);
        }
        int K = Integer.parseInt(br.readLine());    //  최종 점프

        int[] dp = new int[N];      //  여기까지 올때 최소값

        dp[1] = stones[1][0];      // 사실상 두번째칸
        dp[2] = Math.min(dp[1]+stones[2][0], stones[2][1]);

        int max_cost = 0;

        for (int i = 3; i < N; i++) {
            dp[i] = Math.min(dp[i-1]+stones[i][0], dp[i-2]+stones[i][1]);   // 한칸 뛸때랑 두칸 뛸때중 최소값 계산
            max_cost = Math.max(max_cost, dp[i] - dp[i-3]);     // 슈퍼점프 뛸 수 있는 구간중, 제일 높은 비용 선정. 비용 = 현재까지 비용 - 3칸 전 비용
        }

        if (max_cost >= K){
            System.out.println(dp[N-1] - max_cost + K);
        }else{
            System.out.println(dp[N-1]);
        }
    }
}
