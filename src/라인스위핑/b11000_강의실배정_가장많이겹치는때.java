package 라인스위핑;

import java.util.*;
import java.io.*;

public class b11000_강의실배정_가장많이겹치는때
{
    private static class Line{
        long start, end;

        Line(long start, long end){
            if(start<=end) {this.start = start; this.end = end;}
            else {this.start = end; this.end = start;}
        }
    }
    private static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Line[] lines = new Line[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lines[i] = new Line(start, end);
        }

        Arrays.sort(lines, (a, b) -> {
            if(a.start == b.start) return Long.compare(a.end, b.end);
            else return Long.compare(a.start, b.start);
        });

        int maxN = 0;
        // 이미 배정받은 강의실. endTime 만 저장
        PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a));
        queue.add(lines[0].end);
        maxN = Math.max(maxN, queue.size());

        for(int i=1; i<N; i++){
            Line now = lines[i];

            // 끝난 강의 빼기
            while(!queue.isEmpty()){
                if(queue.peek() <= now.start){
                    queue.poll();
                }else{
                    break;
                }
            }

            queue.add(now.end);
            maxN = Math.max(maxN, queue.size());
        }
        System.out.println(maxN);
    }
}
