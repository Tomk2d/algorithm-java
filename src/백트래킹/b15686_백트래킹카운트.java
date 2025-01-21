package 백트래킹;

import java.util.*;
import java.io.*;

public class b15686_백트래킹카운트 {

    static int N, M;
    static int[][] board;
    static List<Point> homes = new ArrayList<>();
    static List<Point> chickenStores = new ArrayList<>();
    static boolean[] visited;
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    homes.add(new Point(i, j));
                } else if (board[i][j] == 2) {
                    chickenStores.add(new Point(i, j));
                }
            }
        }

        visited = new boolean[chickenStores.size()];
        dfs(0, 0);
        System.out.println(minDistance);
    }

    public static void dfs(int idx, int cnt) {
        if (cnt == M) {
            minDistance = Math.min(minDistance, calculateDistance());
            return;
        }

        for (int i = idx; i < chickenStores.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static int calculateDistance() {
        int totalDistance = 0;

        for (Point home : homes) {
            int homeDistance = Integer.MAX_VALUE;
            for (int i = 0; i < chickenStores.size(); i++) {
                if (visited[i]) {
                    Point chicken = chickenStores.get(i);
                    homeDistance = Math.min(homeDistance, Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y));
                }
            }
            totalDistance += homeDistance;
        }

        return totalDistance;
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

