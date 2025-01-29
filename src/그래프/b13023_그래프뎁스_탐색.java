package 그래프;

import java.util.*;
import java.io.*;

public class b13023_그래프뎁스_탐색
{
    private static int answer = 0;
    private static boolean possible = false;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeN = Integer.parseInt(st.nextToken());
        int lineN = Integer.parseInt(st.nextToken());

        for(int i=0; i<=nodeN; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph.get(n).add(m);
            graph.get(m).add(n);
        }

        for(int i=0; i < nodeN; i++){
            visited = new boolean[nodeN];
            dfs(i, 1);

            if(possible) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
        return;
    }

    public static void dfs(int node, int depth){
        if(depth == 5){
            possible = true;
            return;
        }

        visited[node] = true;

        for(int nextNode : graph.get(node)){
            if(!visited[nextNode]){
                dfs(nextNode, depth+1);
                if(possible) return;
            }
        }

        visited[node] = false;
    }
}

