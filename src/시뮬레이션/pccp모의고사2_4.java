package 시뮬레이션;
import java.util.*;
public class pccp모의고사2_4 {

    class Solution {
        int[][] board;
        int N, M;
        public int solution(int M, int N, int[][] holes) {
            int answer = 0;

            this.N = N;
            this.M = M;

            board = new int[N][M];

            for(int[] hole : holes){
                int x = hole[1]-1;
                int y = hole[0]-1;

                board[x][y] = -1;
            }

            return bfs(0, 0);
        }

        private int bfs(int startX, int startY){
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            Deque<Point> queue = new ArrayDeque<>();
            queue.add(new Point(startX, startY, 0, false));

            // 방문 상태를 신발 사용 여부까지 포함해서 체크
            boolean[][][] visited = new boolean[N][M][2];
            visited[startX][startY][0] = true;

            while(!queue.isEmpty()){
                Point now = queue.pollFirst();

                if(now.x == N-1 && now.y == M-1){
                    return now.cnt;
                }

                for(int d=0; d<4; d++){
                    // 1칸 이동
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    int used = now.useItem ? 1 : 0;

                    if(nx>=0 && ny>=0 && nx<N && ny<M && !visited[nx][ny][used] && board[nx][ny] == 0){
                        queue.add(new Point(nx, ny, now.cnt+1, now.useItem));
                        visited[nx][ny][used] = true;
                    }

                    // 신발 사용해서 2칸 점프
                    if(!now.useItem){
                        int jx = now.x + dx[d]*2;
                        int jy = now.y + dy[d]*2;
                        if(jx>=0 && jy>=0 && jx<N && jy<M && !visited[jx][jy][1] && board[jx][jy] == 0){
                            queue.add(new Point(jx, jy, now.cnt+1, true));
                            visited[jx][jy][1] = true;
                        }
                    }
                }
            }

            return -1;
        }


        private class Point{
            int x;
            int y;
            int cnt;
            boolean useItem;

            Point(int x, int y, int cnt, boolean useItem){
                this.x = x;
                this.y = y;
                this.cnt = cnt;
                this.useItem = useItem;
            }
        }
    }
}
