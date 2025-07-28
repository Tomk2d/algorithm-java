package 이분탐색과_누적합;
import java.util.*;
import java.io.*;

public class c오토에버_실력테스트 {
    static int N, M;
    static int[] positions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        positions = new int[N];
        for (int i = 0; i < N; i++) {
            positions[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(positions);

        int distanceMin = 1;
        int distanceMax = positions[N - 1] - positions[0];

        int answer = 0;
        while(distanceMin <= distanceMax){
            int distanceMid = (distanceMax + distanceMin)/2;
            if(isPossible(distanceMid)){
                answer = distanceMid;
                distanceMin = distanceMid + 1;
            }else{
                distanceMax = distanceMid - 1;
            }
        }
    }

    private static boolean isPossible(int distance){
        int cnt = 1;
        int pre = positions[0];

        for(int i=1; i<N; i++){
            if(positions[i] - pre >= distance){
                cnt ++;
                pre = positions[i];
            }
        }

        return cnt >= M;
    }

}
