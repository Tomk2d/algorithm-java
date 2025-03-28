package 백트래킹;

import java.util.*;
import java.io.*;


public class b5014_bfs_최소도착
{
    private static boolean[] visited = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        int up = Integer.parseInt(st.nextToken());
        int down = Integer.parseInt(st.nextToken());

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(start, 0));
        visited[start] = true;

        while(!queue.isEmpty()){
            Point now = queue.pollFirst();

            if(now.stair == goal){
                System.out.println(now.cnt);
                return;
            }

            int next = now.stair + up;
            if(check(next, N)){
                queue.add(new Point(next, now.cnt+1));
                visited[next] = true;
            }

            next = now.stair - down;
            if(check(next, N)){
                queue.add(new Point(next, now.cnt+1));
                visited[next] = true;
            }

        }

        System.out.println("use the stairs");

    }

    private static boolean check(int stair, int N){
        return stair>0 && stair<=N && !visited[stair];
    }

    private static class Point{
        int stair;
        int cnt;

        Point(int stair, int cnt){
            this.stair = stair;
            this.cnt = cnt;
        }
    }
}

