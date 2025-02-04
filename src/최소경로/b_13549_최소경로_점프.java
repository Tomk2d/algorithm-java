package 최소경로;

import java.util.*;
import java.io.*;

// 처음에는 DP 문제인줄 알고 접근했다.
// 하지만 알고보니 bfs 문제였다. 이유는 2배 점프를 할때 시간계산을 하지 않아서 그런거 같다.
// 왜냐하면 dp 는 모든 경우를 전부 고려해서 최소 시간을 구하지만,
// 이 경우는 2배 점프시에는 시간 계산을 하지 않는다.
// 즉, 먼저 그 자리에 도착한게 가장 빠르다.
// 하지만 최종 목적지의 도착 경우만 제외하고 말이다.

public class b_13549_최소경로_점프 {
    private static int minTime = Integer.MAX_VALUE;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];

        bfs(start, end);
        System.out.println(minTime);

    }

    public static void bfs(int start, int end){
        Deque<Now> queue = new ArrayDeque<>();
        queue.add(new Now(start, 0));

        while(!queue.isEmpty()){
            Now now = queue.pollFirst();
            int idx = now.idx;
            int time = now.time;
            visited[idx] = true;

            if(idx == end){
                minTime = Math.min(minTime, time);
            }
            if(idx*2 <= 100000 && !visited[idx*2]){
                queue.add(new Now(idx*2, time));
                visited[idx*2] = true;
            }
            if(idx-1 >= 0 && !visited[idx-1]){
                queue.add(new Now(idx-1, time+1));
                visited[idx-1] = true;
            }
            if(idx+1 <= 100000 && !visited[idx+1]){
                queue.add(new Now(idx+1, time+1));
                visited[idx+1] = true;
            }
        }
    }

    private static class Now{
        int idx, time;

        Now(int idx, int time){
            this.idx = idx;
            this.time = time;
        }
    }
}

