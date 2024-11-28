package 시뮬레이션;

import java.io.*;
import java.util.*;

public class b3190_SnakeGame {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] maps;
    static Map<Integer, Character> changeDirection;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // N x N 보드의 크기
        int K = Integer.parseInt(br.readLine());    // 사과의 개수

        maps = new int[N][N];

        for (int i = 0; i < K; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]) - 1;
            int y = Integer.parseInt(input[1]) - 1;
            maps[x][y] = 1; // 사과 위치 지정
        }

        int L = Integer.parseInt(br.readLine());    // 뱀의 방향변환 횟수
        changeDirection = new HashMap<>();

        for (int i = 0; i < L; i++) {
            String[] input = br.readLine().split(" ");
            int time = Integer.parseInt(input[0]);
            char dir = input[1].charAt(0);
            changeDirection.put(time, dir);
        }

        System.out.println(simulateGame(N));
    }

    private static int simulateGame(int n) {
        int time = 0;
        int directionIndex = 0;
        Deque<Point> snake = new LinkedList<>();
        snake.add(new Point(0, 0));
        maps[0][0] = 2;

        while (true) {
            time++;
            Point head = snake.peekFirst();
            int nx = head.x + direction[directionIndex][0];
            int ny = head.y + direction[directionIndex][1];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || maps[nx][ny] == 2) {
                break; // 벽이나 자신의 몸에 부딪힘
            }

            if (maps[nx][ny] != 1) {
                Point tail = snake.pollLast();
                maps[tail.x][tail.y] = 0;
            }

            snake.addFirst(new Point(nx, ny));
            maps[nx][ny] = 2;

            if (changeDirection.containsKey(time)) {
                char turn = changeDirection.get(time);
                directionIndex = (turn == 'L') ? (directionIndex + 3) % 4 : (directionIndex + 1) % 4;
            }
        }

        return time;
    }
}
