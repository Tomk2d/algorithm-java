package 최소경로;

import java.util.*;
import java.io.*;

// 이 문제의 핵심은
// 1. 테두리를 미리 선별하여, 테두리만 초기 경로로 넣어주는것
// 2. 모든 테두리에서 동시에 출발하는 것이 아닌, 각 블럭마다 최소경로를 측정하는것
//    이후 그 경로들중 제일 짧은거 선별
public class b2146_섬과섬의_최소경로_bfs와dfs_테두리로돌기
{
    private static int N;
    private static int[][] board;
    private static Set<Side> sides = new HashSet<>();
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 2;
        // board 갱신
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(board[i][j] == 1){
                    changeBoard(i, j, num);
                    num ++;
                }
            }
        }
        System.out.println(bfs());
    }

    public static int bfs(){
        int minCnt = Integer.MAX_VALUE;

        // 모든 섬의 가장자리에서 BFS 시작
        for(Side side : sides){
            boolean[][] visited = new boolean[N][N]; // 각 BFS마다 초기화
            Deque<Point> queue = new ArrayDeque<>();
            queue.add(new Point(side.x, side.y, 0, board[side.x][side.y]));
            visited[side.x][side.y] = true;

            while(!queue.isEmpty()){
                Point now = queue.pollFirst();

                for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]){
                        visited[nx][ny] = true;

                        // 바다일 때만 계속 탐색
                        if(board[nx][ny] == 0){
                            queue.add(new Point(nx, ny, now.cnt+1, now.startNum));
                        }
                        // 다른 섬에 도착한 경우
                        else if(board[nx][ny] > 0 && board[nx][ny] != now.startNum){
                            minCnt = Math.min(minCnt, now.cnt);
                            break;
                        }
                    }
                }
            }
        }
        return minCnt;
    }

    private static class Point{
        int x;
        int y;
        int cnt;
        int startNum;
        Point(int x, int y, int cnt, int startNum){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.startNum = startNum;
        }
    }

    public static void changeBoard(int x, int y, int num){
        board[x][y] = num;

        for(int i=0; i<4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < N){
                if(board[nextX][nextY] == 1){
                    changeBoard(nextX, nextY, num);
                }
                else if(board[nextX][nextY] == 0){
                    sides.add(new Side(x, y)); // 현재 좌표를 Side로 저장
                }
            }
        }
    }

    private static class Side{
        int x;
        int y;
        Side(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

