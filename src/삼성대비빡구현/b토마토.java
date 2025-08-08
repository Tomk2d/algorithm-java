package 삼성대비빡구현;

import java.util.*;
import java.io.*;
public class b토마토
{
    private static int N, M;
    private static int[][] board;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        int goodTomato = 0;
        int totalTomato = 0;

        Deque<Point> queue = new ArrayDeque<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int n = Integer.parseInt(st.nextToken());

                if(n == 1){
                    goodTomato ++;
                    queue.add(new Point(i, j, 0));
                }
                if(n != -1) totalTomato ++;
                board[i][j] = n;
            }
        }

        if(goodTomato == 0){
            System.out.println(-1);
            return;
        }

        if(totalTomato == goodTomato){
            System.out.println(0);
            return;
        }

        int turn = 0;
        while(!queue.isEmpty()){
            Point now = queue.pollFirst();
            turn = now.turn;

            for(int d=0; d<4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(check(nx, ny)) {
                    queue.add(new Point(nx, ny, now.turn+1));
                    board[nx][ny] = 1;
                    goodTomato ++;
                }
            }
        }

        if(goodTomato<totalTomato){
            System.out.println(-1);
            return;
        }

        System.out.println(turn);


    }

    private static boolean check(int x, int y){
        return x>=0 && y>=0 && x<N && y<M && board[x][y] == 0;
    }

    private static class Point{
        int x, y, turn;

        Point(int x, int y, int turn){
            this.x = x;
            this.y = y;
            this.turn = turn;
        }

    }
}
