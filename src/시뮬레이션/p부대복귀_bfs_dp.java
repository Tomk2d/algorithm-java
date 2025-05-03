package 시뮬레이션;

import java.util.*;

/*
* 처음 일반적으로 bfs 로 풀었지만 시간 초과가 났다.
* 이유는 같은 경로를 지속적으로 방문 할 수 있는데, 이를 매번 계산했기 때문이다
* 그래서 목적지부터 해당 경로까지의 모든 비용을 1번만 계산하고, 저장했다
* 이후 이 저장된 값을 dp 테이블에서 블러오는 과정으로 풀었다
* */

class p부대복귀_bfs_dp {
    private int goal, N;
    private int[] dp;
    private List<List<Integer>> graph = new ArrayList<>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        goal = destination;
        N = n;
        dp = new int[N+1];
        Arrays.fill(dp, -1);
        dp[goal] = 0;

        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] road : roads){
            int a = road[0];
            int b = road[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bfs(goal);
        for(int i=0; i<sources.length; i++){
            answer[i] = dp[sources[i]];
        }

        return answer;
    }

    private void bfs(int start){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));
        boolean[] visited = new boolean[N+1];
        visited[start] = true;

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();

            for(int next : graph.get(now.node)){
                if(!visited[next]){
                    queue.add(new Node(next, now.cnt+1));
                    visited[next] = true;
                    dp[next] = now.cnt+1;
                }
            }
        }
    }

    private class Node{
        int node;
        int cnt;

        Node(int node, int cnt){
            this.node = node;
            this.cnt = cnt;
        }
    }
}
