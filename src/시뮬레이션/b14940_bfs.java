package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b14940_bfs {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] board = new int[N][M];
        int goalX = 0;
        int goalY = 0;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (board[i][j] == 2) {
                    goalX = i;
                    goalY = j;
                }
            }
        }

        board = bfs(board, N, M, goalX, goalY);
        print(board);

    }

    public static void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] bfs( int[][] board , int N, int M, int goalX, int goalY ){
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(goalX, goalY));
        board[goalX][goalY] = 0;

        while(!queue.isEmpty()){
            Point point = queue.pollFirst();
            int x = point.x;
            int y = point.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx<0 || ny<0 || nx>=N || ny>=M || board[nx][ny] != 1) continue;

                board[nx][ny] = board[x][y] + 1;
                queue.add(new Point(nx, ny));
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) board[i][j] = -1;
            }
        }

        for (int i =0; i < 4; i++){
            int nx = goalX + dx[i];
            int ny = goalY + dy[i];

            if (nx<0 || ny<0 || nx>=N || ny>=M || board[nx][ny] == 0) continue;
            board[nx][ny] = 1;
        }

        return board;
    }
}
