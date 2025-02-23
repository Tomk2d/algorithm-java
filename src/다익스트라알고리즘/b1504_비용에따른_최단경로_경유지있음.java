package 다익스트라알고리즘;

import java.util.*;
import java.io.*;

// 이 문제는 경유지를 들러서, 도착지까지의 가중치를 구하는 것 이다.
// 해당 문제는 복잡하게 생각하기 보다, DP 개념의 방식을 적용하는게 좋다
// 출발지 -> 경유지1, 경유지1 -> 경유지2, 경유지2 -> 도착지
// 위처럼 문제를 분할해서 계산하고, 가능한 경우의 수중 최소값을 구한다.

public class b1504_비용에따른_최단경로_경유지있음 {
    private static List<List<Node>> graph = new ArrayList<>();
    private static int nodeN, lineN, goal1, goal2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeN = Integer.parseInt(st.nextToken());
        lineN = Integer.parseInt(st.nextToken());

        for(int i=0; i<= nodeN; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        st = new StringTokenizer(br.readLine());
        goal1 = Integer.parseInt(st.nextToken());
        goal2 = Integer.parseInt(st.nextToken());

        // 각 노드를 출발지로 해서, 다른 모든 노드에 도착하는 최소비용 구함.(다익스트라)
        int[] startCosts = dijkstra(1);
        int[] goal1Costs = dijkstra(goal1);
        int[] goal2Costs = dijkstra(goal2);


        // 가능한 전체 경로 2가지.
        // 출발지 -> 1 -> 2 -> 도착지
        long path1Cost = (long) startCosts[goal1] + goal1Costs[goal2] + goal2Costs[nodeN];

        // 출발지 -> 2 -> 1 -> 도착지
        long path2Cost = (long) startCosts[goal2] + goal2Costs[goal1] + goal1Costs[nodeN];

        long answer = Math.min(path1Cost, path2Cost);
        System.out.println(answer >= Integer.MAX_VALUE ? -1 : answer);

    }

    private static int[] dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        queue.add(new Node(start, 0));

        int[] minArray = new int[nodeN + 1];
        Arrays.fill(minArray, Integer.MAX_VALUE);
        minArray[start] = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            // 이미 최소 코스트 이상일때, 건너뜀
            if(now.cost > minArray[now.node]){
                continue;
            }

            for(Node next : graph.get(now.node)){
                // 지금 노드의 최소 금액과 다음 금액을 더함
                // 즉, 다음 노드까지 드는 비용
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

