package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b5558_bfs_순차적으로탐색 {
    private static int H, W, N;
    private static char[][] board;
    private static int startX, startY;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new char[H][W];

        for (int i = 0; i < H; i++) {
            String row = br.readLine();
            for (int j = 0; j < W; j++) {
                board[i][j] = row.charAt(j);
                if (board[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        int totalTime = 0;
        for (int cheese = 1; cheese <= N; cheese++) {
            Result result = bfs(startX, startY, (char) ('0' + cheese));
            totalTime += result.time;
            startX = result.x;
            startY = result.y;
        }

        System.out.println(totalTime);
    }

    private static Result bfs(int sx, int sy, char target) {
        boolean[][] visited = new boolean[H][W];
        Queue<Result> q = new LinkedList<>();
        q.add(new Result(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Result now = q.poll();

            if (board[now.x][now.y] == target) {
                return now;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if (visited[nx][ny] || board[nx][ny] == 'X') continue;

                visited[nx][ny] = true;
                q.add(new Result(nx, ny, now.time + 1));
            }
        }

        return null; // 도달 불가한 경우 없음
    }

    private static class Result {
        int x, y, time;
        Result(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}

