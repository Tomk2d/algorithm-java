package 시뮬레이션;

import java.util.*;
import java.io.*;

/*
    * 이 문제는 불의 위치와 움직이는 턴을 맞춰서 해야한다.
*
* */

public class b4179_bfs_빡구현_위치관리 {
    private static int N, M;
    private static int[][] board; // 0: 빈 공간, 1: 벽
    private static int[][] fireTime;
    private static int[][] jihoonTime;
    private static int characterX, characterY;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static Queue<Point> fireQueue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        fireTime = new int[N][M];
        jihoonTime = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(fireTime[i], -1);
            Arrays.fill(jihoonTime[i], -1);
        }

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                if (input[j].equals("#")) board[i][j] = 1;
                else if (input[j].equals(".")) board[i][j] = 0;
                else if (input[j].equals("J")) {
                    characterX = i;
                    characterY = j;
                    board[i][j] = 0;
                    jihoonTime[i][j] = 0;
                } else if (input[j].equals("F")) {
                    board[i][j] = 0;
                    fireQueue.add(new Point(i, j, 0));
                    fireTime[i][j] = 0;
                }
            }
        }

        spreadFire(); // 먼저 불 퍼뜨리기

        int result = moveJihoon();
        System.out.println(result == -1 ? "IMPOSSIBLE" : result + 1);
    }

    private static void spreadFire() {
        while (!fireQueue.isEmpty()) {
            Point now = fireQueue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (board[nx][ny] == 0 && fireTime[nx][ny] == -1) {
                        fireTime[nx][ny] = now.cnt + 1;
                        fireQueue.add(new Point(nx, ny, now.cnt + 1));
                    }
                }
            }
        }
    }

    private static int moveJihoon() {
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(characterX, characterY, 0));

        while (!queue.isEmpty()) {
            Point now = queue.pollFirst();

            // 탈출 조건: 가장자리 도달
            if (now.x == 0 || now.x == N - 1 || now.y == 0 || now.y == M - 1) {
                return now.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (board[nx][ny] == 0 && jihoonTime[nx][ny] == -1) {
                        // 불이 없거나 불보다 먼저 도착 가능한 경우만
                        if (fireTime[nx][ny] != -1 && fireTime[nx][ny] <= now.cnt + 1) continue;

                        jihoonTime[nx][ny] = now.cnt + 1;
                        queue.add(new Point(nx, ny, now.cnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    private static class Point {
        int x, y, cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

