package 백트래킹;

import java.util.*;

class p전력망둘로나누기_백트래킹으로품_유니온파인드가_나을듯 {
    private List<List<Integer>> graph = new ArrayList<>();
    private int answer = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            // 실제 간선 끊기
            graph.get(a).remove(Integer.valueOf(b));
            graph.get(b).remove(Integer.valueOf(a));

            boolean[] visited = new boolean[n + 1];
            int num1 = dfs(a, visited);
            int num2 = n - num1; // 전체 - num1

            answer = Math.min(Math.abs(num1 - num2), answer);

            // 간선 복구
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return answer;
    }

    private int dfs(int node, boolean[] visited) {
        visited[node] = true;
        int count = 1;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                count += dfs(next, visited);
            }
        }
        return count;
    }
}

