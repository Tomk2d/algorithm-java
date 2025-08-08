package 이분탐색과_누적합;

import java.util.*;
import java.io.*;

/*
*       이런 1차원 배열 범위가 나오면 무조건 이분탐색, 투포인터/슬라이딩윈도우, dp 중에 있다.
*       기준이 너무 불명확하고, 대략적인 범위 탐색일때 이분탐색이 유력하다.
*       이분탐색에서 제일 중요한건 "뭐를 찾을까???" 이다.
*       결국 여기서는 가장 가까운 공유기 사이의 거리가 최대인것. 즉, 균일하게 평균적으로 멀~리 떨어져 있는 경우를 구해야한다.
*       이 문제는 해당 거리를 구해야 하기 때문에 "mid = 거리" 로 탐색한다.
*       start=거리의 최소값, end=거리의 최대값 을 미리 정의하고,
*       mid 를 줄여가면서 탐색한다.
*       이때 배치는 "정렬된 집들"을 대상으로 하고, 거리 이상일 경우를 count 한다.
*       이 "이상이 된 거리의 수"가 공유기의 수를 넘으면 가능이고, 가능하면 mid 를 뒤로 미뤄서 더 큰 수를 해본다.
*       안되면 mid 를 줄여서 작은 거리를 해본다.
*       이런식으로 반씩 줄여가면서 검사한다.
* */

public class b2110_이분탐색_이건무조건_할줄알아야함
{
    private static int N, M;
    private static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        houses = new int[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            houses[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(houses);

        int left = 1;
        int right = houses[N-1] - houses[0];
        int answer = 0;

        while(left <= right){
            int mid = (left+right) / 2;

            if(isPossible(mid)){
                left = mid + 1;
                answer = mid;
            }else{
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isPossible(int distance){
        int cnt = 1;
        int pre = houses[0];

        for(int idx=1; idx<N; idx++){
            if(houses[idx]-pre >= distance){
                cnt++;
                pre = houses[idx];
            }
        }

        return cnt >= M;
    }
}

