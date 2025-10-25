package 위상정렬;

import java.io.*;
import java.util.*;

public class b1766_위상정렬_기본기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int peopleN = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] firstRule = new int[peopleN+1];
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<=peopleN; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            firstRule[b] ++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=1; i<=peopleN; i++){
            if(firstRule[i] == 0) queue.add(i);
        }

        List<Integer> answer = new ArrayList<>();
        while(!queue.isEmpty()){
            int now = queue.poll();
            answer.add(now);

            for(int next : graph.get(now)){
                firstRule[next] --;
                if(firstRule[next] == 0){
                    queue.add(next);
                }
            }
        }

        for(int people : answer){
            System.out.print(people + " ");
        }

    }
}

