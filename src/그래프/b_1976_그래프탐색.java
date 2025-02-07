package 그래프;

import java.util.*;
import java.io.*;

public class b_1976_그래프탐색 {
    private static int N;
    private static int M;
    private static Set<Integer>[] cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cities = new HashSet[N];
        for (int i = 0; i < N; i++) {
            cities[i] = new HashSet<>();
        }


        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                if(input[j].equals("1")){
                    cities[i].add(j);
                    cities[j].add(i);
                }
            }
        }
        String[] travelRoot = br.readLine().split(" ");
        for(int i=1; i<travelRoot.length; i++){
            int start = Integer.parseInt(travelRoot[i-1]) - 1;
            int end = Integer.parseInt(travelRoot[i]) - 1;
            boolean[] visited = new boolean[N];
            visited[start] = true;
            if(!find(start, end, visited)){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        return;
    }

    public static boolean find(int start, int end, boolean[] visited){
        boolean result = false;

        if(start == end){
            return true;
        }

        for(int next : cities[start]){
            if(!visited[next]){
                visited[next] = true;
                result = find(next, end, visited);
            }
            if(result){
                return result;
            }
        }
        return result;
    }
}

