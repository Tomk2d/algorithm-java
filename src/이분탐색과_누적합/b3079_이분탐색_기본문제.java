package 이분탐색과_누적합;

import java.util.*;
import java.io.*;

// left ~ right 의 범위는 검사할 시간대.
// 시간대를 반씩 나누면서(mid) 어느정도 까지 가능한지 구하는거
// 이 시간대에서 중간값을 구함(mid)
// 이 mid 동안 각 심사대(배열의 요소) 가 몇 싸이클씩 각각 진행되는지 구함
// 만약 [1, 4, 2, 7] 의 심사대들이 10 분이라는 mid 동안 이분이라는
// 10번, 2번, 5번, 1번 진행됨.
// 근데 처리할 사람이 10 명이라면 시간이 남아돔
// right 값을 mid-1로 줄이면서 다시 진행하는거임

public class b3079_이분탐색_기본문제 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 심사대 수
        int M = Integer.parseInt(st.nextToken()); // 사람 수

        long[] times = new long[N]; // 심사대 별 처리 시간

        long maxTime = 0;       // 가장 오래 걸리는 시간
        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }


        long left = 0;          // 초기는 최소 값.
        long right = maxTime * M;   // 최악의 경우: 가장 느린 심사관이 모두 처리하는 경우
        long result = right;    // 일단 최악의 경우가 가능하니까 저장.

        while(left <= right){
            long mid = (left + right) / 2;   //  중간값
            long sumPerson = 0;

            for(long time : times){
                long person = mid / time;
                sumPerson += person;

                if(sumPerson > M) break;
            }

            // 시간이 남을때
            if(sumPerson >= M){
                result = mid;
                right = mid-1;
            }else{  //  시간을 더 써야 할때
                left = mid+1;
            }
        }

        System.out.println(result);
    }
}

