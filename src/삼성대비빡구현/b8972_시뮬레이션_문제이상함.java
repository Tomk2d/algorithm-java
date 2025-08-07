package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b8972_시뮬레이션_문제이상함 {
    private static int N, M;
    private static Point user;
    private static Deque<Point> robots = new ArrayDeque<>();
    private static int[] dx = {0,  1, 1, 1,  0, 0, 0, -1, -1, -1};
    private static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1,  0,  1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                if (c == 'R') {
                    robots.add(new Point(i, j));
                } else if (c == 'I') {
                    user = new Point(i, j);
                }
            }
        }

        String commands = br.readLine();
        for (int i = 0; i < commands.length(); i++) {
            int command = commands.charAt(i) - '0';

            // 1. 종수 이동
            if (!moveUser(command)) {
                System.out.println("kraj " + (i + 1));
                return;
            }

            // 2. 로봇 이동 (동시 이동 처리)
            if (!moveRobots()) {
                System.out.println("kraj " + (i + 1));
                return;
            }
        }

        // 최종 보드 출력
        char[][] result = new char[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(result[i], '.');
        result[user.x][user.y] = 'I';
        for (Point p : robots) result[p.x][p.y] = 'R';

        for (int i = 0; i < N; i++) {
            System.out.println(new String(result[i]));
        }
    }

    private static boolean moveUser(int command) {
        int nx = user.x + dx[command];
        int ny = user.y + dy[command];
        for (Point robot : robots) {
            if (robot.x == nx && robot.y == ny) return false;
        }
        user.x = nx;
        user.y = ny;
        return true;
    }

    private static boolean moveRobots() {
        Map<String, List<Point>> moveMap = new HashMap<>();
        List<Point> nextRobots = new ArrayList<>();

        for (Point robot : robots) {
            int[] next = getNext(robot.x, robot.y);
            int nx = next[0], ny = next[1];
            if (nx == user.x && ny == user.y) return false;

            String key = nx + "," + ny;
            moveMap.computeIfAbsent(key, k -> new ArrayList<>()).add(new Point(nx, ny));
        }

        for (List<Point> list : moveMap.values()) {
            if (list.size() == 1) nextRobots.add(list.get(0));
        }

        robots.clear();
        robots.addAll(nextRobots);
        return true;
    }

    private static int[] getNext(int x, int y) {
        int minDist = Integer.MAX_VALUE;
        int nx = x, ny = y;
        for (int d = 1; d <= 9; d++) {
            if (d == 5) continue;
            int tx = x + dx[d];
            int ty = y + dy[d];
            if (0 <= tx && tx < N && 0 <= ty && ty < M) {
                int dist = Math.abs(tx - user.x) + Math.abs(ty - user.y);
                if (dist < minDist) {
                    minDist = dist;
                    nx = tx;
                    ny = ty;
                }
            }
        }
        return new int[]{nx, ny};
    }

    private static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }
}

