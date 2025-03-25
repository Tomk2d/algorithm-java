package 시뮬레이션;

import java.util.*;
import java.io.*;

// 해당 문제는 딱 1번 벽을 부술 기회를 준다.
// 단순히 각 노드별로 부술 기회만 관리했다가 틀렸었다.
// 생각해보면 visited 로 방문한 곳을 더이상 들르지 않게 해두었는데,
// 벽을 부수고 해당 자리를 방문해서 다시 막힌경우, 더이상 갈 수 없다.
// 하지만 벽을 부수지 않고, 뒤늦게 해당 자리에 방문하게되면
// 1번의 벽을 뚫을 기회가 생긴다.
// 그래서 반드시 2개의 경우의 수는 다른 방문으로 관리되어야 한다.

public class b2206_벽을부수는_최소경로_3차원_visited {
    private static int N, M;
    private static int[][] board;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        // 입력 처리 수정
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        // 3차원 방문 배열 [x][y][벽 부순 횟수 (0~1)]
        boolean[][][] visited = new boolean[N][M][2];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1, 1)); // 시작 시 벽 부수기 기회 1회
        visited[0][0][1] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            // 도착 조건
            if(now.x == N-1 && now.y == M-1) {
                System.out.println(now.cnt);
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                int nextChance = now.chance;
                if(board[nx][ny] == 1) {
                    if(now.chance == 0) continue; // 벽 부술 기회 없음
                    nextChance = 0; // 벽 부수기 사용
                }

                // 상태별 방문 체크
                if(!visited[nx][ny][nextChance]) {
                    visited[nx][ny][nextChance] = true;
                    queue.add(new Point(nx, ny, nextChance, now.cnt + 1));
                }
            }
        }
        System.out.println(-1);
    }

    static class Point {
        int x, y, chance, cnt;
        public Point(int x, int y, int chance, int cnt) {
            this.x = x;
            this.y = y;
            this.chance = chance;
            this.cnt = cnt;
        }
    }
}

