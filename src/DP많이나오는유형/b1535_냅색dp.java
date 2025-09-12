package DP많이나오는유형;

import java.util.*;
import java.io.*;

public class b1535_냅색dp
{
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[] costs = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int[] values = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }


        int[] dp = new int[100];

        for(int i=0; i<N; i++){
            int selectedCost = costs[i];
            int selectedValue = values[i];

            for(int cost=99; cost>=selectedCost; cost--){
                dp[cost] = Math.max(dp[cost], dp[cost - selectedCost] + selectedValue);
            }
        }

        System.out.println(dp[99]);

    }
}

