package 이분탐색과_누적합;

import java.util.*;
import java.io.*;


public class b2470_이분탐색이라지만_뭔가투포인터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long[] liquids = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquids);
        int leftIdx = 0;
        int rightIdx = N-1;
        long minAbs = Long.MAX_VALUE;
        long[] answer = new long[2];

        while(leftIdx < rightIdx){
            long nowDifference = liquids[leftIdx]+liquids[rightIdx];

            if(nowDifference == 0){
                minAbs = 0;
                answer[0] = liquids[leftIdx];
                answer[1] = liquids[rightIdx];
                break;
            }

            if(Math.abs(nowDifference) <= minAbs){
                minAbs = Math.abs(nowDifference);
                answer[0] = liquids[leftIdx];
                answer[1] = liquids[rightIdx];
            }

            if(nowDifference < 0){
                leftIdx++;
            }else if(nowDifference >0){
                rightIdx--;
            }
        }

        System.out.println(answer[0]+" "+answer[1]);
    }
}

