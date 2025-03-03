package DP;

import java.util.*;
import java.io.*;

public class b1446_배열에서_최소비용 {
    private static int N;
    private static int goal;
    private static List<Route> list = new ArrayList<>();
    private static int[] minCost;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(end-start <= cost || end > goal){
                continue;
            }

            list.add(new Route(start, end, cost));
        }

        minCost = new int[goal+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[0] = 0;

        for(int i=0; i<=goal; i++){

            if(i!=0){
                minCost[i] = Math.min(minCost[i], minCost[i-1] + 1);
            }

            for(Route now : list){
                if(now.start == i){
                    minCost[now.end] = Math.min(minCost[now.end], minCost[i]+now.cost);
                }
            }
        }

        System.out.println(minCost[goal]);
    }

    private static class Route{
        int start;
        int end;
        int cost;

        Route(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}

