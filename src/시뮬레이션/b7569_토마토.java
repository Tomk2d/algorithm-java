package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b7569_토마토 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);
        int H = Integer.parseInt(input[2]);

        int[][][] board = new int[H][N][M];
        Deque<Point> queue = new ArrayDeque<>();
        int cnt_0 = 0;

        for (int h = 0; h < H; h++) {
            for (int x = 0; x < N; x++) {
                input = br.readLine().split(" ");
                for (int y = 0; y < M; y++) {
                    int num = Integer.parseInt(input[y]);
                    if (num == 0) {
                        cnt_0++;
                    } else if (num == 1) {
                        queue.add(new Point(x, y, h));
                    }
                    board[h][x][y] = num;
                }
            }
        }

        int[] dx = {0, 0, 1, -1, 0, 0};
        int[] dy = {1, -1, 0, 0, 0, 0};
        int[] dh = {0, 0, 0, 0, 1, -1};

        if (cnt_0 == 0) {
            System.out.println(0);
            return;
        }

        int cnt_day = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int d = 0; d < size; d++) {
                Point q = queue.pollFirst();
                int x = q.x;
                int y = q.y;
                int h = q.h;

                for (int i = 0; i < 6; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    int nh = h + dh[i];

                    if (nx >= 0 && ny >= 0 && nh >= 0 &&
                        nx < N && ny < M && nh < H &&
                        board[nh][nx][ny] == 0) {
                        board[nh][nx][ny] = 1;
                        queue.add(new Point(nx, ny, nh));
                        cnt_0--;
                    }
                }
            }
            cnt_day++;
        }

        System.out.println(cnt_0 == 0 ? cnt_day - 1 : -1);
    }

    public static class Point {
        int x;
        int y;
        int h;

        Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}
