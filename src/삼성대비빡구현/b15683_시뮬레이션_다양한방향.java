package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b15683_시뮬레이션_다양한방향 {
    private static int[] dx = {1, -1, 0, 0}; // 하, 상, 좌, 우
    private static int[] dy = {0, 0, -1, 1};

    private static final int[][][] dirs = {
        {},
        {{0}, {1}, {2}, {3}},                           // 1번 CCTV
        {{0, 1}, {2, 3}},                               // 2번 (하-상, 좌-우)
        {{0, 2}, {2, 1}, {1, 3}, {3, 0}},               // 3번
        {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},   // 4번
        {{0, 1, 2, 3}}                                  // 5번
    };

    private static int N, M;
    private static int[][] board;
    private static List<CCTV> cctvs = new ArrayList<>();
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num >= 1 && num <= 5) {
                    cctvs.add(new CCTV(num, i, j));
                }
            }
        }

        dfs(0, board);
        System.out.println(answer);
    }

    private static void dfs(int idx, int[][] map) {
        if (idx == cctvs.size()) {
            answer = Math.min(answer, countBlindSpot(map));
            return;
        }

        CCTV now = cctvs.get(idx);
        int[][] dirArray = dirs[now.type];

        for (int[] dirSet : dirArray) {
            int[][] copyMap = copyBoard(map);
            for (int d : dirSet) {
                applyWatch(copyMap, now.x, now.y, d);
            }
            dfs(idx + 1, copyMap);
        }
    }

    private static void applyWatch(int[][] map, int x, int y, int dir) {
        int nx = x;
        int ny = y;

        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
            if (map[nx][ny] == 6) break; // 벽

            if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
            }
        }
    }

    private static int[][] copyBoard(int[][] map) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    private static int countBlindSpot(int[][] map) {
        int count = 0;
        for (int[] row : map) {
            for (int val : row) {
                if (val == 0) count++;
            }
        }
        return count;
    }

    private static class CCTV {
        int type, x, y;

        CCTV(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
}

