package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b_13422_원형슬라이딩윈도우
{
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int totalN = Integer.parseInt(st.nextToken());

        for(int i=0; i<totalN; i++){
            answer = 0;
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int maxMoney = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] board = new int[n];
            for(int j=0; j<n; j++){
                board[j] = Integer.parseInt(st.nextToken());
            }
            solution(n, p, maxMoney, board);
            System.out.println(answer);

        }
    }

    public static void solution(int n, int p, int maxMoney, int[] board){
        int currentSum = 0;
        for(int i=0; i<p; i++){
            currentSum += board[i];
        }
        if(currentSum < maxMoney){
            answer ++;
        }

        if(n == p) return;  // 중복 방지

        for(int i=p; i< n+p-1; i++){
            currentSum += board[i%n];
            currentSum -= board[(i-p)%n];

            if(currentSum < maxMoney){
                answer ++;
            }
        }

    }
}
