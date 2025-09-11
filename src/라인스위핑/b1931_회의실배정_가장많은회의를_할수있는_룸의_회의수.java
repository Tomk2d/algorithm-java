package 라인스위핑;

import java.util.*;
import java.io.*;

public class b1931_회의실배정_가장많은회의를_할수있는_룸의_회의수
{
    private static class Time{
        long start, end;

        Time(long start, long end){
            if(start <= end) {this.start = start; this.end = end;}
            else {this.start = end; this.end = start;}
        }
    }
    private static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Time[] times = new Time[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            times[i] = new Time(a, b);
        }

        // 끝나는게 빠른 기준으로 정렬함
        Arrays.sort(times, (a, b) -> {
            if(a.end == b.end) return Long.compare(a.start, b.start);
            else return Long.compare(a.end, b.end);
        });

        long preEndTime = 0;
        int answer = 0;

        for(Time time : times){
            if(preEndTime <= time.start){
                answer ++;
                preEndTime = time.end;
            }
        }
        System.out.println(answer);
    }
}

