package 비트마스킹;

import java.io.*;
import java.util.*;

public class b2098_비트마스킹_DP_dfs_기본문제 {
    public static int N;
    public static int[][] pay;
    public static int FULL;
    public static int[][] dp;
    public static int INF = 1000000001;

    public static int tsp(int mask, int now){
        if(mask == FULL){
            // 출발지로 못돌아가는 경우
            // now -> 0 으로 가는길이 0 이면
            if(pay[now][0] == 0) return INF;
                // 가능 한경우. 돌아 갈 수 있음. now -> 0 비용 내려줌. 이후 더할거임.
            else return pay[now][0];
        }

        // 이미 계산한거 그냥 줌. 중복 제거
        if(dp[mask][now] != -1) return dp[mask][now];

        int best = INF;
        for(int node=0; node<N; node++){
            // 1. 갈 수 없는 경우. 자기 자신 포함
            if(pay[now][node] == 0) continue;

            // 2. 이미 방문한 경우
            if((mask & (1<<node)) != 0) continue;

            // 그것도 아니라면 금액 계산
            int nextPay = tsp(mask | (1<<node), node);
            // for 문돌면서 가장 적은 금액 찾기
            best = Math.min(pay[now][node]+nextPay, best);
        }

        dp[mask][now] = best;
        return dp[mask][now];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        pay = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                pay[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1111 과 같은 모두 방문 상태
        FULL = (1 << N)-1;
        dp = new int[1<<N][N];  // N x N 만큼의 1차원 평탄화 배열 에 N 개의 루트별 기록

        // 아직 값이 없는거는 -1 로 표시.
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        int result = tsp(1, 0); // 0번 인덱스를 방문한 상태로 시작 0001, 0
        System.out.println(result);
    }
}

