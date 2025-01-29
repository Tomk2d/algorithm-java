package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b16929_사각형만들기_시뮬 {
    private static int[] dx = {0, 1, 0, -1};  // 우 하 좌 상
    private static int[] dy = {1, 0, -1, 0};
    private static int N, M;
    private static char[][] board;
    private static boolean[][] visited;
    private static boolean possible = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }


        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (!visited[x][y]) {
                    dfs(x, y, -1, -1, board[x][y], 0);
                    if (possible) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }
        System.out.println("No");
    }

    public static void dfs(int x, int y, int px, int py, char color, int count) {
        if (possible) return;

        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx == px && ny == py) continue;

            if (check(nx, ny) && board[nx][ny] == color) {
                if (visited[nx][ny]) {
                    if (count >= 3) {
                        possible = true;
                        return;
                    }
                } else {
                    dfs(nx, ny, x, y, color, count + 1);
                }
            }
        }
    }

    private static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}

