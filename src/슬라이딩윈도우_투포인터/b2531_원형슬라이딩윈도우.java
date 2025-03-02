package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b2531_원형슬라이딩윈도우 {
    static int maxSize =0;
    static int coupon =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 초밥수
        int d = Integer.parseInt(st.nextToken());   // 초밥의 가지수
        int k = Integer.parseInt(st.nextToken());   // 연속해서 먹는 수. 부분집합
        coupon = Integer.parseInt(st.nextToken()); // 쿠폰번호

        int[] board = new int[N];

        for(int i=0; i<N; i++){
            int n = Integer.parseInt(br.readLine());
            board[i] = n;
        }

        Deque<Integer> P = new ArrayDeque<>();

        for(int i=0; i<k; i++){
            P.add(board[i]);
        }

        sizeUpdate(P);

        for(int i=k; i<N+k; i++){
            P.add(board[i%N]);
            P.pollFirst();

            sizeUpdate(P);
        }
        System.out.println(maxSize);
    }

    public static void sizeUpdate(Deque<Integer> p){
        Set<Integer> set = new HashSet<>(p);

        set.add(coupon);

        maxSize = Math.max(maxSize, set.size());

    }

}

