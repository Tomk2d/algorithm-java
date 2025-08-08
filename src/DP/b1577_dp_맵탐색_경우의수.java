package DP;

import java.util.*;
import java.io.*;

/*
*       자 "경우의수" 나오면 무조건 DP다
*       이건 0,0 에서 N,M 까지 가는 최단경로를 보장한다 -> 이 멘트가 중요한데, 무조건 N+M 만큼만 움직인다
*       즉, 최단 경로를 보장하려면 무조건 오른쪽 이동, 하단 이동 만 해야한다.
*       경우의 수를 구하는거니까, 이전까지 왔던 경로들을 전부 더해서 합해나간다.
*       이때 갈 수 없는 길을 2가지로 나눠서 저장한다.
*       하단으로 못가는 길과 우측으로 못가는길.
*       이후 길이 안막혀 있으면 이전거를 더해준다.
*
*
* */

public class b1577_dp_맵탐색_경우의수 {
    private static int N, M, loadN;
    private static boolean[][] rightBlock;
    private static boolean[][] upBlock;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rightBlock = new boolean[N+1][M+1]; // 오른쪽 이동 불가
        upBlock = new boolean[N+1][M+1];    // 위(또는 아래) 이동 불가

        loadN = Integer.parseInt(br.readLine());

        for (int i = 0; i < loadN; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st2.nextToken());
            int y1 = Integer.parseInt(st2.nextToken());
            int x2 = Integer.parseInt(st2.nextToken());
            int y2 = Integer.parseInt(st2.nextToken());

            if (x1 == x2) { // 세로 도로
                int minY = Math.min(y1, y2);
                upBlock[x1][minY] = true;
            } else { // 가로 도로
                int minX = Math.min(x1, x2);
                rightBlock[minX][y1] = true;
            }
        }

        long[][] dp = new long[N+1][M+1];
        dp[0][0] = 1;

        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= M; y++) {
                if (x > 0 && !rightBlock[x-1][y]) {
                    dp[x][y] += dp[x-1][y];
                }
                if (y > 0 && !upBlock[x][y-1]) {
                    dp[x][y] += dp[x][y-1];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}

