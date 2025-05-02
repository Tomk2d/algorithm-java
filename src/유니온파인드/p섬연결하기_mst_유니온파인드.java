package 유니온파인드;

import java.util.*;

class p섬연결하기_mst_유니온파인드 {
    private List<Node> graph = new ArrayList<>();
    private int[] parents;
    public int solution(int N, int[][] costs) {
        int answer = 0;
        parents = new int[N];

        // 부모 초기화
        for(int i=0; i<N; i++){
            parents[i] = i;
        }

        for(int[] arr : costs){
            int a = arr[0];
            int b = arr[1];
            int cost = arr[2];

            graph.add(new Node(a, b, cost));
        }

        Collections.sort(graph, Comparator.comparingInt(a -> a.cost));

        int cnt = 0;
        for(Node line : graph){
            // 부모가 다를 경우만 연결.
            if(find(line.a) != find(line.b)){
                union(line.a, line.b);
                answer += line.cost;
                cnt ++;
                if(cnt == N-1){
                    break;
                }
            }
        }

        return answer;
    }

    private int find(int x){
        if(parents[x] != x){
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    private void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY){
            // 더 작은 수에 편입 시킴. 부모에 더 작은 수를 둔다
            if(rootX < rootY) parents[rootY] = rootX;
            else parents[rootX] = rootY;
        }
    }

    private class Node{
        int a;
        int b;
        int cost;

        Node(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
}
