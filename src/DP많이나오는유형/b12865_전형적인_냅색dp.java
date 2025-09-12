package DP많이나오는유형;

import java.util.*;
import java.io.*;

public class b12865_전형적인_냅색dp
{
    private static int N, limit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        int[] weights = new int[N];
        int[] values = new int[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            weights[i] = weight;
            values[i] = value;
        }

        // index = weight, value = 가치
        int[] dp = new int[limit+1];

        for(int i=0; i<N; i++){
            int selectedWeight = weights[i];
            int selectedValue = values[i];

            for(int weight=limit; weight>=selectedWeight; weight--){
                dp[weight] = Math.max(dp[weight], dp[weight-selectedWeight] + selectedValue);
            }
        }

        System.out.println(dp[limit]);

    }
}

