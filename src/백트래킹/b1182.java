package 백트래킹;

import java.io.*;
import java.util.*;

public class b1182 {
    private static int answer = 0;
    private static int N, target;

    public static void dfs(int index, int sum, int[] array) {
        if (index == N) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        dfs(index + 1, sum + array[index], array);

        dfs(index + 1, sum, array);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, array);

        if (target == 0) {
            answer--;
        }

        System.out.println(answer);
    }

}
