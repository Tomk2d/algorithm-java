package 슬라이딩윈도우;

import java.util.*;
import java.io.*;

public class b15961_원형슬라이딩윈도우_좋은성능_별도의_카운트
{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());       // 접시수
        int d = Integer.parseInt(st.nextToken());       // 초밥의 가지수
        int p = Integer.parseInt(st.nextToken());       // 연속 초밥. 부분집합
        int coupon = Integer.parseInt(st.nextToken());  // 무료인 쿠폰

        int[] board = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            board[i] = Integer.parseInt(st.nextToken());
        }

        // int[] current_count = new int[3000001];

        Map<Integer, Integer> current_count = new HashMap<>();
        for(int i=0; i<p; i++){
            int n = board[i];
            current_count.put(n, current_count.getOrDefault(board[i], 0)+ 1);
        }

        int maxSize = 0;

        maxSize = current_count.size();
        if(!current_count.containsKey(coupon)){
            maxSize ++;
        }

        for(int i=p; i<N+p; i++){

            // 추가 스시
            int sushi = board[i%N];
            // 추가하기
            current_count.put(sushi, current_count.getOrDefault(sushi,0)+1);

            // 제거 스시
            sushi = board[(i-p)%N];
            // 스시 개수 줄임
            current_count.put(sushi, current_count.get(sushi)-1);
            if(current_count.get(sushi) ==0){
                current_count.remove(sushi);
            }

            int current_size = current_count.size();
            if(!current_count.containsKey(coupon)){
                current_size ++;
            }

            maxSize = Math.max(maxSize, current_size);
        }

        System.out.println(maxSize);
    }
}

