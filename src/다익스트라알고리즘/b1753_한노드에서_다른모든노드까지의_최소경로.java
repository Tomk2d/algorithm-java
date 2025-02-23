package 다익스트라알고리즘;

import java.util.*;
import java.io.*;

public class b1753_한노드에서_다른모든노드까지의_최소경로 {
    private static int nodeN, lineN, start;
    private static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeN = Integer.parseInt(st.nextToken());
        lineN = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        for(int i=0; i<=nodeN; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
        }

        int[] answer = dikstra(start);

        for(int i=1; i<answer.length; i++){
            System.out.println(answer[i]==Integer.MAX_VALUE ? "INF" : answer[i]);
        }

    }

    private static int[] dikstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        queue.add(new Node(start, 0));

        int[] minArray = new int[nodeN + 1];
        Arrays.fill(minArray, Integer.MAX_VALUE);
        minArray[start] = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            if(now.cost > minArray[now.node]){
                continue;
            }

            for(Node next : graph.get(now.node)){
                // 굉장히 중요!!! now.cost 가 아닌 minArray[now.node]
                // 왜냐하면, now.cost 는 그저 현재 노드의 금액임.
                // 지금까지의 최소 금액과 더해야함.
                int nextCost = minArray[now.node] + next.cost;

                if(nextCost < minArray[next.node]){
                    minArray[next.node] = nextCost;
                    queue.add(new Node(next.node, nextCost));
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
