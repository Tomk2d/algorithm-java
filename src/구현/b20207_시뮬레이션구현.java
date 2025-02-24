package 구현;

import java.io.*;
import java.util.*;

public class b20207_시뮬레이션구현
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] board = new int[366];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for(int j=start; j<=end; j++){
                board[j] ++;
            }
        }

        int answer = 0;
        boolean inBox = false;
        int boxStart = 0;
        int boxEnd = 0;
        int maxHeight =0;

        for(int i=1; i<=365; i++){
            int now = board[i];

            maxHeight = Math.max(maxHeight, now);

            // 박스 진입
            if(!inBox && now!=0){
                boxStart = i;
                inBox = true;
                // 박스 나감. 이미 박스 안이고, 0만났을때. -> 근데 이때, 365 일때가 고려 안되는 문제 있음
            }else if(inBox && now==0){
                boxEnd = i-1;
                answer += (boxEnd - boxStart + 1) * maxHeight;
                inBox = false;
                maxHeight = 0;
            }
        }
        // 365 일까지 꽉차 있을때, 한번 더 계산
        if(inBox){
            answer += (365 - boxStart +1) * maxHeight;
        }

        System.out.println(answer);
    }
}

