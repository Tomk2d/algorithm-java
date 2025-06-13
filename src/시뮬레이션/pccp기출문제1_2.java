package 시뮬레이션;
import java.util.*;

public class pccp기출문제1_2 {


    class Solution {
        int N, M;
        int[][] board;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        boolean[][] visited;
        int[] oilInColumn;

        public int solution(int[][] land) {
            int answer = 0;

            N = land.length;
            M = land[0].length;
            board = land;
            oilInColumn = new int[M];
            visited = new boolean[N][M];

            for(int x=0; x<N; x++){
                for(int y=0; y<M; y++){
                    if(board[x][y] == 1 && !visited[x][y]){
                        bfs(x, y);
                    }
                }
            }

            for(int val : oilInColumn){
                answer = Math.max(answer, val);
            }

            return answer;
        }

        private void bfs(int x, int y){
            int result = 1;
            Deque<Point> queue = new ArrayDeque<>();
            List<Point> list = new ArrayList<>();
            Set<Integer> colSet = new HashSet<>();

            queue.add(new Point(x, y));
            visited[x][y] = true;
            list.add(new Point(x, y));
            colSet.add(y);

            while(!queue.isEmpty()){
                Point now = queue.pollFirst();

                for(int d=0; d<4; d++){
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];

                    if(nx>=0 && ny>=0 && nx<N && ny<M && !visited[nx][ny] && board[nx][ny]==1){
                        visited[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                        list.add(new Point(nx, ny));
                        colSet.add(ny);
                        result++;
                    }
                }
            }

            // 덩어리 크기를 해당 열들에 누적
            for(int col : colSet){
                oilInColumn[col] += result;
            }
        }

        private class Point{
            int x;
            int y;

            Point(int x, int y){
                this.x = x;
                this.y = y;
            }
        }
    }

}
