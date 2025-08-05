package 유니온파인드;

import java.util.*;
import java.io.*;

public class b10021_최소스패닝트리_완전탐색과_최소비용조건
{
    private static int N , minCost;
    private static List<Line> graph = new ArrayList<>();
    private static int[][] nodes;
    private static int[] parents;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        minCost = Integer.parseInt(st.nextToken());

        nodes = new int[N][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[i][0] = a;
            nodes[i][1] = b;
        }

        parents = new int[N];
        for(int i=0; i<N; i++){
            parents[i] = i;
        }


        makeLines();

        Collections.sort(graph, Comparator.comparingInt((a)-> a.cost));

        int sum = 0;
        int cnt = 0;
        for(Line line : graph){
            int a = line.a;
            int b = line.b;
            int cost = line.cost;

            if(find(a) != find(b)){
                union(a, b);
                cnt ++;
                sum += cost;

                if(cnt == N-1) break;
            }
        }

        System.out.println(cnt < N-1 ? -1 : sum);


// 		for(Line line : graph){
// 		    System.out.println(line.a + " , " + line.b + " = " + line.cost);
// 		}
    }

    private static int find(int x){
        if(x != parents[x]){
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    private static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA <= rootB){
            parents[rootB] = rootA;
        }else{
            parents[rootA] = rootB;
        }
    }

    private static void makeLines(){
        for(int idx=0; idx<N; idx++){
            int x = nodes[idx][0];
            int y = nodes[idx][1];

            for(int next=0; next<N; next++){
                if(next == idx) continue;

                int nx = nodes[next][0];
                int ny = nodes[next][1];

                int dx = x-nx;
                int dy = y-ny;

                int cost = dx*dx + dy*dy;
                if(cost >= minCost) graph.add(new Line(idx, next, cost));
            }
        }
    }

    private static class Line{
        int a, b, cost;

        Line(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }


}

