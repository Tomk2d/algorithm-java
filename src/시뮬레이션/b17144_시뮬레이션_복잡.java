package 시뮬레이션;

import java.util.*;
import java.io.*;

// 이 문제는 결국 먼지 청소부분에서 보드를 언제 업데이트 하냐 차이인거같다

public class b17144_시뮬레이션_복잡
{
    private static int N, M, T;
    private static int[][] board;
    private static int cleanerX1, cleanerX2, cleanerY1, cleanerY2;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        int cleanerCnt = 1;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int n = Integer.parseInt(st.nextToken());
                if(n == -1){
                    if(cleanerCnt == 1){
                        cleanerX1 = i;
                        cleanerY1 = j;
                        cleanerCnt ++;
                    }else{
                        cleanerX2 = i;
                        cleanerY2 = j;
                    }
                }
                board[i][j] = n;
            }
        }

        int time = 0;
        while(time < T){

            // 1. 미세먼지 확산
            spreadDirt();

            // 2. 공기청정기 가동
            cleanAir();

            time ++;
        }

        int answer = 0;
        for(int[] arr : board){
            for(int num : arr){
                if(num >0){
                    answer += num;
                }
            }
        }

        System.out.println(answer);
    }

    private static void cleanAir(){
        // 위쪽 클리너
        int[] cleanDx = {0, -1, 0, 1};
        int[] cleanDy = {1, 0, -1, 0};
        int direction = 0;

        Deque<Point> queue = new ArrayDeque<>();
        // 처음 위치 위쪽 클리너, 다음 값은0
        queue.add(new Point(cleanerX1, cleanerY1, 0));

        while(!queue.isEmpty()){
            Point now = queue.pollFirst();

            int nx = now.x + cleanDx[direction];
            int ny = now.y + cleanDy[direction];

            // 넘어가면 다음 방향
            // 여기서 방향까지 다 세팅하고, 다음값도 미리 다 세팅
            if(nx<0 || ny<0 || nx>=N || ny>=M){
                direction = (direction+1)%4;
                nx = now.x + cleanDx[direction];
                ny = now.y + cleanDy[direction];
            }

            // 방향 다 계산하고, 여기서 미리 검사하는게 핵심 !!
            if(nx==cleanerX1 && ny==cleanerY1) break;

            queue.add(new Point(nx, ny, board[nx][ny]));
            board[nx][ny] = now.amount;
        }

        // ---------------------------------------------
        // 아래쪽 클리너
        cleanDx = new int[]{0, 1, 0, -1};
        cleanDy = new int[]{1, 0, -1, 0};
        direction = 0;

        queue = new ArrayDeque<>();
        // 처음 위치 아래쪽 클리너, 다음 값은0
        queue.add(new Point(cleanerX2, cleanerY2, 0));

        while(!queue.isEmpty()){
            Point now = queue.pollFirst();

            int nx = now.x + cleanDx[direction];
            int ny = now.y + cleanDy[direction];

            // 넘어가면 다음 방향
            if(nx<0 || ny<0 || nx>=N || ny>=M){
                direction = (direction+1)%4;
                nx = now.x + cleanDx[direction];
                ny = now.y + cleanDy[direction];
            }

            if(nx==cleanerX2 && ny==cleanerY2) break;

            queue.add(new Point(nx, ny, board[nx][ny]));
            board[nx][ny] = now.amount;
        }
    }

    private static void spreadDirt(){
        // 퍼질 미세먼지의 리스트
        List<Point> list = new ArrayList<>();

        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                if(board[x][y]>0){
                    calDirt(x, y, list);
                }
            }
        }

        for(Point now : list){
            board[now.x][now.y] += now.amount;
        }
    }

    private static void calDirt(int x, int y, List<Point> list){
        int amount = board[x][y]/5;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<M && board[nx][ny]>=0){
                list.add(new Point(nx, ny, amount));
                board[x][y] -= amount;
            }
        }
    }

    private static class Point{
        int x;
        int y;
        int amount;

        Point(int x, int y, int amount){
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }
}

