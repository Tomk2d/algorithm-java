package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b15565_슬라이딩윈도우_최소배열길이
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] dolls = new int[N];
        for(int i=0; i<N; i++){
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        int minCnt = Integer.MAX_VALUE;
        int start=0;
        int end = 0;
        int rionCnt=0;

        while(end < N){
            int doll = dolls[end];
            if(doll == 1){
                rionCnt ++;
            }

            while(rionCnt >= K){
                minCnt = Math.min(minCnt, end-start+1);

                doll = dolls[start];
                if(doll == 1){
                    rionCnt --;
                }
                start ++;
            }

            end ++;
        }

        System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
    }
}

