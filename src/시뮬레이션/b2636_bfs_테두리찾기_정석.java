package 시뮬레이션;

import java.util.*;
import java.io.*;

// 외부 테두리를 검사할땐 오히려 0을 세어버리면 된다.
// 대신 board 의 테두리에는 치즈가 없는 공간이라는 전제가 필요하다.

public class b2636_bfs_테두리찾기_정석
{
    private static int N, M;
    private static int[][] board;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static boolean[][] outSideAir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        // 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int lastCheese = 0;
        while(true){
            // 치즈 개수 확인
            int cheeseCnt = countCheese();
            if(cheeseCnt == 0) break;

            lastCheese = cheeseCnt;

            // 1. 바깥 공기 찾기
            findOutSideAir();

            // 2. 바깥 공기와 맞닿은 치즈 찾고, 녹이기
            changeOutSideCheese();

            // 3. 시간 더하기

            time++;
        }

        System.out.println(time);
        System.out.println(lastCheese);

    }

    private static int countCheese(){
        int cnt =0;
        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                if(board[x][y] == 1){
                    cnt ++;
                }
            }
        }
        return cnt;
    }

    private static void findOutSideAir(){
        Deque<Point> queue = new ArrayDeque<>();
        outSideAir = new boolean[N][M];
        queue.add(new Point(0, 0));
        outSideAir[0][0] = true;

        while(!queue.isEmpty()){
            Point now = queue.pollFirst();

            for(int i=0; i<4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];

                if(nx>=0 && ny>=0 && nx<N && ny<M && board[nx][ny]==0 && !outSideAir[nx][ny]){
                    queue.add(new Point(nx, ny));
                    outSideAir[nx][ny] = true;
                }
            }
        }
    }

    private static void changeOutSideCheese(){
        List<Point> outSideCheese = new ArrayList<>();

        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                // 치즈이고, 외부일때
                if(board[x][y] == 1 && checkOutSideCheese(x, y)){
                    outSideCheese.add(new Point(x, y));
                }
            }
        }

        for(Point p : outSideCheese){
            board[p.x][p.y] = 0;
        }
    }

    private static boolean checkOutSideCheese(int x, int y){
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            // 바깥 공기와 맞닿은 경우
            if(nx>=0 && ny>=0 && nx<N && ny<M && outSideAir[nx][ny]){
                return true;
            }
        }
        return false;
    }

    private static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}

