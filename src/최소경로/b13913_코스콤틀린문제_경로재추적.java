package 최소경로;

import java.util.*;
import java.io.*;

/*
*       코스콤 코테때 맞은줄 알았던 문제인데, 이거 틀렸던거 같다. (3차원 배열로 풀었어야 할거 같음)
*       이번에도 처음에 그때처럼 풀었다.
*       Node 객체를 생성하고, 해당 객체에 List 로 경로를 저장하며 풀었다.
*       하지만 List 를 매번 복사해서 생성하는것은 시간이 많이 걸린다.
*       이때 경로 추척을 쓴다.
*       해당 문제에서는 저장해야할 경로가 1개 이기 때문에
*       방문한 곳의 value 에 이전 idx 를 저장해서 역추척 하면 된다.
*       사실 이렇게 하면 visited 도 쓰지않고, 경로 추적 배열의 -1 로 검사해도 될 것 이다.
* */


public class b13913_코스콤틀린문제_경로재추적 {
    private static int start, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        bfs();
    }

    private static void bfs() {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[200001];
        int[] preIdxArray = new int[200001];

        Arrays.fill(preIdxArray, -1); // 먼저 초기화
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int now = queue.pollFirst();

            if (now == target) {
                int cur = now;
                Stack<Integer> path = new Stack<>();

                while (cur != -1) {
                    path.push(cur);
                    cur = preIdxArray[cur];
                }

                System.out.println(path.size() - 1); // 이동 횟수
                while (!path.isEmpty()) {
                    System.out.print(path.pop() + " ");
                }
                return;
            }

            int[] nexts = {now + 1, now - 1, now * 2};
            for (int next : nexts) {
                if (next >= 0 && next <= 200000 && !visited[next]) {
                    visited[next] = true;
                    preIdxArray[next] = now;
                    queue.add(next);
                }
            }
        }
    }
}

