package 이분탐색과_누적합;

import java.io.*;
import java.util.*;

public class b20002_2차원배열_부분집합_누적합 {
    private static int N;
    private static int[][] board;
    private static int[][] calBoard;
    private static int maxMoney = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calBoard = new int[N][N];

        for(int y=0; y<N; y++){
            calBoard[0][y] = board[0][y];
        }

        // 누적합 계산
        for(int x=1; x<N; x++){
            for(int y=0; y<N; y++){
                calBoard[x][y] += calBoard[x-1][y] + board[x][y];
            }
        }

        // 정사각형 크기
        for(int size=1; size<=N; size++){
            for(int x=0; x<=N-size; x++){
                for(int y=0; y<=N-size; y++){
                    getMoney(size, x, y);
                }
            }
        }
        System.out.println(maxMoney);
    }

    private static void getMoney(int size, int startX, int startY){
        int money = 0;
        int endX = startX+size-1;
        int endY = startY+size-1;

        // 합 더하기
        for(int i=startY; i<=endY; i++){
            money += calBoard[endX][i];
        }

        if(startX <= 0){
            maxMoney = Math.max(maxMoney, money);
            return;
        }

        for(int i=startY; i<=endY; i++){
            money -= calBoard[startX-1][i];
        }

        maxMoney = Math.max(maxMoney, money);
    }
}

