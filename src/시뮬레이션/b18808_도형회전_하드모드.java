package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b18808_도형회전_하드모드 {
    private static int N, M, blockN;
    private static int[][] board;
    private static Deque<List<List<Integer>>> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 노트북 세로
        M = Integer.parseInt(st.nextToken()); // 노트북 가로
        blockN = Integer.parseInt(st.nextToken()); // 스티커 개수

        board = new int[N][M];

        for (int i = 0; i < blockN; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); // 스티커 행
            int c = Integer.parseInt(st.nextToken()); // 스티커 열

            List<List<Integer>> block = new ArrayList<>();
            for (int x = 0; x < r; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < c; y++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) {
                        block.add(Arrays.asList(x, y));
                    }
                }
            }
            queue.add(block);
        }

        while (!queue.isEmpty()) {
            List<List<Integer>> block = queue.pollFirst();
            boolean placed = false;

            // 0°, 90°, 180°, 270° 시도 (최대 4번)
            for (int rot = 0; rot < 4; rot++) {
                if (locateBlock(block)) {
                    placed = true;
                    break;
                }
                block = turnBlock(block); // 회전
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static List<List<Integer>> turnBlock(List<List<Integer>> block) {
        List<List<Integer>> rotated = new ArrayList<>();

        // (x, y) → (y, -x)
        for (List<Integer> pos : block) {
            int x = pos.get(0);
            int y = pos.get(1);
            rotated.add(Arrays.asList(y, -x));
        }

        // 정규화: 가장 작은 x, y를 0으로 맞추기
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (List<Integer> pos : rotated) {
            minX = Math.min(minX, pos.get(0));
            minY = Math.min(minY, pos.get(1));
        }

        List<List<Integer>> normalized = new ArrayList<>();
        for (List<Integer> pos : rotated) {
            int normX = pos.get(0) - minX;
            int normY = pos.get(1) - minY;
            normalized.add(Arrays.asList(normX, normY));
        }

        return normalized;
    }

    private static boolean locateBlock(List<List<Integer>> block) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (canAttach(x, y, block)) {
                    attachBlock(x, y, block);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canAttach(int x, int y, List<List<Integer>> block) {
        for (List<Integer> pos : block) {
            int nx = x + pos.get(0);
            int ny = y + pos.get(1);

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) return false;
            if (board[nx][ny] == 1) return false;
        }
        return true;
    }

    private static void attachBlock(int x, int y, List<List<Integer>> block) {
        for (List<Integer> pos : block) {
            int nx = x + pos.get(0);
            int ny = y + pos.get(1);
            board[nx][ny] = 1;
        }
    }
}

