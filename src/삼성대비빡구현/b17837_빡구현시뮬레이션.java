package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b17837_빡구현시뮬레이션 {
    private static int[][] board;       // 0 : 흰색, 1 : 빨강 , 2 : 파랑
    private static int N, K;
    private static List<Integer>[][] memberBoard;
    private static int[] dx = {0, 0, -1, 1};    // 우좌상하
    private static int[] dy = {1, -1, 0, 0};
    private static Map<Integer, Point> member = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        memberBoard = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                memberBoard[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            member.put(i, new Point(x, y, d));
            memberBoard[x][y].add(i);
        }

        int turn = 0;
        while (++turn <= 1000) {

            for (int i = 1; i <= K; i++) {
                Point now = member.get(i);

                List<Integer> current = memberBoard[now.x][now.y];
                int idx = current.indexOf(i);
                List<Integer> moveList = new ArrayList<>(current.subList(idx, current.size()));
                memberBoard[now.x][now.y] = new ArrayList<>(current.subList(0, idx));

                int nx = now.x + dx[now.d];
                int ny = now.y + dy[now.d];

                // 파랑이거나 밖인 경우
                if (outBox(nx, ny) || board[nx][ny] == 2) {
                    now.d = reverse(now.d);
                    nx = now.x + dx[now.d];
                    ny = now.y + dy[now.d];

                    if (outBox(nx, ny) || board[nx][ny] == 2) {
                        memberBoard[now.x][now.y].addAll(moveList); // 원위치
                        continue;
                    }
                }

                // 흰색
                if (board[nx][ny] == 0) {
                    white(moveList, nx, ny);
                }
                // 빨간색
                else if (board[nx][ny] == 1) {
                    red(moveList, nx, ny);
                }

                // 체크
                if (memberBoard[nx][ny].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    private static void red(List<Integer> list, int nx, int ny) {
        Collections.reverse(list);
        for (int key : list) {
            memberBoard[nx][ny].add(key);
            Point mem = member.get(key);
            mem.x = nx;
            mem.y = ny;
        }
    }

    private static void white(List<Integer> list, int nx, int ny) {
        for (int n : list) {
            memberBoard[nx][ny].add(n);
            Point mem = member.get(n);
            mem.x = nx;
            mem.y = ny;
        }
    }

    private static int reverse(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }

    private static boolean outBox(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    private static class Point {
        int x, y, d;

        Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}

