package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b16928_시뮬레이션 {

    static int[][] labbers;
    static int[][] snakes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int labber_n = Integer.parseInt(input[0]);
        int snake_n = Integer.parseInt(input[1]);

        labbers = new int[labber_n][2];
        for (int i = 0; i < labber_n; i++) {
            input = br.readLine().split(" ");
            labbers[i][0] = Integer.parseInt(input[0]);
            labbers[i][1] = Integer.parseInt(input[1]);
        }

        snakes = new int[snake_n][2];
        for (int i = 0; i < snake_n; i++) {
            input = br.readLine().split(" ");
            snakes[i][0] = Integer.parseInt(input[0]);
            snakes[i][1] = Integer.parseInt(input[1]);
        }

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(1, 0));
        boolean[] visited = new boolean[101];
        visited[1] = true;

        while (!queue.isEmpty()) {
            Point point = queue.pollFirst();

            int num = point.num;
            int turn = point.turn;
            for (int i = 1; i <= 6; i++) {
                int next_num = num + i;

                if (next_num > 100) continue;
                if (next_num == 100) {
                    System.out.println(turn +1);
                    return;
                }

                next_num = check_labber(next_num);
                next_num = check_snake(next_num);
                if(!visited[next_num]){
                    queue.add(new Point(next_num, turn+1));
                    visited[next_num] = true;
                }
            }
        }
    }
    public static class Point{
        int num;
        int turn;

        Point(int num, int turn){
            this.num = num;
            this.turn = turn;
        }
    }

    public static int check_snake(int num) {
        for (int i = 0; i < snakes.length; i++) {
            if (snakes[i][0] == num) {
                return snakes[i][1];
            }
        }
        return num;
    }

    public static int check_labber(int num) {
        for (int i = 0; i < labbers.length; i++) {
            if (labbers[i][0] == num) {
                return labbers[i][1];
            }
        }
        return num;
    }
}
