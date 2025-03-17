package 슬라이딩윈도우_투포인터;

import java.io.*;
import java.util.*;

// 음수도 고려한다면 별 문제없이 풀 수 있다.

public class b1253_다른두수로_현재수만들기 {
    private static int N;
    private static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        array = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        int cnt = 0;

        for(int i=0; i<N; i++){
            int num = array[i];

            int start = 0;
            int end = N-1;

            while(start < end){
                if(start == i){
                    start ++;
                    continue;
                }else if(end == i){
                    end--;
                    continue;
                }

                int sum = array[start] + array[end];
                if(sum == num){
                    cnt++;
                    break;
                }else if(sum < num){
                    start ++;
                }else if(sum > num){
                    end --;
                }
            }
        }

        System.out.println(cnt);
    }
}

