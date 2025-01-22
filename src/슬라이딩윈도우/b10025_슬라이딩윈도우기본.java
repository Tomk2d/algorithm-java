package 슬라이딩윈도우;

import java.util.*;
import java.io.*;

public class b10025_슬라이딩윈도우기본 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int windowSize = K*2 + 1;
        int maxIceLocation = 0;


        int[] board = new int[1000001];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int kg = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            board[idx] = kg;
            maxIceLocation = Math.max(maxIceLocation, idx);
        }

        int maxSum =0;
        int currentSum =0;
        for(int i=0; i< Math.min(windowSize, maxIceLocation+1); i++){
            currentSum += board[i];
        }
        maxSum = currentSum;

        for(int i=windowSize; i<=maxIceLocation; i++){
            currentSum += board[i];
            currentSum -= board[i-windowSize];
            maxSum = Math.max(currentSum, maxSum);
        }

        System.out.println(maxSum);
    }
}

