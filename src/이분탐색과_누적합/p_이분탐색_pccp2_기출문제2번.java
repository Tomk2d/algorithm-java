package 이분탐색과_누적합;

/**
 *      이런식으로 탐색해야하는 범위가 방대하고, 그중 최소 값을 찾는경우 웬만하면 이분탐색을 떠올리자.
 *      안그러면 시간초과 걸릴확률이 너무 높다
 */

public class p_이분탐색_pccp2_기출문제2번 {
    class Solution {
        private int[] levels;
        private int[] times;
        private int N;

        public int solution(int[] levels, int[] times, long limit) {
            this.levels = levels;
            this.times = times;
            this.N = times.length;

            int left = 1;
            int right = (int) 1e9; // 최대 레벨값은 이보다 작을 것
            int answer = -1;

            while(left <= right){
                int mid = left + (right - left) / 2;

                if(calTime(mid, limit)){
                    answer = mid;
                    right = mid - 1;
                } else{
                    left = mid + 1;
                }
            }

            return answer;
        }

        private boolean calTime(int myLevel, long limit){
            long sum = 0;

            int level = levels[0];
            int time = times[0];

            if(myLevel >= level){
                sum += time;
            } else{
                sum += (long) (level - myLevel + 1) * time;
            }

            for(int i = 1; i < N; i++){
                level = levels[i];
                time = times[i];

                if(myLevel >= level){
                    sum += time;
                } else{
                    sum += (long) (level - myLevel) * times[i - 1];
                    sum += (long) (level - myLevel + 1) * time;
                }

                if(sum > limit) return false;
            }

            return sum <= limit;
        }
    }

}
