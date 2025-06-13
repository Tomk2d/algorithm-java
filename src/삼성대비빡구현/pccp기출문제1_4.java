package 삼성대비빡구현;
import java.util.*;
public class pccp기출문제1_4 {

    class Solution {
        int[][] board;
        int N, M;
        int redX, redY, redGoalX, redGoalY;
        int blueX, blueY, blueGoalX, blueGoalY;

        public int solution(int[][] maze) {
            N = maze.length;
            M = maze[0].length;

            board = new int[N][M];

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(maze[i][j] == 1){
                        redX = i;
                        redY = j;
                    } else if(maze[i][j] == 2){
                        blueX = i;
                        blueY = j;
                    } else if(maze[i][j] == 3){
                        redGoalX = i;
                        redGoalY = j;
                    } else if(maze[i][j] == 4){
                        blueGoalX = i;
                        blueGoalY = j;
                    } else if(maze[i][j] == 5){
                        board[i][j] = -1;
                    }
                }
            }

            return bfs();
        }

        private int bfs(){
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            Deque<Point> queue = new ArrayDeque<>();
            boolean[][] visitedRed = new boolean[N][M];
            boolean[][] visitedBlue = new boolean[N][M];
            visitedRed[redX][redY] = true;
            visitedBlue[blueX][blueY] = true;

            queue.add(new Point(redX, redY, blueX, blueY, visitedRed, visitedBlue, 0, false, false));

            while(!queue.isEmpty()){
                Point now = queue.pollFirst();

                if(now.arriveRed && now.arriveBlue){
                    return now.cnt;
                }

                for(int d=0; d<4; d++){
                    int redNx = now.redX;
                    int redNy = now.redY;
                    boolean arriveRedNext = now.arriveRed;

                    if(!now.arriveRed){
                        redNx = now.redX + dx[d];
                        redNy = now.redY + dy[d];
                        if(!inBox(redNx, redNy)) continue;
                        if(now.visitedRed[redNx][redNy]) continue;
                        if(redNx == redGoalX && redNy == redGoalY) arriveRedNext = true;
                    }

                    for(int i=0; i<4; i++){
                        int blueNx = now.blueX;
                        int blueNy = now.blueY;
                        boolean arriveBlueNext = now.arriveBlue;

                        if(!now.arriveBlue){
                            blueNx = now.blueX + dx[i];
                            blueNy = now.blueY + dy[i];
                            if(!inBox(blueNx, blueNy)) continue;
                            if(now.visitedBlue[blueNx][blueNy]) continue;
                            if(blueNx == blueGoalX && blueNy == blueGoalY) arriveBlueNext = true;
                        }

                        // 겹침 방지
                        if(redNx == blueNx && redNy == blueNy) continue;

                        // 스왑 방지
                        if(now.redX == blueNx && now.redY == blueNy &&
                            now.blueX == redNx && now.blueY == redNy) continue;

                        boolean[][] newVisitedRed = createVisited(now.visitedRed);
                        boolean[][] newVisitedBlue = createVisited(now.visitedBlue);

                        if(!arriveRedNext) newVisitedRed[redNx][redNy] = true;
                        if(!arriveBlueNext) newVisitedBlue[blueNx][blueNy] = true;

                        queue.add(new Point(redNx, redNy, blueNx, blueNy,
                            newVisitedRed, newVisitedBlue, now.cnt + 1, arriveRedNext, arriveBlueNext));
                    }
                }
            }
            return 0;
        }

        private boolean[][] createVisited(boolean[][] visited){
            boolean[][] result = new boolean[N][M];
            for(int i=0; i<N; i++){
                result[i] = visited[i].clone();
            }
            return result;
        }

        private boolean inBox(int x, int y){
            return x >= 0 && y >= 0 && x < N && y < M && board[x][y] == 0;
        }

        private class Point{
            int redX, redY;
            int blueX, blueY;
            boolean[][] visitedRed;
            boolean[][] visitedBlue;
            int cnt;
            boolean arriveRed;
            boolean arriveBlue;

            Point(int redX, int redY, int blueX, int blueY,
                boolean[][] visitedRed, boolean[][] visitedBlue,
                int cnt, boolean arriveRed, boolean arriveBlue) {
                this.redX = redX;
                this.redY = redY;
                this.blueX = blueX;
                this.blueY = blueY;
                this.visitedRed = visitedRed;
                this.visitedBlue = visitedBlue;
                this.cnt = cnt;
                this.arriveRed = arriveRed;
                this.arriveBlue = arriveBlue;
            }
        }
    }

}
