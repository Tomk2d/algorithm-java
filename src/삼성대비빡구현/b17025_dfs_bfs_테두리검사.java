package 삼성대비빡구현;

import java.util.*;
import java.io.*;
public class b17025_dfs_bfs_테두리검사
{
    private static int N;
    private static char[][] board;
    private static boolean[][] visited;
    private static int maxSize = 0;
    private static int minAir = Integer.MAX_VALUE;
    private static List<Point> block = new ArrayList<>();
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int nowAir = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new char[N][N];
        visited = new boolean[N][N];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<N; j++){
                board[i][j] = input.charAt(j);
            }
        }

        // print();
        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                if(board[x][y] == '#' && !visited[x][y]){
                    visited[x][y] = true;
                    Point now = new Point(x, y);
                    List<Point> list = new ArrayList<>();
                    list.add(now);
                    nowAir = countAir(x, y);
                    countBlock(now, list);
                }
            }
        }

        System.out.println(maxSize + " " + minAir);

    }

    private static void countBlock(Point now, List<Point> list){
        if(list.size() > maxSize){
            block = list;
            maxSize = list.size();
            minAir = nowAir;
        }else if(list.size() == maxSize && nowAir < minAir){
            block = list;
            maxSize = list.size();
            minAir = nowAir;
        }

        for(int d=0; d<4; d++){
            int nx = now.x + dx[d];
            int ny = now.y + dy[d];

            if(inBox(nx, ny) && !visited[nx][ny]){
                visited[nx][ny] = true;
                Point next = new Point(nx, ny);
                list.add(next);
                nowAir += countAir(nx, ny);
                countBlock(next, list);
            }
        }
    }

    private static int countAir(int x, int y){
        int result = 0;

        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(!inBox(nx, ny)) result ++;
        }
        return result;
    }

    // true : 아이스크림 박스 안
    // false : 공기나 외부
    private static boolean inBox(int x, int y){
        return x>=0 && y>=0 && x<N && y<N && board[x][y] == '#';
    }

    private static void print(){
        System.out.println("====board====");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(board[i][j] + " ");
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

