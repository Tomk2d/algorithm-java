package 시뮬레이션;

import java.util.*;

class Point {
    int x, y, dist;

    Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class b16236_bfs와_우선순위큐_시뮬레이션_다시풀어봐야할듯 {
    static int N;
    static int[][] map;
    static int sharkX, sharkY, sharkSize = 2, eatCount = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }
        sc.close();

        System.out.println(bfs());
    }

    static int bfs() {
        int time = 0;
        while (true) {
            PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> {
                if (a.dist != b.dist) return Integer.compare(a.dist, b.dist);
                if (a.x != b.x) return Integer.compare(a.x, b.x);
                return Integer.compare(a.y, b.y);
            });
            boolean[][] visited = new boolean[N][N];
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(sharkX, sharkY, 0));
            visited[sharkX][sharkY] = true;

            while (!queue.isEmpty()) {
                Point cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                        if (map[nx][ny] <= sharkSize) { // 지나갈 수 있는지 확인
                            visited[nx][ny] = true;
                            queue.add(new Point(nx, ny, cur.dist + 1));
                            if (map[nx][ny] > 0 && map[nx][ny] < sharkSize) { // 먹을 수 있는 경우
                                pq.add(new Point(nx, ny, cur.dist + 1));
                            }
                        }
                    }
                }
            }

            if (pq.isEmpty()) return time; // 더 이상 먹을 수 있는 물고기가 없음

            Point fish = pq.poll();
            time += fish.dist;
            sharkX = fish.x;
            sharkY = fish.y;
            map[sharkX][sharkY] = 0;
            eatCount++;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }
    }
}
