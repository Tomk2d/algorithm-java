package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b15565_최소길이_부분배열_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] array = new int[N];
        for(int i=0; i<N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int start=0, end=0, count=0;
        int minLength = Integer.MAX_VALUE;

        while(end < N){
            // 끝자리 1이면 카운트
            if(array[end] == 1){
                count ++;
            }

            // 일단 개수 충족하면 들어와. 앞자리 줄이면서 축소
            while(count >= goal){
                minLength = Math.min(minLength, end-start+1);

                // 앞 자리에 있는애들 줄이면서, 윈도우 축소
                if(array[start] == 1){
                    count --;
                }
                start ++;
            }
            // 뒷 자리 애들 늘리면서, 윈도우 확장
            end++;
        }
        System.out.println(minLength==Integer.MAX_VALUE ? -1 : minLength);
    }
}
