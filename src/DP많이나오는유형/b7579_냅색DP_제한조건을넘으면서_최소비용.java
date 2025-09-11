package DP많이나오는유형;

import java.util.*;
import java.io.*;

public class b7579_냅색DP_제한조건을넘으면서_최소비용
{
    private static int N, limit;
    private static int minCost = Integer.MAX_VALUE;
    private static int[] memories;
    private static int[] costs;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        memories = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            memories[i] = Integer.parseInt(st.nextToken());
        }

        costs = new int[N];
        int maxCost = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            costs[i] = Integer.parseInt(st.nextToken());
            maxCost += costs[i];
        }

        // 즉 dp[cost] = memory 다.
        // 헷갈릴 수 있는게, 냅색 dp 에서는 index에 정답을 저장한다.
        // 그리고 value 에 조건을 저장한다.
        int[] dp = new int[maxCost + 1];    // 그래서 index 최대값이 모든 경우를 다 가진 cost

        for(int i=0; i<N; i++){
            int selectedCost = costs[i];
            int selectedMemory = memories[i];
            for(int cost=maxCost; cost>=selectedCost; cost--){
                dp[cost] = Math.max(dp[cost], dp[cost - selectedCost] + selectedMemory);
            }
        }
        for(int cost=0; cost<=maxCost; cost++){
            int nowMemory = dp[cost];
            if(nowMemory >= limit){
                System.out.println(cost);
                break;
            }
        }
    }
}

