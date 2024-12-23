package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b20055_simulation {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input_NK = br.readLine().split(" ");
        int N = Integer.parseInt(input_NK[0]);
        int K = Integer.parseInt(input_NK[1]);

        String[] input_hp = br.readLine().split(" ");
        int[] belt_hp = new int[N * 2];
        for (int i = 0; i < N * 2; i++) {
            belt_hp[i] = Integer.parseInt(input_hp[i]);
        }
        boolean[] belt_robot = new boolean[N * 2];

        int result = simulation(belt_hp, belt_robot, N, K);
        System.out.println(result);
    }

    public static int countZero(int[] belt_hp) {
        int count = 0;
        for (int i = 0; i < belt_hp.length; i++) {
            if (belt_hp[i] == 0) {
                count++;
            }
        }
        return count;
    }

    public static int simulation(int[] belt_hp, boolean[] belt_robot, int N, int K) {
        int cnt = 0;

        while (true) {
            cnt++;

            // 마지막 값을 미리 저장
            int last_hp = belt_hp[N * 2 - 1];
            boolean last_robot = belt_robot[N * 2 - 1];

            // 1. 벨트를 시계방향으로 회전.
            for (int i = N * 2 - 1; i > 0; i--) {
                belt_hp[i] = belt_hp[i - 1];
                belt_robot[i] = belt_robot[i - 1];
            }
            belt_hp[0] = last_hp;
            belt_robot[0] = last_robot;

            // 즉시 내리기
            belt_robot[N - 1] = false;

            // 2. 로봇 이동
            for (int i = N * 2 - 2; i >= 0; i--) {
                if (belt_robot[i] && !belt_robot[i + 1] && belt_hp[i + 1] > 0) {
                    belt_robot[i] = false;
                    belt_robot[i + 1] = true;
                    belt_hp[i + 1]--;
                }
            }
            // 즉시 내리기
            belt_robot[N - 1] = false;

            // 3. 로봇 올리기
            if (!belt_robot[0] && belt_hp[0] > 0) {
                belt_robot[0] = true;
                belt_hp[0]--;
            }

            // 종료 조건
            if (countZero(belt_hp) >= K) {
                break;
            }
        }

        return cnt;
    }
}
