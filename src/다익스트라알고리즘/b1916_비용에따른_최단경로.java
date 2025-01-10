package 다익스트라알고리즘;

import java.util.*;
import java.io.*;

public class b1916_비용에따른_최단경로{
    // 다시 풀어보자
    public static int minTime = Integer.MAX_VALUE;
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[][] busArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 도시수
        int M = Integer.parseInt(br.readLine()); // 버스수

        // 그래프 초기화
        for (int i = 0; i <= N; i++) { // 0부터 n 까지
            graph.add(new ArrayList<>());
        }

        busArray = new int[M][3]; // 클래스 멤버 변수로 사용

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int[] value = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            busArray[i] = value;

            // 출발지에 버스 번호 추가
            graph.get(value[0]).add(i);
        }

        String[] input2 = br.readLine().split(" ");

        int start = Integer.parseInt(input2[0]);
        int end = Integer.parseInt(input2[1]);
        boolean[] visited = new boolean[M]; // 버스 방문
        dfs(start, end, 0, visited);

        System.out.println(minTime);
    }

    public static void dfs(int now, int end, int cost, boolean[] visited) {
        if (cost >= minTime) { // 현재 비용이 이미 최소 시간보다 크다면 중단
            return;
        }
        if (now == end) {
            minTime = Math.min(cost, minTime);
            return;
        }
        for (int bus : graph.get(now)) {
            if (!visited[bus]) {
                visited[bus] = true;
                dfs(busArray[bus][1], end, cost + busArray[bus][2], visited);
                visited[bus] = false;
            }
        }
    }

}

