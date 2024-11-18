package DP;

import java.io.*;

public class b1912_배열의크기가_커서_틀림 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");

        int[] array = new int[N+1];
        for (int i = 1; i <= N; i++) {
            array[i] = Integer.parseInt(input[i-1]);
        }

        int[][] dp = new int[N+1][N+1];

        int max = 0;

        for(int i=1;i<=N;i++){
            dp[1][i] = array[i];
            Math.max(array[i], max);
        }

        for(int i=2;i<=N;i++){
            for(int j=i;j<=N;j++){
                int num = dp[i-1][j-1] + dp[1][j];
                dp[i][j] = num;
                max = Math.max(max, num);
            }
        }

        System.out.println(max);
    }
}
