package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b10026_묶음서로다른조건 {
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int normal_cnt = 0;
    private static int unNormal_cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[][] board = new String[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                board[i][j] = input[j];
            }
        }

        boolean[][] normal_visited = new boolean[N][N];
        boolean[][] unNormal_visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 일반인 영역 탐색
                if (!normal_visited[i][j]) {
                    dfs(i, j, board[i][j], true, normal_visited, board, N);
                    normal_cnt++;
                }
                // 색맹 영역 탐색
                if (!unNormal_visited[i][j]) {
                    dfs(i, j, board[i][j], false, unNormal_visited, board, N);
                    unNormal_cnt++;
                }
            }
        }

        System.out.print(normal_cnt + " ");
        System.out.print(unNormal_cnt);
    }

    public static void dfs(int x, int y, String color, boolean normal, boolean[][] visited, String[][] board, int N) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                if (normal) { // 일반인
                    if (board[nx][ny].equals(color)) {
                        dfs(nx, ny, color, normal, visited, board, N);
                    }
                } else { // 색맹
                    if ((color.equals("G") || color.equals("R")) &&
                        (board[nx][ny].equals("G") || board[nx][ny].equals("R"))) {
                        dfs(nx, ny, color, normal, visited, board, N);
                    } else if (board[nx][ny].equals(color)) {
                        dfs(nx, ny, color, normal, visited, board, N);
                    }
                }
            }
        }
    }
}
