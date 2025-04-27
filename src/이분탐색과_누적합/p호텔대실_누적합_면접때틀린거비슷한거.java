package 이분탐색과_누적합;

import java.util.*;
class p호텔대실_누적합_면접때틀린거비슷한거 {
    public int solution(String[][] book_time) {
        int answer = 0;

        int[] dp = new int[60*60+1];
        int minTime = Integer.MAX_VALUE;
        int maxTime = Integer.MIN_VALUE;

        for(String[] book : book_time){

            String[] startTime = book[0].split(":");
            String[] endTime = book[1].split(":");

            int start = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            int end = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]) + 10;
            dp[start] ++;
            dp[end] --;
            minTime = Math.min(start, minTime);
            maxTime = Math.max(end, maxTime);
        }
        // 계산
        for(int i=minTime+1; i<= maxTime; i++){
            dp[i] += dp[i-1];

            if(dp[i] > answer) answer = dp[i];
        }

        return answer;
    }
}
