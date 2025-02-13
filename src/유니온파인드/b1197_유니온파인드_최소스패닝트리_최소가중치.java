package 유니온파인드;

import java.util.*;
import java.io.*;

public class b1197_유니온파인드_최소스패닝트리_최소가중치 {
    public static int N;            //  정점의 개수
    public static int lineN;        //  간선의 개수
    public static int[] parent;     //  루트의 위치
    //  간선과 가중치. a, b, 가중치
    public static List<List<Integer>> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        lineN = Integer.parseInt(st.nextToken());
        parent = new int[N]; // parent 배열 초기화

        for(int i = 0; i < lineN; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            edges.add(Arrays.asList(a, b, weight));  // ArrayList 생성 방식 수정
        }

        for(int i = 0; i < N; i++) {
            // 초기에는 자기자신이 루트
            parent[i] = i;
        }

        // Collections.sort() 사용
        Collections.sort(edges, (a, b) -> Integer.compare(a.get(2), b.get(2)));

        int minWeight = 0;
        int cnt = 0;

        for(List<Integer> line : edges) {
            int nowA = line.get(0);
            int nowB = line.get(1);
            int nowWeight = line.get(2);
            // 둘의 부모가 다르다는 것 자체가 싸이클을 형성하지 않는 것임. 부모가 같으면 이미 같은 집단이니까.
            if(findParent(nowA) != findParent(nowB)) {
                unionParent(nowA, nowB);
                minWeight += nowWeight;
                cnt++;
                if(cnt == N - 1) break;
            }
        }
        System.out.println(minWeight);
    }

    public static int findParent(int x) {
        if(x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    public static void unionParent(int a, int b) {
        int rootA = findParent(a);
        int rootB = findParent(b);

        if(rootA != rootB) {
            // 수가 작은걸로 통일
            if(rootA < rootB) {
                parent[rootB] = rootA;
            } else {
                parent[rootA] = rootB;
            }
        }
    }
}

