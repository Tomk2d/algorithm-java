package 다익스트라알고리즘;

import java.util.*;
import java.io.*;

//  전형적인 다익스트라 알고리즘이다.
//  안풀다가 풀었더니 PriorityQueue 의 정렬 선언을 깜빡해서 오류가 났다.
//  잘 기억하자

public class b5972_전형적인_다익스트라알고리즘 {
    private static int N;
    private static int M;
    private static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        int[] result = dikstra(1);
        System.out.println(result[N]);
    }

    private static int[] dikstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a ->a.cost));
        queue.add(new Node(start, 0));
        int[] minArray = new int[N+1];
        Arrays.fill(minArray, Integer.MAX_VALUE);
        minArray[start] = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            // 지금 가려는 루트의 비용이 다른 길로 왔던 루트보다 비쌀때
            if(now.cost > minArray[now.node]){
                continue;
            }

            for(Node next : graph.get(now.node)){

                int nextCost = minArray[now.node] + next.cost;

                if(nextCost < minArray[next.node]){
                    minArray[next.node] = nextCost;
                    queue.add(new Node(next.node, next.cost));
                }
            }
        }
        return minArray;
    }

    private static class Node{
        int node;
        int cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }
}

