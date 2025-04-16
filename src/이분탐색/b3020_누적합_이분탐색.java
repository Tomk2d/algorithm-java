package 이분탐색;

import java.io.*;
import java.util.*;

// 해당 문제의 핵심은 누적합이다.
// 결국 bottomUp 의 경우, 높이가 높은 놈은 더 작은 높이일때 무조건 부딪힌다
// topDown 또한 경우, 높이가 낮은 놈은 높이가 작은 놈일때 무조건 부딪힌다.
// 이걸 인덱스 기준이 아닌 높이 기준으로 먼저 생각한다.
// 이후 두개의 배열을 반대로 인덱스를 계산하면 된다.

public class b3020_누적합_이분탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] bottomUp = new int[H + 1];
        int[] topDown = new int[H + 1];

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());

            if (i % 2 == 0) {
                // 높이가 idx 임. 즉 3번째 인덱스에 ++ 하는건, 높이가 3인애를 센거임
                bottomUp[height]++;
            } else {
                topDown[height]++;
            }
        }

        // 위에서 부터 내려오면서 누적합 계산
        for(int i=H; i>1; i--){
            bottomUp[i-1] += bottomUp[i];
            topDown[i-1] += topDown[i];
        }

        int minCrush = Integer.MAX_VALUE;
        int cnt = 0;

        for(int h =1; h<= H; h++){
            int nowCrush = bottomUp[h] + topDown[H-h+1];

            if(nowCrush < minCrush){
                cnt = 1;
                minCrush = nowCrush;
            }else if(nowCrush == minCrush){
                cnt ++;
            }
        }

        System.out.println(minCrush + " " + cnt);
    }
}

