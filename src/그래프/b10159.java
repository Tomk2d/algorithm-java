package 그래프;

import java.util.*;
import java.io.*;

public class b10159
{
    private static int N, lineN;
    private static List<List<Integer>> bigGraph = new ArrayList<>();
    private static List<List<Integer>> smallGraph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        lineN = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++){
            bigGraph.add(new ArrayList<>());
            smallGraph.add(new ArrayList<>());
        }

        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bigGraph.get(a).add(b);
            smallGraph.get(b).add(a);
        }

        for(int i=1; i<=N; i++){
            boolean[] visited = new boolean[N+1];
            visited[i] = true;

            visited = countNotVisited(i, visited, bigGraph);
            visited = countNotVisited(i, visited, smallGraph);

            int cnt = 0;
            for(int idx=1; idx<=N; idx++){
                if(!visited[idx]) cnt ++;
            }

            System.out.println(cnt);
        }
    }

    private static boolean[] countNotVisited(int start, boolean[] visited, List<List<Integer>> graph){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int now = queue.pollFirst();

            for(int next : graph.get(now)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return visited;
    }
}

