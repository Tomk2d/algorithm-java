package 그래프;

import java.util.*;
import java.io.*;

public class b18352 {
    private static ArrayList<Integer> list = new ArrayList<>();

    public void bfs(ArrayList<ArrayList<Integer>> roads, int start, int distance){
        Queue<Integer> queue = new LinkedList<>();


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시개수
        int M = Integer.parseInt(st.nextToken());   // 도로개수
        int distance = Integer.parseInt(st.nextToken());   // 목표거리
        int start = Integer.parseInt(st.nextToken()); // 출발도시

        // 도시와 연결된 도로들을 저장할 ArrayList 생성
        ArrayList<ArrayList<Integer>> roads = new ArrayList<>();
        // 도시 개수만큼 ArrayList를 초기화
        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<>());
        }

        // 도로 정보 입력 (한 번만 입력 받음)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());  // 새로 한 줄 읽기
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());

            // city1에서 city2로 가는 도로 정보 추가
            roads.get(city1).add(city2);    // roads[city 1의 인덱스]에 city2 추가. -> 단방향으로 city1에서 city2로 가는 길
        }


    }
}
