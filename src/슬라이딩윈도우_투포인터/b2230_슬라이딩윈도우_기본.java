package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b2230_슬라이딩윈도우_기본 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] array = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        if (target == 0) {
            System.out.println(0);
            return;
        }

        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;

        while (end < N) {
            int gap = array[end] - array[start];

            if (gap >= target) {
                answer = Math.min(answer, gap);
                start++;  // start를 증가시켜 더 작은 차이를 찾음
            } else {
                end++;  // gap이 target보다 작으면 end를 증가
            }
        }

        System.out.println(answer);
    }
}

