package 비트마스킹;

import java.io.*;
import java.util.*;

public class b16385_그룹_비트마스킹 {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine().trim()); // 포켓스탑 개수
        int n = m + 1; // 출발지 포함

        int[][] pos = new int[n][2];
        int[] pokeId = new int[n];
        pos[0][0] = 0; pos[0][1] = 0;
        pokeId[0] = -1; // 출발지는 포켓몬 없음

        Map<String, Integer> idMap = new HashMap<>();
        int uniqueCount = 0;

        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            pos[i][0] = r;
            pos[i][1] = c;

            if (!idMap.containsKey(name)) {
                idMap.put(name, uniqueCount++);
            }
            pokeId[i] = idMap.get(name);
        }

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);
            }
        }

        int FULL = (1 << uniqueCount) - 1;
        int[][] dp = new int[1 << uniqueCount][n];
        for (int[] row : dp) Arrays.fill(row, INF);

        // 시작점 (0,0) 에서 시작
        dp[0][0] = 0;

        for (int mask = 0; mask < (1 << uniqueCount); mask++) {
            for (int u = 0; u < n; u++) {
                if (dp[mask][u] == INF) continue;

                for (int v = 0; v < n; v++) {
                    if (u == v) continue;
                    int newMask = mask;
                    if (pokeId[v] != -1) newMask |= (1 << pokeId[v]);
                    dp[newMask][v] = Math.min(dp[newMask][v], dp[mask][u] + dist[u][v]);
                }
            }
        }

        // 모든 포켓몬을 잡은 상태에서 출발지(0)로 복귀
        int ans = INF;
        for (int u = 0; u < n; u++) {
            if (dp[FULL][u] < INF) {
                ans = Math.min(ans, dp[FULL][u] + dist[u][0]);
            }
        }

        System.out.println(ans);
    }
}

