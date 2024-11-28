package DP다시풀기;

import java.io.*;
import java.util.*;

public class b11722_감소하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        int max = 1;

        for(int i =1; i<N; i++){
            for(int j =0; j<i; j++){
                if (numbers[i] < numbers[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }

}
