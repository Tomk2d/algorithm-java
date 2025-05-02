package 최소경로;

import java.util.*;
class p가장먼노드 {
    List<List<Integer>> graph = new ArrayList<>();
    public int solution(int n, int[][] edge) {
        int answer = 0;

        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] el : edge){
            int a = el[0];
            int b = el[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return bfs(n);
    }

    private int bfs(int n){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 0));
        boolean[] visited = new boolean[n+1];
        visited[1] = true;

        int maxDistance = 0;
        int result = 0;

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();

            if(now.cnt > maxDistance){
                maxDistance = now.cnt;
                result = 1;
            }else if(now.cnt == maxDistance){
                result ++;
            }

            for(int next: graph.get(now.node)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(new Node(next, now.cnt+1));
                }
            }
        }

        return result;
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
