package 비트마스킹;

import java.util.*;
import java.io.*;

public class b2098_tsp_비트마스킹_정석문제
{
    static final int INF = 1_000_000_000;
    private static int N;
    private static int[][] pay;
    private static int FULL;
    private static int dp[][];

    private static int tsp(int mask, int now){
        if(mask == FULL){
            // 도착지에서(now 가 도착지임. mask full 이니까) 출발지 못가면 안됨.
            if(pay[now][0] == 0) return INF;
                // now 에서 출발지로 가는 비용 return 해서 더 해줌
            else return pay[now][0];
        }

        // dp 에 저장된 결과가 있으면 바로 리턴
        if(dp[mask][now] != -1) return dp[mask][now];

        int best = INF;
        for(int node=0; node<N; node++){
            // (1 << node) : 01000 처럼 해당 node 만 1로나옴
            // mask & (1 << node) :  랑 and 비트 연산을 수행함.
            // 결국 01000 처럼 1자리만 검사하는 로직이 나오는거임.
            // 그래서, 해당 부분 방문했는지 체크
            if((mask & (1 << node)) != 0) continue;

            // 갈 수 없는 경우
            if(pay[now][node] == 0) continue;

            // (mask | (1<<node)) : 이전 방문(mask) + 현재 노드
            // 즉, 방문 체크해서 함수 호출
            int next = tsp(mask | (1 << node), node);

            if(next != INF){
                best = Math.min(best, pay[now][node] + next);
            }
        }

        return dp[mask][now] = best;
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

        // 0001 을 N칸 만큼 왼쪽으로 밈. 3이면 1000
        // 이후 -1 해서 모든칸을 방문으로 만듬. 1111 (자신 포함 모든 방문. 애초에 2^0 이 0001 이니까.)
        FULL = (1<<N) - 1;
        dp = new int[1<<N][N];

        for(int[] row : dp){
            Arrays.fill(row, -1);   //  비용 처리. 비용이 양수니까 -1로 초기값
        }

        int result = tsp(1, 0);
        System.out.println(result);
    }
}

