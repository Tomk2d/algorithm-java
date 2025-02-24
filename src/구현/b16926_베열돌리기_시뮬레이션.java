package 구현;

import java.io.*;
import java.util.*;

public class b16926_베열돌리기_시뮬레이션 {
    private static int[][] board;
    private static int[][] changeBoard;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int turn = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (turn-- > 0) {
            changeBoard = new int[N][M];

            int startN = 0;
            int startM = 0;
            int endN = N - 1;
            int endM = M - 1;

            while (startN < endN && startM < endM) {
                turnLayer(startN, endN, startM, endM);
                startN++;
                startM++;
                endN--;
                endM--;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = changeBoard[i][j];
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void turnLayer(int startN, int endN, int startM, int endM) {
        // 왼쪽 → 아래 → 오른쪽 → 위쪽 순서로 이동
        for (int j = startM; j < endM; j++) {
            changeBoard[startN][j] = board[startN][j + 1]; // 왼쪽 이동
        }
        for (int i = startN; i < endN; i++) {
            changeBoard[i][endM] = board[i + 1][endM]; // 아래 이동
        }
        for (int j = endM; j > startM; j--) {
            changeBoard[endN][j] = board[endN][j - 1]; // 오른쪽 이동
        }
        for (int i = endN; i > startN; i--) {
            changeBoard[i][startM] = board[i - 1][startM]; // 위쪽 이동
        }
    }
}

