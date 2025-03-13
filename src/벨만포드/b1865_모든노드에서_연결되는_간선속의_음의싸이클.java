package 벨만포드;

import java.util.*;
import java.io.*;

// 해당 문제는 출발점과 도착점이 정해져 있는것이 아니라,
// 모든 노드를 대상으로 음의 싸이클이 발생하는지 조사하여야 한다.
// 벨만 포드는 N-1 개의 간선만을 가진다고(모든 노드가 연결된, 싸이클이없는, 간선의 개수) 보기때문에
// N-1 만큼 돌면서 최소 비용을 업데이트 시킨다.
// 이 경우, 모든 간선을 계속 갱신하기 때문에 결국 그래프가 연결된다.
// 근데 이게, 마지막 턴 (즉, 이미 그래프가 완성된 상태) 에서도 갱신이 된다면,
// 이것은 음의 싸이클이 존재하는 것 이다. (간선의 총합이 -인 경우)
// 간선의 총합이 - 인경우는 결국, 일어날 수 없는 경우이다. 출발시간이 9시 인데 도착시간이 8시반 일 수 없으니까

public class b1865_모든노드에서_연결되는_간선속의_음의싸이클 {
    private static int N, lineN, holeN;
    private static List<List<Node>> graph;
    private static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            lineN = Integer.parseInt(st.nextToken());
            holeN = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < lineN; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Node(b, cost));
                graph.get(b).add(new Node(a, cost));
            }

            for (int i = 0; i < holeN; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken()) * -1;

                graph.get(a).add(new Node(b, cost));
            }

            if (belmanFord()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb);
    }

    private static boolean belmanFord() {
        long[] minArray = new long[N + 1];
        Arrays.fill(minArray, 0); // 모든 거리 초기화 (가상의 0번 노드에서 시작)

        for (int i = 0; i < N; i++) { // N-1번 반복. 모든 노드를 연결하는 간선의 수
            for (int j = 1; j <= N; j++) {  // 1개의 간선당, 모든 노드를
                for (Node node : graph.get(j)) {
                    if (minArray[node.arrive] > minArray[j] + node.cost) {
                        minArray[node.arrive] = minArray[j] + node.cost;

                        // 마지막 바퀴. 즉, 마지막 간선 타이밍에도 갱신이 일어난다면, 음의 싸이클 이라고 봄.
                        if(i == N-1){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static class Node {
        int arrive;
        int cost;

        Node(int arrive, int cost) {
            this.arrive = arrive;
            this.cost = cost;
        }
    }
}

