package DP;

import java.util.*;
import java.io.*;

/*
이 문제는 1, 2, 3 만을 사용해서 해당 수를 몇개의 방법으로 만들 수 있나 구하는 것 이다.
문제 자체의 경우의 수를 찾는데 시간이 걸렸다.
중복도 없어야 하기에 헷갈린다. 점화식 세우는 연습좀 해보자!
 */

public class b15989_1과2와3으로_만드는_경우의수_중복없이
{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[10001];
        Arrays.fill(dp, 1);

        for(int i=2; i<10001; i++){
            dp[i] += dp[i-2];
        }
        for(int i=3; i<10001; i++){
            dp[i] += dp[i-3];
        }

        while(N-->0){
            int target = Integer.parseInt(br.readLine());
            System.out.println(dp[target]);
        }
    }
}

