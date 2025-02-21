package 슬라이딩윈도우;

import java.io.*;
import java.util.*;

public class b2559_슬라이딩윈도우_누적합 {
    private static int maxTemp = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] days = new int[N];
        for(int i=0; i<N; i++){
            days[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for(int i=0; i<M; i++){
            sum += days[i];
        }
        maxTemp = Math.max(sum, maxTemp);

        for(int i=0; i<N-M; i++){
            int start = i;
            int end = i+M;
            sum -= days[start];
            sum += days[end];

            maxTemp = Math.max(sum, maxTemp);
        }

        System.out.println(maxTemp);
    }
}

