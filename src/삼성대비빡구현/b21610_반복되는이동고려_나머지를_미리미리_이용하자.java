package 삼성대비빡구현;

import java.util.*;
import java.io.*;

/*
*       해당 문제는 distance 의 크기가 맵의 몇배수로 주어지면 인덱스 에러나 시간초과가 날 수 있다.
*       이런 문제를 코테에서도 접했었는데, 거리 자체를 미리 % N 해서 계산하는게 좋겠다
*       어차피 배수의 경우에는 나머지만큼만 이동하니까
* */

public class b21610_반복되는이동고려_나머지를_미리미리_이용하자 {
    private static int N, turn;
    private static int[][] board;
    private static List<Point> clouds = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        turn = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 세팅
        clouds.add(new Point(N-1, 0));
        clouds.add(new Point(N-1, 1));
        clouds.add(new Point(N-2, 0));
        clouds.add(new Point(N-2, 1));

        for(int i=0; i<turn; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) -1;
            int distance = Integer.parseInt(st.nextToken());

            // 1. 구름 이동
            moveCloud(d, distance);

            // 2. 비내림과 물복사
            boolean[][] visited = makeItRainAndGetWater();

            // 3. 새로운 구름 생성
            changeClouds(visited);
        }
        int answer = 0;
        for(int[] b : board){
            for(int water: b){
                answer += water;
            }
        }

        System.out.println(answer);
    }

    private static void changeClouds(boolean[][] visited){
        List<Point> newClouds = new ArrayList<>();

        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                if(!visited[x][y] && board[x][y] >= 2){
                    board[x][y] -= 2;
                    newClouds.add(new Point(x, y));
                }
            }
        }

        clouds = newClouds;
    }

    private static boolean[][] makeItRainAndGetWater(){
        boolean[][] visited = new boolean[N][N];
        // 비내림
        for(Point cloud : clouds){
            board[cloud.x][cloud.y] ++;
            visited[cloud.x][cloud.y] = true;
        }

        // 물복사
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1};
        for(Point cloud : clouds){
            int cnt = 0;
            for(int d=0; d<4; d++){
                int nx = cloud.x + dx[d];
                int ny = cloud.y + dy[d];

                if(inBoard(nx, ny) && board[nx][ny] > 0) cnt ++;
            }
            board[cloud.x][cloud.y] += cnt;
        }

        return visited;
    }

    private static void moveCloud(int d, int distance){
        // ← ↖ ↑ ↗ → ↘ ↓ ↙
        int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

        for(Point cloud : clouds){
            cloud.x = (N + cloud.x + (dx[d] * distance)%N ) % N;
            cloud.y = (N + cloud.y + (dy[d] * distance)%N ) % N;
        }
    }

    private static boolean inBoard(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    private static void printBoard(){
        System.out.println(" ========== ");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printCloud(){
        System.out.println(" ========== ");
        for(Point cloud : clouds){
            System.out.println(" (" + cloud.x + " , " + cloud.y + ") ");
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

