package 시뮬레이션;
import java.util.*;
public class pccp모의고사2_2 {

    class Solution {
        public int solution(int[] ability, int number) {
            int answer = 0;

            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));

            for(int n: ability){
                queue.add(n);
            }

            while(number--> 0){
                int a = queue.poll();
                int b = queue.poll();
                int sum = a + b;

                queue.add(sum);
                queue.add(sum);
            }

            for(int n : queue){
                answer += n;
            }

            return answer;
        }
    }
}
