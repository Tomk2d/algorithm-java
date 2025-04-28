package 다익스트라알고리즘;

import java.util.*;

class p배달_전형적인_다익스트라 {
    private int N;
    List<List<Node>> graph = new ArrayList<>();
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        this.N = N;

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] node : road){
            int a = node[0];
            int b = node[1];
            int cost = node[2];

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        int[] result = dikstra(1);

        for(int i=1; i<=N; i++){
            if(result[i] <= K){
                answer++;
            }
        }

        return answer;
    }

    private int[] dikstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        queue.add(new Node(start, 0));
        int[] minArray = new int[N+1];
        Arrays.fill(minArray, Integer.MAX_VALUE);
        minArray[start] = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            if(now.cost > minArray[now.node]){
                continue;
            }

            for(Node next : graph.get(now.node)){
                int nextCost = minArray[now.node] + next.cost;

                if(nextCost < minArray[next.node]){
                    minArray[next.node] = nextCost;
                    queue.add(new Node(next.node, nextCost));
                }
            }

        }
        return minArray;
    }

    private class Node{
        int node;
        int cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }
}