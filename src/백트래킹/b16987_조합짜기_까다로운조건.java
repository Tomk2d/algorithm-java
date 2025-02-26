package 백트래킹;

import java.io.*;
import java.util.*;

public class b16987_조합짜기_까다로운조건 {
    private static int maxCnt = 0;
    private static int N;
    private static Egg[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int hp = Integer.parseInt(st.nextToken());
            int attack = Integer.parseInt(st.nextToken());
            board[i] = new Egg(hp, attack);
        }

        boolean[] broken = new boolean[N];
        dfs(0, 0, broken);
        System.out.println(maxCnt);
    }

    private static void dfs(int idx, int cnt, boolean[] broken) {
        if (idx >= N) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        if (broken[idx]) {
            dfs(idx + 1, cnt, broken);
            return;
        }

        boolean isAllBroken = true;

        for (int i = 0; i < N; i++) {
            if (i != idx && !broken[i]) {
                isAllBroken = false;
                Egg pick = board[idx];
                Egg target = board[i];

                int oldPickHp = pick.hp;
                int oldTargetHp = target.hp;

                pick.hp = Math.max(0, pick.hp - target.attack);
                target.hp = Math.max(0, target.hp - pick.attack);

                int nextCnt = cnt;

                if (pick.hp == 0) {
                    broken[idx] = true;
                    nextCnt++;
                }
                if (target.hp == 0) {
                    broken[i] = true;
                    nextCnt++;
                }

                dfs(idx + 1, nextCnt, broken);

                // 복구
                broken[idx] = false;
                broken[i] = false;
                pick.hp = oldPickHp;
                target.hp = oldTargetHp;
            }
        }

        // 깨지지 않은 다른 달걀이 없다면 그냥 다음 달걀로 이동
        if (isAllBroken) {
            dfs(idx + 1, cnt, broken);
        }
    }

    private static class Egg {
        int hp, attack;

        Egg(int hp, int attack) {
            this.hp = hp;
            this.attack = attack;
        }
    }
}

