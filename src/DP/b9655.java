package DP;

import java.util.*;
import java.io.*;

public class b9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[N+1];
        dp[1] = true;   // true = 상근
        dp[2] = false;   // false = 찬영
        dp[3] = true;

        if(N < 4){
            if (dp[N]) {
                System.out.println("SK");
            }else{
                System.out.println("CY");
            }
        }

        for(int i=4;i<=N;i++){
            dp[i] = !dp[i-1];
        }
        if (dp[N]) {
            System.out.println("SK");
        }else{
            System.out.println("CY");
        }
    }

}
