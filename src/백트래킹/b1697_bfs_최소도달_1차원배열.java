package 백트래킹;

import java.util.*;
import java.io.*;


public class b1697_bfs_최소도달_1차원배열
{
    private static boolean[] visited = new boolean[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(start, 0));

        while(!queue.isEmpty()){
            Point now = queue.pollFirst();

            if(now.num == target){
                System.out.println(now.cnt);
                return;
            }

            int next = now.num + 1;
            if(check(next)){
                visited[next] = true;
                queue.add(new Point(next, now.cnt+1));
            }

            next = now.num-1;
            if(check(next)){
                visited[next] = true;
                queue.add(new Point(next, now.cnt+1));
            }

            next = now.num *2;
            if(check(next)){
                visited[next] = true;
                queue.add(new Point(next, now.cnt+1));
            }
        }


    }

    private static boolean check(int next){
        return next >= 0 && next < 1000000 && !visited[next];
    }


    private static class Point{
        int num;
        int cnt;

        Point(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }
}

