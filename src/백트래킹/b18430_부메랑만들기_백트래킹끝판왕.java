package 백트래킹;

import java.util.*;
import java.io.*;

public class b18430_부메랑만들기_백트래킹끝판왕 {
    private static int maxScore = 0;
    private static int[][] board;
    private static int N, M;

    // 4가지 부메랑 모양 방향
    private static int[][][] direction = {
        {{-1, 0}, {0, -1}}, // 왼쪽, 위
        {{-1, 0}, {0, 1}},  // 오른쪽, 위
        {{0, -1}, {1, 0}},  // 왼쪽, 아래
        {{1, 0}, {0, 1}}    // 오른쪽, 아래
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];
        backtrack(0, 0, 0, visited);

        System.out.println(maxScore);
    }

    // 백트래킹으로 모든 블럭 들어가서, 부메랑 배치 가능한것만 계산
    public static void backtrack(int x, int y, int nowScore, boolean[][] visited) {
        if (x == N) { // 끝까지 탐색했을 경우
            maxScore = Math.max(maxScore, nowScore);
            return;
        }

        int nx = (y == M-1) ? x+1 : x;  //  가로로 끝까지 가면 아래로 한칸
        int ny = (y == M-1) ? 0 : y+1;  //  가로로 끝까지 가면 가로 첫번째 칸으로 이동.

        // 부메랑 배치 가능한 경우 찾기
        if (!visited[x][y]) {
            for (int i = 0; i < 4; i++) {
                int bx = x + direction[i][0][0];
                int by = y + direction[i][0][1];
                int cx = x + direction[i][1][0];
                int cy = y + direction[i][1][1];

                if (isValid(bx, by, cx, cy, visited)) {
                    // 방문 처리
                    visited[x][y] = visited[bx][by] = visited[cx][cy] = true;
                    int score = board[x][y] * 2 + board[bx][by] + board[cx][cy];
                    // 탐색
                    backtrack(nx, ny, nowScore + score, visited);

                    visited[x][y] = visited[bx][by] = visited[cx][cy] = false;
                }
            }
        }

        // 부메랑을 놓지 않고 다음 위치 탐색
        backtrack(nx, ny, nowScore, visited);
    }

    // 유효한 위치인지 확인
    public static boolean isValid(int x1, int y1, int x2, int y2, boolean[][] visited) {
        return x1 >= 0 && x1 < N && y1 >= 0 && y1 < M &&
            x2 >= 0 && x2 < N && y2 >= 0 && y2 < M &&
            !visited[x1][y1] && !visited[x2][y2];
    }
}

