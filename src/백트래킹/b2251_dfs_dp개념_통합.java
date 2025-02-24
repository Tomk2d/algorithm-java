package 백트래킹;

import java.util.*;
import java.io.*;

public class b2251_dfs_dp개념_통합 {
    private static int[] maxArray = new int[3];
    private static Set<Integer> set = new TreeSet<>();
    private static boolean[][] visited = new boolean[201][201]; // 방문 체크 (A, B 기준)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        maxArray[0] = A;
        maxArray[1] = B;
        maxArray[2] = C;

        dfs(0, 0, C);

        for (int num : set) {
            System.out.print(num + " ");
        }
    }

    private static void dfs(int a, int b, int c) {
        // 물의 양 기준으로 방문했는지. 즉, 남긴물 10 -> 5 로 이동한적 있는지
        if (visited[a][b]) return;

        visited[a][b] = true;

        if (a == 0) {
            set.add(c);
        }

        // 모든경우의 수
        cal(a, b, c, 0, 1);
        cal(a, b, c, 0, 2);
        cal(a, b, c, 1, 0);
        cal(a, b, c, 1, 2);
        cal(a, b, c, 2, 0);
        cal(a, b, c, 2, 1);
    }

    private static void cal(int a, int b, int c, int from, int to) {
        // 현재 상태
        int[] nowArray = {a, b, c};

        int moveWater = Math.min(nowArray[from], maxArray[to] - nowArray[to]);
        // 출발지 -
        nowArray[from] -= moveWater;
        // 도착지 +
        nowArray[to] += moveWater;

        dfs(nowArray[0], nowArray[1], nowArray[2]);
    }
}

