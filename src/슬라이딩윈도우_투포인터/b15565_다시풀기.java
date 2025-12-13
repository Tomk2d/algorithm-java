package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b15565_다시풀기
{
    public static int N, target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        int[] array = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int lion = 0;
        if(array[0] == 1) lion ++;

        if(lion == target) {
            System.out.println(1);
            return;
        }

        end++;
        int minN = Integer.MAX_VALUE;
        while(end < N){

            if(array[end] == 1) lion ++;

            while(lion>=target && start<=end){
                minN = Math.min(end+1-start, minN);

                if(array[start] == 1) lion--;
                start ++;
            }
            end ++;
        }

        System.out.println(minN==Integer.MAX_VALUE ? -1 : minN);
    }

}

