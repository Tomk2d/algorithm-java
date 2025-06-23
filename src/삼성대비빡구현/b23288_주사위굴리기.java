package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b23288_주사위굴리기 {
    private static int N, M, K;
    private static int[][] board;
    private static int[][] pointBoard;
    private static int[][] dp;
    // 우 하 좌 상
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    // 주사위 초기값
    private static int diceD = 0;   // 우 방향
    private static int top = 1, bottom = 6, right = 3, left = 4, up = 2, down = 5;  // 윗면 아랫면 우 좌 상 하
    private static int diceX=0, diceY=0;

    // 이동경로. 누적 점수로 기록
    private static List<Integer> moves = new ArrayList<>();

    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        pointBoard = new int[N][M];
        dp = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0. 묶음 수 미리 세어두기
        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                if(pointBoard[x][y] == 0){
                    countGroup(x, y);
                }
            }
        }

        // print(pointBoard);  // ===== 이 위로 검증 완료

        // 시뮬레이션 시작
        for(int i=0; i<K; i++){
            // 1. 주사위 이동 & 주사위 재설정
            move();

            // 2. 점수 얻기
            int point = board[diceX][diceY] * pointBoard[diceX][diceY];
            answer += point;
            dp[diceX][diceY] = answer;
            moves.add(answer);

            // 3. 방향 재설정
            if(bottom > board[diceX][diceY]){
                diceD = (diceD + 1) % 4;
            }else if(bottom < board[diceX][diceY]){
                diceD = (4 + diceD - 1) % 4;
            }

            //System.out.println("turn : " + (i+1) + "번째" + " d : " + diceD + " x : " + (diceX+1) + " y : " + (diceY+1));
            //System.out.println("top : " + top + " bottom : " + bottom + " right : " + right + " left : " + left + " up : " + up + " down : " + down);

        }
        System.out.println(answer);
    }

    private static void move(){
        // 위치 이동
        int nx = diceX + dx[diceD];
        int ny = diceY + dy[diceD];

        if(nx<0 || ny<0 || nx>=N || ny>=M){
            diceD = (diceD + 2) % 4;
            nx = diceX + dx[diceD];
            ny = diceY + dy[diceD];
        }

        diceX = nx;
        diceY = ny;

        // 주사위 재설정
        if(diceD == 0){         // 우
            int temp = top;
            top = left;
            left = bottom;
            bottom = right;
            right = temp;
        }else if(diceD == 1){    // 하
            int temp = top;
            top = up;
            up = bottom;
            bottom = down;
            down = temp;
        }else if(diceD == 2){   // 좌
            int temp = top;
            top = right;
            right = bottom;
            bottom = left;
            left = temp;
        }else if(diceD == 3){   // 상
            int temp = top;
            top = down;
            down = bottom;
            bottom = up;
            up = temp;
        }
    }

    private static void countGroup(int x, int y){
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));

        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;

        List<Point> member = new ArrayList<>();

        while(!queue.isEmpty()){
            Point now = queue.pollFirst();
            member.add(now);

            for(int d=0; d<4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx>=0 && ny>=0 && nx<N && ny<M && !visited[nx][ny] && board[x][y] == board[nx][ny]){
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        int n = member.size();
        for(Point now : member){
            pointBoard[now.x][now.y] = n;
        }
        return ;
    }


    private static void print(int[][] map){
        System.out.println();

        for(int x=0; x<map.length; x++){
            for(int y=0; y<map[0].length; y++){
                System.out.print(map[x][y] + " ");
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
