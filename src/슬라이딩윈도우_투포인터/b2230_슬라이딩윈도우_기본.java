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
        for(int i=0; i<N; i++){
            array[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);

        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;

        while(end < N){
            int diff = array[end] - array[start];

            if(diff >= target){
                answer = Math.min(answer, diff);
                if(start+1 <= end){
                    start ++;
                }else{
                    end++;
                }
            }else{
                end++;
            }
        }

        System.out.println(answer);
    }
}

