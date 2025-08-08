package 다익스트라알고리즘;

import java.util.*;
import java.io.*;

public class b1238_단방향노드_왕복비용
{
    private static int townN, lineN, goal;
    private static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        townN = Integer.parseInt(st.nextToken());
        lineN = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        for(int i=0; i<=townN; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
        }

        int[] startToGoal = dikstra(goal);

        int answer = 0;
        for(int i=1; i<=townN; i++){
            int[] startToHome = dikstra(i);
            int time = startToHome[goal] + startToGoal[i];
            answer = Math.max(answer, time);
        }

        System.out.println(answer);
    }

    private static int[] dikstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.cost));
        queue.add(new Node(start, 0));

        int[] minArray = new int[townN+1];
        Arrays.fill(minArray, Integer.MAX_VALUE);
        minArray[start] = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            if(now.cost > minArray[now.node]) continue;

            for(Node next : graph.get(now.node)){
                int nextCost = next.cost + minArray[now.node];
                if(nextCost <= minArray[next.node]){
                    minArray[next.node] = nextCost;
                    queue.add(new Node(next.node, next.cost));
                }
            }
        }

        return minArray;
    }

    private static class Node{
        int node, cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }
}

