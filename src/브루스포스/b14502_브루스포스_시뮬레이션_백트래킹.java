package 브루스포스;

import java.util.*;
import java.io.*;

// 처음 이 문제를 봤을때 매우 당황했다.
// 이런 문제를 어떻게 풀어야하지 ? 라고 말이다.
// 편법인데, 이런 경우 보통 브루스포스다.
// 최적화를 생각하다가 문제 자체를 못 풀 수 있다.
// 입력의 최대 조건을 잘 확인해보면 입력값이 굉장히 낮은 경우를 볼 수 있다.

public class b14502_브루스포스_시뮬레이션_백트래킹 {
    private static int N, M;
    private static int[][] board;
    private static List<Point> virusList = new ArrayList<>();
    private static List<Point> blankList = new ArrayList<>();
    private static int maxSafeArea = 0;

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

                if (num == 0) {
                    blankList.add(new Point(i, j));
                } else if (num == 2) {
                    virusList.add(new Point(i, j));
                }
            }
        }

        makeWall(0, 0, board);
        System.out.println(maxSafeArea);
    }

    private static void makeWall(int depth, int start, int[][] tempBoard) {
        if (depth == 3) {
            int[][] copied = copyBoard(tempBoard);
            spreadVirus(copied);
            maxSafeArea = Math.max(maxSafeArea, countSafeArea(copied));
            return;
        }

        for (int i = start; i < blankList.size(); i++) {
            Point p = blankList.get(i);
            tempBoard[p.x][p.y] = 1;
            makeWall(depth + 1, i + 1, tempBoard);
            tempBoard[p.x][p.y] = 0;
        }
    }

    private static void spreadVirus(int[][] map) {
        Queue<Point> queue = new LinkedList<>();
        for (Point v : virusList) {
            queue.offer(new Point(v.x, v.y));
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = 2; // 바이러스 감염
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    private static int countSafeArea(int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int[][] copyBoard(int[][] src) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(src[i], M);
        }
        return copy;
    }

    private static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

