package 삼성대비빡구현;

import java.util.*;
import java.io.*;

/*
*       이문제의 핵심은 무작정 구현이 아니다
*       왔다 갔다 하는 경로를 최대한 축소 하는것
*       예를 들어 3X3 보드에 상어가 1000 스피드로 이동한다면
*       결국 3으로 나눈 나머지만틈만 이동할 것이다.
*       그 부분만 잘해주면 된다.
*
* */

public class b17143_반복이동경로축소 {
    private static int N, M, sharkN;
    private static int[] dx = {-1, 1, 0, 0}; // 상 하 우 좌
    private static int[] dy = {0, 0, 1, -1};
    private static Shark[][] board;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharkN = Integer.parseInt(st.nextToken());

        board = new Shark[N][M];

        for (int i = 0; i < sharkN; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());

            board[x][y] = new Shark(d, speed, size);
        }

        for (int fisherMan = 0; fisherMan < M; fisherMan++) {
            fishing(fisherMan);
            findShark();
        }

        System.out.println(answer);
    }

    private static void findShark() {
        Shark[][] tempBoard = new Shark[N][M];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board[x][y] != null) {
                    moveShark(board[x][y], x, y, tempBoard);
                }
            }
        }

        board = tempBoard;
    }

    private static void moveShark(Shark shark, int x, int y, Shark[][] tempBoard) {
        int s = shark.speed;
        int d = shark.d;

        // 이동 최적화
        if (d == 0 || d == 1) s %= (N - 1) * 2;
        else s %= (M - 1) * 2;

        int nx = x;
        int ny = y;

        for (int i = 0; i < s; i++) {
            int tx = nx + dx[d];
            int ty = ny + dy[d];

            if (tx < 0 || tx >= N || ty < 0 || ty >= M) {
                // 방향 전환
                if (d == 0) d = 1;
                else if (d == 1) d = 0;
                else if (d == 2) d = 3;
                else if (d == 3) d = 2;

                tx = nx + dx[d];
                ty = ny + dy[d];
            }

            nx = tx;
            ny = ty;
        }

        shark.d = d;

        // 상어가 없거나, 기존에 있던 상어보다 크면
        if (tempBoard[nx][ny] == null || tempBoard[nx][ny].size < shark.size) {
            tempBoard[nx][ny] = shark;
        }

        board[x][y] = null;
    }

    private static void fishing(int y) {
        for (int x = 0; x < N; x++) {
            if (board[x][y] != null) {
                answer += board[x][y].size;
                board[x][y] = null;
                break;
            }
        }
    }

    private static class Shark {
        int d;
        int speed;
        int size;

        Shark(int d, int speed, int size) {
            this.d = d;
            this.speed = speed;
            this.size = size;
        }
    }
}

