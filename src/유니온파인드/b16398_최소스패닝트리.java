package 유니온파인드;

import java.io.*;
import java.util.*;

// 전형적인 최소 스패닝 트리 문제이다.
// 이를 분별할때는, 모든 노드가 연결되어야 한다 -> 이 조건이면 된다.
// 이 경우에는 입력값이 2차원 배열로 주어져서, 중복값과 자기자신 간선을 제외할 필요가 있다.

public class b16398_최소스패닝트리 {
    private static List<Node> nodeList = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int cost = Integer.parseInt(st.nextToken());
                // 중복 및 자기자신 건너뜀
                if(i<=j) continue;

                nodeList.add(new Node(i, j, cost));
            }
        }

        parent = new int[N];

        // 일단 자기 자신이 루트
        for(int i=0; i<N; i++){
            parent[i] = i;
        }

        Collections.sort(nodeList, (a, b)-> Integer.compare(a.cost, b.cost));

        int cnt = 0;
        long sum = 0;
        for(Node now : nodeList){
            // 서로 다른 그룹일때. 서로 뿌리가 다를때.
            if(find(now.a) != find(now.b)){
                union(now.a, now.b);
                sum += (long) now.cost;
                cnt ++;
                if(cnt == N-1){
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    private static int find(int x){
        if(x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB){
            parent[rootB] = rootA;
        }
    }

    private static class Node{
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

