package 시뮬레이션;
import java.util.*;
public class pccp모의고사2_3 {


    class Solution {
        public int solution(int[] menu, int[] orders, int k) {
            int answer = 0;
            int n = orders.length;

            // 손님 수 n, 최대 시간은 누적된 제작시간이 될 수 있음
            int[] dp = new int[100000000]; // 충분한 크기로 선언 (예: n * 최대 제작시간)

            int currentTime = 0;

            for (int i = 0; i < n; i++) {
                int arrival = i * k;
                int start = Math.max(currentTime, arrival);
                int end = start + menu[orders[i]];
                currentTime = end;

                dp[arrival]++;
                dp[end]--;
            }

            for (int i = 1; i < dp.length; i++) {
                dp[i] += dp[i - 1];
                answer = Math.max(answer, dp[i]);
            }

            return answer;
        }
    }

}
