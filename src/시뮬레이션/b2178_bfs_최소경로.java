package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b2178_bfs_최소경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[][] board = new int[N][M];

        for(int i =0; i<N; i++){
            String inputLine = br.readLine();
            for(int j =0; j<M; j++){
                board[i][j] = inputLine.charAt(j) - '0';
            }
        }
        int result = bfs(board, N, M);
        System.out.println(result);
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int[][] board,int N,int M){
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0,0));

        int result = 0;

        while(!queue.isEmpty()){
            Point point = queue.pollFirst();
            int x = point.x;
            int y = point.y;

            if (x == N-1 && y == M-1){
                result = board[x][y];
                break;
            }

            for (int i =0; i <4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny] != 1) continue;

                board[nx][ny] = board[x][y] +1;
                queue.add(new Point(nx,ny));
            }
        }
        return result;
    }
}
