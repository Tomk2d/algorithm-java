package 라인스위핑;

import java.util.*;
import java.io.*;

public class b2170_겹치는선분 {
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
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());

            lines[i] = new Line(a, b);
        }

        Arrays.sort(lines, (a, b)->{
            if(a.start == b.start) return Long.compare(a.end, b.end);
            else return Long.compare(a.start, b.start);
        });

        long preL = Long.MIN_VALUE;
        long preR = Long.MIN_VALUE;
        boolean isOn = false;
        long answer = 0;

        for(Line line : lines){
            if(!isOn){
                preL = line.start;
                preR = line.end;
                isOn = true;
            }else{
                if(preR >= line.start){
                    preR = Math.max(preR, line.end);
                }else{
                    answer += Math.abs(preR - preL);
                    preL = line.start;
                    preR = line.end;
                }
            }
        }

        answer += Math.abs(preR - preL);

        System.out.println(answer);
    }
}

