package 슬라이딩윈도우_투포인터;

import java.io.*;
import java.util.*;

// 누적합에서 윈도우 축소하는 부분에서 순서를 바꿔써서 오답이 나왔다.
// 먼저 누적합에서 - 하고, 이후에 윈도우를 축소해야한다

public class b1806_최소길이_부분배열_누적합 {
    private static int N;
    private static int minNum;
    private static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        minNum = Integer.parseInt(st.nextToken());

        array = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int cnt = Integer.MAX_VALUE;

        while(end <= N-1 && end >= start){
            sum += array[end];

            while(sum >= minNum){
                cnt = Math.min(end-start+1 , cnt);

                // 이 두줄 순서 중요. 일단 빼고, 윈도우 축소해야함.
                sum -= array[start];
                start ++;
            }

            end++;
        }

        System.out.println(cnt == Integer.MAX_VALUE ? 0 : cnt);
    }
}

