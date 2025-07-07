package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

/**
 * 이 문제는 꽤나 어렵다. 메모리 초과가 났었는데 왜 나는지 몰랐다.
 * 근데 결국 start 를 늘려서 윈도우 크기를 줄여주는 과정에서도 일일히 검사할 필요가 없었던 것이다
 * 그저 왜냐하면 이미 이전에 검사했던 로직이기 때문.
 * 그래서 그냥 윈도우 크기를 저장해주면 된다.
 * 틀리고도 왜틀린지 모르기 딱좋다
 * */

public class b13144_슬라이딩윈도우_꽤나어려움 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] board = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> counter = new HashMap<>();
        int start = 0, end = 0;
        long answer = 0; // 결과가 클 수 있으므로 long

        while(end < N){
            int num = board[end];
            counter.put(num, counter.getOrDefault(num, 0) + 1);

            // 중복이 생기면 start 이동
            while(counter.get(num) > 1){
                int startNum = board[start];
                counter.put(startNum, counter.get(startNum) - 1);
                start++;
            }

            // 현재 구간 [start, end]는 중복 없는 수열
            answer += (end - start + 1);
            end++;
        }

        System.out.println(answer);
    }
}

