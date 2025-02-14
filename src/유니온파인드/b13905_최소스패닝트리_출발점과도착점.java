package 유니온파인드;

import java.util.*;
import java.io.*;

// 이 문제의 핵심은
// 1. 최소 가중치가 아닌 최대 가중치를 기준으로 정렬한다.
// 2. 시작과 종료지점을 고려하기 보단, 가중치가 큰 정점들의 연결 먼저 고려한다.
// 3. 이후 시작점과 종료점이 연결되면, 그때 값을 구한다.
public class b13905_최소스패닝트리_출발점과도착점 {
    private static int N, M;
    private static int start, end;
    private static List<int[]> lines = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 정점의 수
        M = Integer.parseInt(st.nextToken());   // 연결 라인 수

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        // 다리 정보 입력 받기
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            lines.add(new int[]{a, b, weight});
        }

        parent = new int[N];
        for(int i = 0; i < N; i++) {
            parent[i] = i;  // 각 집을 자기 자신으로 초기화
        }

        // 무게 제한이 큰 순서대로 정렬
        Collections.sort(lines, (a, b) -> Integer.compare(b[2], a[2]));

        int maxWeight = 0;

        // 유니온 파인드 적용
        for(int[] line : lines) {
            int a = line[0];
            int b = line[1];
            int weight = line[2];

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
            }

            if (findParent(start) == findParent(end)) {
                maxWeight = weight;
                break;
            }
        }

        System.out.println(maxWeight);
    }

    // find 연산
    private static int findParent(int x) {
        if (x != parent[x]) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    // union 연산
    private static void unionParent(int a, int b) {
        int rootA = findParent(a);
        int rootB = findParent(b);

        if (rootA != rootB) {
            if (rootA < rootB) {
                parent[rootB] = rootA;
            } else {
                parent[rootA] = rootB;
            }
        }
    }
}

