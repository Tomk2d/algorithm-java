package DP많이나오는유형;

import java.io.*;


/*
        이 문제의 핵심은 dp 배열과 points 배열을 따로 관리하는데 있다
        계산을 dp 적 관점으로 작은 묶음 단위로 본다.
        먼저 초기 값으로 1, 2, 3 번째에서 할당받을 수 있는 가장 큰값을 설정한다
        이후 4번째 부터 돌면서
        ** 현재 묶음이 1개 짜리 **
        dp[2번째 전] + point[현재 값]  <-- 2번째 전과 현재는 어차피 3개가 연속될 수 없음
        ** 현재 묶음이 2개짜리 연속 **
        dp[3번째 전] + point[1번째 전] + point[현재 값] <-- 한개전 원래 점수 + 3번째 전 누적 최대 값

        이런식으로 dp 와 point 배열을 둘다 사용하여 규직 자체를 로직으로 짜버린다
*/
public class b3579_누적합_계단오르기 {
    private static int N;
    private static int[] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        points = new int[N+1];

        for(int i=1; i<=N; i++){
            points[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(points[1]);
            return;
        } else if (N == 2) {
            System.out.println(points[1] + points[2]);
            return;
        }

        int[] dp = new int[N+1];
        dp[1] = points[1];
        dp[2] = points[1] + points[2];
        dp[3] = Math.max(points[1]+points[3], points[2]+points[3]);


        for(int i=4; i<=N; i++){
            // 2칸 점프 했을때와, 1칸 점프 + 3칸전에 2칸 점프
            dp[i] = Math.max(dp[i-2] + points[i] , dp[i-3] + points[i-1] + points[i]);
        }

        System.out.println(dp[N]);
    }
}

