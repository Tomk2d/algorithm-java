package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b20058_도형회전시뮬레이션{
    private static int N, orderN;
    private static int[][] board;
    private static int[] orders;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        orderN = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        orders = new int[orderN];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<orderN; i++){
            orders[i] = Integer.parseInt(st.nextToken());
        }

        // 시뮬레이션 시작
        for(int i=0; i<orderN; i++){
            int order = orders[i];
            int n2 = (int) Math.pow(2, order);

            // 2^n 만큼 쪼개서 회전 및 마이너스 시키기
            for(int x=0; x<N; x+=n2){
                for(int y=0; y<N; y+=n2){
                    turnBoard(x, y, n2);
                }
            }

            minusBoard();
        }

        // 합 세기
        int sum = 0;
        int maxCnt = 0;
        visited = new boolean[N][N];
        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                sum += board[x][y];

                if(!visited[x][y] && board[x][y] > 0){
                    int result = bfs(x, y);
                    maxCnt = Math.max(maxCnt, result);
                }
            }
        }
        System.out.println(sum);
        System.out.println(maxCnt);

    }

    private static void turnBoard(int startX, int startY, int n2){
        int[][] copyBlock = new int[n2][n2];

        for(int i=0; i<n2; i++){
            for(int j=0; j<n2; j++){
                copyBlock[i][j] = board[startX+i][startY+j];
            }
        }

        for(int i=0; i<n2; i++){
            for(int j=0; j<n2; j++){
                board[startX + j][startY + (n2 - 1 - i)] = copyBlock[i][j];
            }
        }

    }

    private static void minusBoard(){
        List<Point> meltBlock = new ArrayList<>();

        for(int i=0; i<N; i++){
            int x = i;
            for(int j=0; j<N; j++){
                int y = j;

                int cnt = 0;
                for(int d=0; d<4; d++){
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(nx>=0 && ny>=0 && nx<N && ny<N && board[nx][ny]>0){
                        cnt ++;
                    }
                }

                if(cnt < 3) meltBlock.add(new Point(x, y));
            }
        }

        // 반영
        for(Point now : meltBlock){
            if(board[now.x][now.y] > 0) board[now.x][now.y] --;
        }
    }

    private static int bfs(int x, int y){
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            Point now = queue.pollFirst();
            cnt ++;

            for(int d=0; d<4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx>=0 && ny>=0 && nx<N && ny<N && !visited[nx][ny] && board[nx][ny]>0){
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }

        }

        return cnt;
    }

    private static void print(int[][] board){
        System.out.println();
        for(int x=0; x<board.length; x++){
            for(int y=0; y<board[0].length; y++){
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }

    private static class Point{
        int x, y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

