package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b15685_선잇기빡구현 {
    private static int N;
    private static int[] dx = {1, 0, -1, 0}; // → ↑ ← ↓
    private static int[] dy = {0, -1, 0, 1};
    private static int[][] board = new int[101][101];
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            makeCurve(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (checkBox(i, j)) answer++;
            }
        }

        System.out.println(answer);
    }

    private static void makeCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for (int gen = 0; gen < g; gen++) {
            for (int i = directions.size() - 1; i >= 0; i--) {
                directions.add((directions.get(i) + 1) % 4);
            }
        }

        board[y][x] = 1;
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            if (x >= 0 && x <= 100 && y >= 0 && y <= 100) {
                board[y][x] = 1;
            }
        }
    }

    private static boolean checkBox(int y, int x) {
        return board[y][x] == 1 &&
            board[y + 1][x] == 1 &&
            board[y][x + 1] == 1 &&
            board[y + 1][x + 1] == 1;
    }
}

