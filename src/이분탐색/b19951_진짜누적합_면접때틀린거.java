package 이분탐색;

import java.io.*;
import java.util.*;

// 이런 범위(range) 마다 +1 을 해주거나 어떠한 수를 더해주고,
// 그 중, 최대값이나 최소값이라던가 해당 내용을 전부 출력하는 문제가 나오면
// 무조건 누적합이다
// 배열의 맨앞, 맨뒤에 +1 의 크기로(즉, N+2) 마련하고,
// 요건이 충족되는 range 의 시작부분에 해당 가중치를 적립.
// 요건보다 1개의 인덱스가 큰곳에 해당 가중치를 - 해준다.
// 이후 이 계산 배열을 돌면서 누적합을 구해준다.
public class b19951_진짜누적합_면접때틀린거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] firstGround = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            firstGround[i] = Integer.parseInt(st.nextToken());
        }

        // 여기서 맨앞과 맨뒤에 일부러 공간을 둔다. 잉여공간
        // 실제로는 사용하지 않지만 인덱스 에러를 방지하기 위한 공간이다.
        int[] calGround = new int[N+2];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());   // 시작 idx
            int end = Integer.parseInt(st.nextToken());     // 끝 idx
            int cost = Integer.parseInt(st.nextToken());    // 이동량

            // 해당 명령의 시작 지점에 + 해준다.
            // 어차피 이후에 배열을 앞에서 부터 돌면서 누적합 해줄거라서
            // 이후 인덱스는 동일한 + 값을 갖는다.
            calGround[start] += cost;
            // 명령이 끝난 이후부터는 누적합이 계산되면 안된다.
            // 그래서 그 이후 인덱스부터 -값을 적용하여 + 계산을 무마 시킨다.
            calGround[end+1] -= cost;
        }

        // 누적합 계산
        for(int i=1; i<=N; i++){
            // 여기서 차례로 진행하면서 누적합을 계산해준다.
            calGround[i] += calGround[i-1];
        }

        for(int i=1; i<=N; i++){
            System.out.print((firstGround[i] + calGround[i])+" ");
        }
    }
}

