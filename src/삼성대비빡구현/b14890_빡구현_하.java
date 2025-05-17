package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b14890_빡구현_하 {
    private static int N, L;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int[] arr = new int[N];

        // 가로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[j] = board[i][j];
            }
            if (checkArray(arr)) answer++;
        }

        // 세로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[j] = board[j][i];
            }
            if (checkArray(arr)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean checkArray(int[] array) {
        boolean[] installed = new boolean[N];
        int pre = array[0];
        int sameCnt = 1;
        int idx = 1;

        while (idx < N) {
            int diff = array[idx] - pre;

            if (diff == 0) {
                sameCnt++;
            }
            else if (diff == 1) { // 오르막
                if (sameCnt < L) return false;
                for (int j = idx - L; j < idx; j++) {
                    if (installed[j]) return false;
                    installed[j] = true;
                }
                sameCnt = 1;
            }
            else if (diff == -1) { // 내리막
                if (idx + L - 1 >= N) return false;
                for (int j = idx; j < idx + L; j++) {
                    if (array[j] != array[idx] || installed[j]) return false;
                    installed[j] = true;
                }
                idx += L - 1;
                sameCnt = 0;
            }
            else return false;

            pre = array[idx];
            idx++;
        }

        return true;
    }
}

