package 그래프;

import java.util.*;
import java.io.*;

public class b1325_그래프탐색 {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 컴퓨터의 수
        int M = Integer.parseInt(st.nextToken());  // 신뢰 관계의 수

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(B).add(A);  // A가 B를 신뢰한다는 의미이므로 B를 해킹하면 A도 해킹 가능
            inDegree[A]++;
        }

        result = new int[N + 1];
        int maxCount = 0;

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {  // 최상단 부모 노드만 탐색
                visited = new boolean[N + 1];
                int count = dfs(i);
                result[i] = count;
                maxCount = Math.max(maxCount, count);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (result[i] == maxCount) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }

    static int dfs(int node) {
        visited[node] = true;
        int count = 1;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        return count;
    }
}