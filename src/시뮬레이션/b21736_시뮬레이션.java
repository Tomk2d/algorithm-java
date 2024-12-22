package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b21736_시뮬레이션 {
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};
    public static int max_peole = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        String[][] board = new String[N][M];
        int start_x = 0;
        int start_y = 0;

        for(int i=0; i<N; i++){
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++){
                board[i][j] = input[j];
                if(input[j].equals("I")){
                    start_x = i;
                    start_y = j;
                }
            }
        }
        boolean[][] visited = new boolean[N][M];

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(start_x, start_y));

        // bfs
        while(!queue.isEmpty()){
            Point point = queue.pollFirst();
            int x = point.x;
            int y = point.y;

            if (board[x][y].equals("P")){
                max_peole ++;
            }

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >=0 && nx < board.length && ny >= 0 && ny < board[0].length && !board[nx][ny].equals("X")){
                    if(!visited[nx][ny]){
                        visited[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
        // 예외 처리
        if(max_peole == 0) System.out.println("TT");
        else System.out.println(max_peole);
    }

    public static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
