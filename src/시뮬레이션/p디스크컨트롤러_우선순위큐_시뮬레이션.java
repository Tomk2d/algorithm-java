package 시뮬레이션;

import java.util.*;

class p디스크컨트롤러_우선순위큐_시뮬레이션 {
    public int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<Process> queue = new PriorityQueue<>(
            Comparator.comparingInt((Process a) -> a.cost)
                .thenComparingInt(a -> a.start)
                .thenComparingInt(a -> a.number)
        );

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        int idx = 0;
        int time = 0;
        int result = 0;

        while (idx < jobs.length || !queue.isEmpty()) {
            // 현재 시간 이전에 도착한 작업은 전부 대기열에 추가
            while (idx < jobs.length && jobs[idx][0] <= time) {
                queue.add(new Process(jobs[idx][0], jobs[idx][1], idx));
                idx++;
            }

            if (!queue.isEmpty()) {
                Process now = queue.poll();
                time = Math.max(time, now.start) + now.cost;
                result += time - now.start;
            } else {
                // 작업이 없으면 다음 작업 시간까지 점프
                time = jobs[idx][0];
            }
        }

        answer = result / jobs.length;
        return answer;
    }

    private class Process {
        int start;
        int cost;
        int number;

        Process(int start, int cost, int number) {
            this.start = start;
            this.cost = cost;
            this.number = number;
        }
    }
}

