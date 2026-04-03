package 다익스트라알고리즘;

import java.util.*;
import java.io.*;

public class b1486_다익스트라_시뮬레이션_그래프리버스 {
    static int N, M, T, D;
    static int[][] height;
    static List<List<Node>> graph = new ArrayList<>();
    static List<List<Node>> reverseGraph = new ArrayList<>();

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        height = new int[N][M];

        for (int i = 0; i < N * M; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // 높이 변환
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if ('A' <= c && c <= 'Z') {
                    height[i][j] = c - 'A';
                } else {
                    height[i][j] = c - 'a' + 26;
                }
            }
        }

        // 그래프 구성
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {

                int nowIdx = getIdx(x, y);
                int nowH = height[x][y];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                    int nextIdx = getIdx(nx, ny);
                    int nextH = height[nx][ny];

                    int diff = Math.abs(nowH - nextH);
                    if (diff > T) continue;

                    int cost;
                    if (nextH <= nowH) cost = 1;
                    else cost = diff * diff;

                    // 정방향
                    graph.get(nowIdx).add(new Node(nextIdx, cost));
                    // 역방향 (그대로 뒤집기!)
                    reverseGraph.get(nextIdx).add(new Node(nowIdx, cost));
                }
            }
        }

        int[] go = dijkstra(0, graph);          // 0 → i
        int[] back = dijkstra(0, reverseGraph); // i → 0

        int answer = 0;

        for (int i = 0; i < N * M; i++) {
            if (go[i] == Integer.MAX_VALUE || back[i] == Integer.MAX_VALUE) continue;

            int total = go[i] + back[i];

            if (total <= D) {
                int x = i / M;
                int y = i % M;
                answer = Math.max(answer, height[x][y]);
            }
        }

        System.out.println(answer);
    }

    static int[] dijkstra(int start, List<List<Node>> g) {
        int[] dist = new int[N * M];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > dist[now.node]) continue;

            for (Node next : g.get(now.node)) {
                int nextCost = now.cost + next.cost;

                if (nextCost < dist[next.node]) {
                    dist[next.node] = nextCost;
                    pq.add(new Node(next.node, nextCost));
                }
            }
        }

        return dist;
    }

    static int getIdx(int x, int y) {
        return x * M + y;
    }

    static class Node {
        int node, cost;

        Node(int n, int c) {
            node = n;
            cost = c;
        }
    }
}
