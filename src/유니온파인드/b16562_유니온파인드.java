package 유니온파인드;

import java.util.*;
import java.io.*;

//  보니까 일단, 같은 노선으로 연결된걸 모두 한번에 방문처리하는 경우, (이 문제처럼)
//  유니온파인드를 사용한다. 부모를 찾아간 다음, 그 부모안에 어떤것이든 방문하면 퉁 치는것.
public class b16562_유니온파인드 {
    private static int[] parent;  // 부모(대표자) 배열
    private static int[] cost;    // 각 학생의 친구비
    private static int N, M, money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());

        // 초기화
        parent = new int[N + 1];  // 1-based index 사용
        cost = new int[N + 1];

        // 부모를 자기 자신으로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 친구비 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // 친구 관계 입력 (Union)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);  // v와 w를 같은 그룹으로 합침
        }

        // 각 그룹별 최소 비용 계산
        Map<Integer, Integer> minCostMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int root = find(i); // 해당 노드의 대표자를 찾음
            minCostMap.put(root, Math.min(minCostMap.getOrDefault(root, Integer.MAX_VALUE), cost[i]));
        }

        // 총 비용 계산
        int totalCost = 0;
        for (int minCost : minCostMap.values()) {
            totalCost += minCost;
        }

        // 결과 출력
        if (totalCost <= money) {
            System.out.println(totalCost);
        } else {
            System.out.println("Oh no");
        }
    }

    // 유니온 파인드: Find 연산 (경로 압축)
    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축 적용
    }

    // 유니온 파인드: Union 연산
    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            // 더 작은 번호를 root로 유지 (통일성 유지)
            if (rootA < rootB) {
                parent[rootB] = rootA;
            } else {
                parent[rootA] = rootB;
            }
        }
    }
}

