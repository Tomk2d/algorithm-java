package 다익스트라알고리즘;

import java.util.*;
import java.io.*;

public class b1916_비용에따른_최단경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: 도시 수와 버스 수
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 그래프 초기화
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new int[] { end, cost });
        }

        // 출발점과 도착점 입력
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 최소 비용 출력
        System.out.println(dijkstra(N, graph, start, end));
    }

    public static int dijkstra(int N, List<List<int[]>> graph, int start, int end) {
        // 거리 배열 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 우선순위 큐 (비용 기준 오름차순 정렬)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] { start, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int now = current[0];
            int cost = current[1];

            // 현재 비용이 이미 최단 거리보다 크면 스킵
            if (cost > dist[now]) continue;

            // 인접 노드 탐색
            for (int[] next : graph.get(now)) {
                int nextCity = next[0];
                int nextCost = cost + next[1];

                // 더 짧은 경로 발견 시 갱신
                if (nextCost < dist[nextCity]) {
                    dist[nextCity] = nextCost;
                    pq.add(new int[] { nextCity, nextCost });
                }
            }
        }

        return dist[end]; // 도착점까지의 최단 거리 반환
    }
}



