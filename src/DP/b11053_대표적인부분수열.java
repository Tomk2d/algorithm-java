package DP;

import java.io.*;
import java.util.Arrays;

public class b11053_대표적인부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        int max = 1;

        for(int i=1;i<N;i++){
            for(int j =0; j<i; j++){
                if (array[i] > array[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max,dp[i]);
        }
        System.out.println(max);

    }
}
