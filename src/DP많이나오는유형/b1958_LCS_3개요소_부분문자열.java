package DP많이나오는유형;

import java.io.*;

/*
*       이 경우도 똑같다.
*       결국 중요한 것은 요소만큼 배열의 차원을 추가하고,
*       최대치를 갱신해 나가는 것 이다.
*       몇개의 요소가 추가되든 그 비교할 요소들과 모두 일치하는지의 여부만 잘 식별하면 될 듯 하다
*
* */

public class b1958_LCS_3개요소_부분문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word1 = br.readLine();
        String word2 = br.readLine();
        String word3 = br.readLine();
        int N = word1.length();
        int M = word2.length();
        int T = word3.length();

        int[][][] dp = new int[N+1][M+1][T+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                for(int k=1; k<=T; k++){
                    if(word1.charAt(i-1) == word2.charAt(j-1) && word2.charAt(j-1) == word3.charAt(k-1)){
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    }else{
                        int temp = Math.max(dp[i-1][j][k], dp[i][j-1][k]);
                        dp[i][j][k] = Math.max(dp[i][j][k-1], temp);
                    }
                }
            }
        }

        System.out.println(dp[N][M][T]);

    }
}

