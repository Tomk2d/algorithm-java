package 비트마스킹;

import java.util.*;
import java.io.*;

public class b16991_비트마스킹_거리계산까지
{
    public static double INF = 10_000_001;
    public static int FULL;
    public static int N;
    public static double[][] pay;
    public static double[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        pay = new double[N][N];

        int[][] positions = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            positions[i][0] = x;
            positions[i][1] = y;
        }

        for(int i=0; i<N; i++){
            int x1 = positions[i][0];
            int y1 = positions[i][1];
            for(int j=0; j<N; j++){
                if(i==j) continue;

                int x2 = positions[j][0];
                int y2 = positions[j][1];

                pay[i][j] = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
            }
        }

        FULL = (1<<N) - 1;
        dp = new double[1<<N][N];

        for(double[] row : dp){
            Arrays.fill(row, -1);
        }

        double answer = tsp(0, 1);
        System.out.println(answer);
    }

    public static double tsp(int now, int mask){
        if(mask == FULL){
            if(pay[now][0] == 0) return INF;

            else return pay[now][0];
        }

        if(dp[mask][now] != -1) return dp[mask][now];

        double best = INF;
        for(int next=0; next<N; next++){
            if(pay[now][next] == 0) continue;

            if((mask & (1<<next)) != 0) continue;

            double nextCost = tsp(next, mask|(1<<next));
            best = Math.min(best, pay[now][next] + nextCost);
        }

        dp[mask][now] = best;
        return dp[mask][now];
    }
}

