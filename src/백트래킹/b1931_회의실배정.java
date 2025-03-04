package 백트래킹;

import java.io.*;
import java.util.*;

public class b1931_회의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Time> times = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times.add(new Time(start, end));
        }

        // 종료 시간이 빠른 순서대로 정렬 (종료 시간이 같다면 시작 시간이 빠른 순서)
        Collections.sort(times, (a, b) -> a.end == b.end ? Integer.compare(a.start, b.start) : Integer.compare(a.end, b.end));

        int maxCount = 0;
        int nowTime = 0;

        for (Time time : times) {
            if (nowTime <= time.start) {
                maxCount++;
                nowTime = time.end;
            }
        }

        System.out.println(maxCount);
    }

    private static class Time {
        int start, end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

