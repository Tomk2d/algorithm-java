package 시뮬레이션;

import java.util.*;
import java.io.*;

// 맵 업데이트 하는 함수를 잘 봐두면 좋을거 같다.
// 굳이 빈칸과 블럭을 모두 변수에 저장해서 기억하고 있을 필요가 없다
public class b11559_시뮬레이션빡구현_여기있는_맵업데이트_외우자 {
    private static int N = 12;
    private static int M = 6;
    private static char[][] board = new char[N][M];
    private static int answer = 0;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boolean isBomb = true;
        while (isBomb) {
            isBomb = false;
            visited = new boolean[N][M];

            // 1. 터뜨리기
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (board[x][y] != '.' && !visited[x][y]) {
                        if (findBomb(x, y)) {
                            isBomb = true;
                        }
                    }
                }
            }

            // 2. 배치 정리
            updateBoard();

            if (isBomb) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static void updateBoard() {
        for (int y = 0; y < M; y++) {
            int emptyIdx = N - 1; // 블록이 내려갈 위치

            for (int x = N - 1; x >= 0; x--) {
                if (board[x][y] != '.') {   // 블록이면 아래로 이동
                    char temp = board[x][y];
                    board[x][y] = '.';
                    board[emptyIdx][y] = temp;
                    emptyIdx--;             // 다음 블록이 내려갈 위치 갱신
                }
                // 빈칸일 경우 건들지 않음.
                // 어차피 문자에 걸리지 않으면 그게 빈칸인거고,
                // 빈칸을 문자가 채우면, 그 자리는 다시 빈칸이 되니까.
            }
        }
    }

    private static boolean findBomb(int x, int y) {
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        List<Point> result = new ArrayList<>();
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point now = queue.pollFirst();
            result.add(now);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M
                    && board[now.x][now.y] == board[nx][ny]
                    && !visited[nx][ny]) {
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        if (result.size() >= 4) { // 4개 이상 연결되면 터뜨리기
            for (Point now : result) {
                board[now.x][now.y] = '.';
            }
            return true;
        }
        return false;
    }

    private static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

