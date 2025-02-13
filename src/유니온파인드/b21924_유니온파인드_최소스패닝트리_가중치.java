package 유니온파인드;

import java.util.*;
import java.io.*;

public class b21924_유니온파인드_최소스패닝트리_가중치 {
    public static int N, M;
    public static int[] parent;
    public static List<int[]> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long totalPrice = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            lines.add(new int[] {a, b, weight});
            totalPrice += weight;
        }

        // Union-Find 초기화
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        // 가중치 기준 오름차순 정렬
        Collections.sort(lines, (a, b) -> Integer.compare(a[2], b[2]));

        long minPrice = 0;
        int cnt = 0;

        // 크루스칼 알고리즘
        for (int[] line : lines) {
            int a = line[0];
            int b = line[1];
            int weight = line[2];

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                minPrice += weight;
                cnt++;

                if (cnt == N - 1) {
                    break;
                }
            }
        }

        // 모든 건물이 연결되었는지 확인
        if (cnt != N - 1) {
            System.out.println(-1);
        } else {
            System.out.println(totalPrice - minPrice > 0 ? totalPrice - minPrice : -1);
        }
    }

    // Find 연산 (경로 압축)
    public static int findParent(int x) {
        if (x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    // Union 연산
    public static void unionParent(int a, int b) {
        int rootA = findParent(a);
        int rootB = findParent(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}

