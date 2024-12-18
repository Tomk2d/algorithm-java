package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b14503_bfs_로봇청소기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        String[] input_direction = br.readLine().split(" ");
        int start_x = Integer.parseInt(input_direction[0]);
        int start_y = Integer.parseInt(input_direction[1]);
        int direction = Integer.parseInt(input_direction[2]);

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }
        int result = simulation(board, N, M, start_x, start_y, direction);
        System.out.println(result);
    }

    public static class Point {
        int x;
        int y;
        int now_direction;

        Point(int x, int y, int now_direction) {
            this.x = x;
            this.y = y;
            this.now_direction = now_direction;
        }
    }

    public static int simulation(int[][] board, int N, int M, int start_x, int start_y, int start_direction) {
        int[][] directions = {
            {-1, 0},  // 북
            {0, 1},   // 동
            {1, 0},   // 남
            {0, -1}   // 서
        };
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(start_x, start_y, start_direction));

        int cnt = 0;

        while (!queue.isEmpty()) {
            Point point = queue.pollFirst();
            int x = point.x;
            int y = point.y;
            int now_direction = point.now_direction;

            // 1. 현재 칸이 청소되지 않은 경우 청소
            if (board[x][y] == 0) {
                cnt++;
                board[x][y] = 2; // 청소된 칸
            }

            // 2. 네 방향 검사
            boolean cleaned = false;
            for (int i = 0; i < 4; i++) {
                now_direction = (now_direction + 3) % 4; // 왼쪽 방향
                int nx = x + directions[now_direction][0];
                int ny = y + directions[now_direction][1];

                // 청소되지 않은 빈칸이 있으면 이동
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && board[nx][ny] == 0) {
                    queue.add(new Point(nx, ny, now_direction));
                    cleaned = true;
                    break;
                }
            }

            // 3. 네 방향 모두 청소가 되어 있거나 벽인 경우
            if (!cleaned) {
                int back_direction = (now_direction + 2) % 4; // 후진 하기
                int bx = x + directions[back_direction][0];
                int by = y + directions[back_direction][1];

                // 뒤로 이동할 수 있으면 이동, 아니면 종료
                if (bx >= 0 && by >= 0 && bx < N && by < M && board[bx][by] != 1) {
                    queue.add(new Point(bx, by, now_direction));
                } else {
                    break;
                }
            }
        }

        return cnt;
    }
}
