package 배열과문자열;

public class pccp실전문제_2_2 {
    class Solution {
        int[] diffs;
        int[] times;
        long limit;
        int N;

        public int solution(int[] diffs, int[] times, long limit) {
            this.diffs = diffs;
            this.times = times;
            this.limit = limit;
            N = diffs.length;

            int left = 0;
            int right = (int) 1e9;
            int answer = right;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (isPossible(mid)) {
                    answer = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return answer;
        }

        private boolean isPossible(int level){
            long nowTime = 0;

            for(int i=0; i<N; i++){
                int diff = diffs[i];
                int pay = times[i];

                if(level >= diff){
                    nowTime += pay;
                }else if(level < diff){
                    int d = diff - level;
                    if(i == 0) nowTime += (long) pay*d;
                    else nowTime += (long) (pay + times[i-1])*d;

                    nowTime += pay;
                }
            }
            if(nowTime <= limit) return true;
            return false;
        }
    }
}
