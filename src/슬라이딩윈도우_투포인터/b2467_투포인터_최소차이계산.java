package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b2467_투포인터_최소차이계산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        int start = 0;
        int end = N - 1;
        int minValue = Integer.MAX_VALUE;
        int result1 = 0, result2 = 0;

        while (start < end) {
            int sum = array[start] + array[end];

            if (Math.abs(sum) < minValue) {
                minValue = Math.abs(sum);
                result1 = array[start];
                result2 = array[end];
            }

            // 0에 더 가까워지도록 투 포인터 이동
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(result1 + " " + result2);
    }
}
