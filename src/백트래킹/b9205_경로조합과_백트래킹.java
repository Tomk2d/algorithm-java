package 백트래킹;

import java.util.*;
import java.io.*;

public class b9205_경로조합과_백트래킹 {
    private static int oilstationN;
    private static int[][] maps;
    private static boolean[] visited;
    private static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());

        while (testCase-- > 0) {
            oilstationN = Integer.parseInt(br.readLine());
            maps = new int[oilstationN + 2][2];
            visited = new boolean[oilstationN + 2];
            isPossible = false;

            // 시작점 입력
            st = new StringTokenizer(br.readLine());
            maps[0][0] = Integer.parseInt(st.nextToken());
            maps[0][1] = Integer.parseInt(st.nextToken());

            // 주유소 입력
            for (int i = 1; i <= oilstationN; i++) {
                st = new StringTokenizer(br.readLine());
                maps[i][0] = Integer.parseInt(st.nextToken());
                maps[i][1] = Integer.parseInt(st.nextToken());
            }

            // 도착점 입력
            st = new StringTokenizer(br.readLine());
            maps[oilstationN + 1][0] = Integer.parseInt(st.nextToken());
            maps[oilstationN + 1][1] = Integer.parseInt(st.nextToken());

            dfs(0);

            System.out.println(isPossible ? "happy" : "sad");
        }
    }

    private static void dfs(int idx) {
        if (idx == oilstationN + 1) {
            isPossible = true;
            return;
        }

        visited[idx] = true;

        for (int i = 1; i <= oilstationN + 1; i++) {
            if (!visited[i]) {
                int distance = Math.abs(maps[idx][0] - maps[i][0]) + Math.abs(maps[idx][1] - maps[i][1]);
                if (distance <= 1000) {
                    dfs(i);
                }
            }
        }
    }
}

