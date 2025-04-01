package DP;

import java.util.*;
import java.io.*;

public class b17404_누적합과_까다로운_조건 {
    private static int N;
    private static int[][] board;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 집의 색을 각각 R, G, B로 설정하는 경우를 모두 고려
        for (int firstColor = 0; firstColor < 3; firstColor++) {
            List<List<Point>> dp = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                dp.add(new ArrayList<>());
                for (int j = 0; j < 3; j++) {
                    dp.get(i).add(new Point(Integer.MAX_VALUE, firstColor, 0));
                }
            }

            // 첫 번째 집 초기화
            dp.get(0).set(firstColor, new Point(board[0][firstColor], firstColor, 0));

            // DP 테이블 채우기
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < 3; j++) {
                    Point now = dp.get(i).get(j);
                    if (now.pay == Integer.MAX_VALUE) continue;

                    for (int nextIdx = 0; nextIdx < 3; nextIdx++) {
                        if (i + 1 == N - 1 && firstColor == nextIdx) continue;
                        if (nextIdx == j) continue;

                        int nextPay = now.pay + board[i + 1][nextIdx];
                        if (nextPay < dp.get(i + 1).get(nextIdx).pay) {
                            Point next = dp.get(i + 1).get(nextIdx);
                            next.pay = nextPay;
                            next.firstIdx = firstColor;
                            next.preIdx = j;
                        }
                    }
                }
            }

            // 마지막 집에서 최소값 갱신
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != firstColor) {
                    answer = Math.min(answer, dp.get(N - 1).get(lastColor).pay);
                }
            }
        }

        System.out.println(answer);
    }

    private static class Point {
        int pay;
        int firstIdx;
        int preIdx;

        Point(int pay, int firstIdx, int preIdx) {
            this.pay = pay;
            this.firstIdx = firstIdx;
            this.preIdx = preIdx;
        }
    }
}

