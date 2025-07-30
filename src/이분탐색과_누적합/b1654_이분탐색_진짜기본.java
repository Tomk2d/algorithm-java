package 이분탐색과_누적합;

import java.util.*;
import java.io.*;

public class b1654_이분탐색_진짜기본 {
    private static int N;
    private static long goal;
    private static long[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        goal = Long.parseLong(st.nextToken());

        lines = new long[N];
        for (int i = 0; i < N; i++) {
            lines[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(lines);

        long start = 1;
        long end = lines[N - 1];
        long answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (isPossible(mid)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isPossible(long mid) {
        long cnt = 0;
        for (long line : lines) {
            cnt += line / mid;
        }
        return cnt >= goal;
    }
}

