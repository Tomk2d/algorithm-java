package 벨만포드;

import java.util.*;
import java.io.*;

// 먼저 한노드에서 가는 다른 노드의 경우라서 다익스트라 알고리즘을 시도했다
// 하지만 이문제는 음수 가중치가 나온다
// 이러한 경우 다익스트라 알고리즘으로 풀 수 없다
// 우선순위큐를 사용하면 가중치가 -로 가는 경우가 있기때문에 음의 사이클이 생긴다
// 이는 해당 경우가 불가함을 가리킨다
// 그래서 벨만포드를 사용해서 이를 거른다.

public class b11657_음수가중치 {
    private static int N;
    private static int lineN;
    private static List<Node> graph = new ArrayList<>();
    private static long[] minArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        lineN = Integer.parseInt(st.nextToken());

        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Node(a, b, c));
        }

        minArray = new long[N+1];
        Arrays.fill(minArray, Integer.MAX_VALUE);
        minArray[1] = 0;

        // 음수 사이클 존재. 성립이 안되는 경우
        if(bellmanFort()){
            System.out.println(-1);
        }else{
            // 일부 노드에 못가는 경우만 추출
            for(int i=2; i<=N; i++){
                System.out.println(minArray[i]==Integer.MAX_VALUE ? -1 : minArray[i]);
            }
        }
    }

    private static boolean bellmanFort(){
        //boolean hasCycle = false;

        // 나머지 도시로 가는 최소비용 구하기
        for(int i=1; i<=N; i++){
            for(Node node : graph){
                long nextCost = (long) minArray[node.depart] + node.cost;

                if(minArray[node.depart] != Integer.MAX_VALUE
                    && minArray[node.arrive] > nextCost){
                    minArray[node.arrive] = nextCost;

                    // 원래는 N-1 만큼 돌면서 그래프를 완성한다.
                    // 하지만 N번째 턴을 추가하여, 그래프가 완성된 후에도 갱신되는지 검사한다.
                    // 이때도 갱신 된다면, 음의 싸이클이 존재하는 것 이다.(돌릴때마다 - 로 향하는)
                    if(i == N){
                        return true;

                    }
                }
            }
        }
        return false;
    }

    private static class Node{
        int depart;     // 출발지
        int arrive;     // 도착지
        int cost;       // 비용

        Node(int depart, int arrive, int cost){
            this.depart = depart;
            this.arrive = arrive;
            this.cost = cost;
        }
    }
}

