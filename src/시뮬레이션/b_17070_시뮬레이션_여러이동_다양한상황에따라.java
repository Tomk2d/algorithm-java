package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b_17070_시뮬레이션_여러이동_다양한상황에따라 {
    private static int N;
    private static int[][] board;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(count);
    }

    public static void bfs() {
        int[][][] direction = new int[][][]{
            { {0, 1, 0}, {1, 1, 2} },  // 가로 → 오른쪽, 대각선
            { {1, 0, 1}, {1, 1, 2} },  // 세로 → 아래, 대각선
            { {0, 1, 0}, {1, 0, 1}, {1, 1, 2} }  // 대각선 → 오른쪽, 아래, 대각선
        };

        Deque<Pipe> queue = new ArrayDeque<>();
        queue.add(new Pipe(0, 1, 0));

        while (!queue.isEmpty()) {
            Pipe pipe = queue.pollFirst();

            int x = pipe.x;
            int y = pipe.y;
            int dir = pipe.direction;

            if (x == N - 1 && y == N - 1) {
                count++;
                continue;
            }

            for (int i = 0; i < direction[dir].length; i++) {
                int nx = x + direction[dir][i][0];
                int ny = y + direction[dir][i][1];
                int nd = direction[dir][i][2];

                if (isValid(nx, ny, nd)) {
                    queue.add(new Pipe(nx, ny, nd));
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int direction) {
        if (x < 0 || y < 0 || x >= N || y >= N || board[x][y] == 1) {
            return false;
        }

        // 대각선 이동일 경우 추가 체크
        if (direction == 2) {
            if (board[x - 1][y] == 1 || board[x][y - 1] == 1) {
                return false;
            }
        }

        return true;
    }

    private static class Pipe {
        int x, y, direction;
        Pipe(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}

