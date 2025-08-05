package 그래프;

import java.util.*;
import java.io.*;

public class b15591_그래프와_이상한가중치
{
    private static int N , Q;
    private static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        while(Q -->0){
            st = new StringTokenizer(br.readLine());
            int maxCost = Integer.parseInt(st.nextToken());
            int nowNum = Integer.parseInt(st.nextToken());

            System.out.println(countPossible(maxCost, nowNum));
        }
    }

    private static int countPossible(int maxCost, int nowNum){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(nowNum, maxCost+1));
        boolean[] visited = new boolean[N+1];
        visited[nowNum] = true;

        int answer = 0;

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();

            if(now.cost >= maxCost) answer++;

            for(Node next : graph.get(now.node)){
                if(!visited[next.node]) {
                    queue.add(new Node(next.node, Math.min(now.cost, next.cost)));
                    visited[next.node] = true;
                }
            }
        }

        return answer-1;
    }

    private static class Node{
        int node, cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }
}

