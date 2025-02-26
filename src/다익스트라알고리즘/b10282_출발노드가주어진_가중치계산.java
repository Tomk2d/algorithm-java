package 다익스트라알고리즘;

import java.io.*;
import java.util.*;

public class b10282_출발노드가주어진_가중치계산 {
    private static List<List<Node>> graph;
    private static int nodeN;
    private static int lineN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        while(N --> 0){
            st = new StringTokenizer(br.readLine());

            nodeN = Integer.parseInt(st.nextToken());
            lineN = Integer.parseInt(st.nextToken());
            int firstNode = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int i=0; i<=nodeN; i++){
                graph.add(new ArrayList<>());
            }

            // 입력 받기
            for(int i=0; i<lineN; i++){
                st = new StringTokenizer(br.readLine());

                int end = Integer.parseInt(st.nextToken());
                int start = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.get(start).add(new Node(end, cost));
            }

            int[] result = dikstra(firstNode);

            int cnt = 0;
            int max = 0;
            for(int num : result){
                if(num != Integer.MAX_VALUE){
                    cnt ++;
                    max = Math.max(max, num);
                }
            }
            System.out.println(cnt + " " + max);
        }
    }

    private static int[] dikstra(int firstNode){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        queue.add(new Node(firstNode, 0));

        int[] minCost = new int[nodeN+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[firstNode] = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            // 이미 크면 넘김
            if(now.cost > minCost[now.node]) continue;

            for(Node next : graph.get(now.node)){
                int nextCost = minCost[now.node]+next.cost;

                if(nextCost < minCost[next.node]){
                    queue.add(new Node(next.node, next.cost));
                    minCost[next.node] = nextCost;
                }
            }
        }
        return minCost;
    }

    private static class Node{
        int node;   // 갈곳
        int cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }
}

