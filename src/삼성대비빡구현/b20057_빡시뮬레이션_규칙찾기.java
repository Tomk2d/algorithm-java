package 삼성대비빡구현;

import java.io.*;
import java.util.*;

public class b20057_빡시뮬레이션_규칙찾기 {
    private static int answer = 0;
    private static int N;
    private static int[][] board;

    // 좌 하 우 상
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulation();
        System.out.println(answer);
    }

    private static void simulation() {
        Deque<Tornado> queue = new ArrayDeque<>();
        queue.add(new Tornado(N / 2, N / 2, 0));

        int tornadoMove = 1;
        int tornadoCnt = tornadoMove;
        int isTwice = 0;

        while (!queue.isEmpty()) {
            Tornado now = queue.pollFirst();
            int nx = now.x + dx[now.d];
            int ny = now.y + dy[now.d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) break;

            updateBoard(nx, ny, now.d, board[nx][ny]);
            board[nx][ny] = 0;

            tornadoCnt--;
            if (tornadoCnt == 0) {
                isTwice++;
                queue.add(new Tornado(nx, ny, (now.d + 1) % 4));
                if (isTwice == 2) {
                    tornadoMove++;
                    tornadoCnt = tornadoMove;
                    isTwice = 0;
                } else {
                    tornadoCnt = tornadoMove;
                }
            } else {
                queue.add(new Tornado(nx, ny, now.d));
            }
        }
    }

    private static void updateBoard(int x, int y, int d, int amount) {
        int[][][] windByDir = {
            {   // ←
                {-1, 1, 1}, {1, 1, 1},
                {-1, 0, 7}, {1, 0, 7},
                {-1, -1, 10}, {1, -1, 10},
                {0, -2, 5},
                {-2, 0, 2}, {2, 0, 2}
            },
            {   // ↓
                {-1, -1, 1}, {-1, 1, 1},
                {0, -1, 7}, {0, 1, 7},
                {1, -1, 10}, {1, 1, 10},
                {2, 0, 5},
                {0, -2, 2}, {0, 2, 2}
            },
            {   // →
                {-1, -1, 1}, {1, -1, 1},
                {-1, 0, 7}, {1, 0, 7},
                {-1, 1, 10}, {1, 1, 10},
                {0, 2, 5},
                {-2, 0, 2}, {2, 0, 2}
            },
            {   // ↑
                {1, -1, 1}, {1, 1, 1},
                {0, -1, 7}, {0, 1, 7},
                {-1, -1, 10}, {-1, 1, 10},
                {-2, 0, 5},
                {0, -2, 2}, {0, 2, 2}
            }
        };

        int sand = amount;
        for (int[] wind : windByDir[d]) {
            int nx = x + wind[0];
            int ny = y + wind[1];
            int moveAmount = amount * wind[2] / 100;
            sand -= moveAmount;

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                answer += moveAmount;
            } else {
                board[nx][ny] += moveAmount;
            }
        }

        // 나머지 모래는 전방 방향으로 (alpha)
        int alphaX = x + dx[d];
        int alphaY = y + dy[d];
        if (alphaX < 0 || alphaY < 0 || alphaX >= N || alphaY >= N) {
            answer += sand;
        } else {
            board[alphaX][alphaY] += sand;
        }
    }

    private static class Tornado {
        int x, y, d;
        Tornado(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}

