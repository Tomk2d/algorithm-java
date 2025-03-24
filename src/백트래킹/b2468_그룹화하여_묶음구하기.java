package 백트래킹;

import java.util.*;
import java.io.*;

public class b2468_그룹화하여_묶음구하기 {
    private static int N;
    private static int[][] board;
    private static int answer = 0;
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        int maxRain = 0;
        int minRain = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;

                maxRain = Math.max(maxRain, num);
                minRain = Math.min(minRain, num);
            }
        }

        for(int rain=minRain; rain < maxRain; rain++){

            visited = new boolean[N][N];
            int cnt =0;

            for(int x=0; x<N; x++){
                for(int y=0; y<N; y++){
                    if(!visited[x][y] && board[x][y] > rain){
                        checkBlock(rain, x, y);
                        cnt ++;
                    }
                }
            }

            answer = Math.max(cnt, answer);
        }

        System.out.println(answer==0?1:answer);
    }

    private static void checkBlock(int rain, int x, int y){
        visited[x][y] = true;

        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<N && !visited[nx][ny] && board[nx][ny] > rain){
                checkBlock(rain, nx, ny);
            }
        }
    }
}

