package 시뮬레이션;

import java.util.*;
import java.io.*;

class b14891_톱니바퀴 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[4][12];

        // 톱니바퀴 입력 처리
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        // 회전 명령 입력 처리
        int N = Integer.parseInt(br.readLine());
        int[][] turn_array = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            turn_array[i][0] = Integer.parseInt(input[0]);
            turn_array[i][1] = Integer.parseInt(input[1]);
        }

        int result = simulation(board, turn_array, N);
        System.out.println(result);
    }

    public static int[] turn_simulation(int[] wheel, int direction) {
        int[] newWheel = new int[8];
        if (direction == 1) { // 시계 방향
            for (int i = 0; i < 7; i++) {
                newWheel[i+1] = wheel[i];
            }
            newWheel[0] = wheel[7];
        } else { // 반시계 방향
            for(int i =7; i > 0; i--) {
                newWheel[i-1] = wheel[i];
            }
            newWheel[7] = wheel[0];
        }
        return newWheel;
    }

    public static int simulation(int[][] board, int[][] turn_array, int N) {
        for (int i = 0; i < N; i++) {
            int num = turn_array[i][0] - 1;
            int direction = turn_array[i][1];

            // 회전 결과 동기화
            boolean[] visited = new boolean[4];
            rotate(board, num, direction, visited);
        }

        // 점수 계산
        int point = 0;
        for (int i = 0; i < 4; i++) {
            if (board[i][0] == 1) {
                point += (1 << i);
            }
        }
        return point;
    }

    public static void rotate(int[][] board, int num, int direction, boolean[] visited) {
        visited[num] = true;

        // 왼쪽 톱니 검사
        if (num > 0 && !visited[num - 1] && board[num][6] != board[num - 1][2]) {
            rotate(board, num - 1, -direction, visited);
        }

        // 오른쪽 톱니 검사
        if (num < 3 && !visited[num + 1] && board[num][2] != board[num + 1][6]) {
            rotate(board, num + 1, -direction, visited);
        }

        // 현재 톱니 회전
        board[num] = turn_simulation(board[num], direction);
    }
}
