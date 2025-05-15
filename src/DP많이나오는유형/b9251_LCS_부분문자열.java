package DP많이나오는유형;

import java.io.*;

/*
*       가장 많이 나오는 LCS 유형이다
*       이 문제는 기본적으로 암기하는게 좋을 거 같다
*       dp 계산하는 과정을 매번 매번 짜는데 시간이 오래걸릴듯 하다
* */

public class b9251_LCS_부분문자열 {
    private static int N;
    private static int[] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word1 = br.readLine();
        String word2 = br.readLine();
        int N = word1.length();
        int M = word2.length();

        int[][] dp = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}

