package 백트래킹;

import java.io.*;
import java.util.*;

public class b1062_여러메서드활용_문자열백트래킹 {
    private static int maxCnt = 0;
    private static int N, K;
    private static String[] words;
    private static List<Character> availableChars = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        // 필수 글자 5개
        Set<Character> essential = new HashSet<>(Arrays.asList('a', 'n', 't', 'i', 'c'));

        // 나오는 문자만 중복없이 저장
        for (char c = 'a'; c <= 'z'; c++) {
            if (!essential.contains(c)) {
                availableChars.add(c);
            }
        }

        boolean[] visited = new boolean[26];

        // 필수 글자 5개 배움
        for (char c : essential) {
            visited[c - 'a'] = true;
        }


        learn(0, 0, visited);
        System.out.println(maxCnt);
    }

    private static void learn(int start, int nowK, boolean[] visited) {
        if (nowK == K - 5) {
            countWord(visited);
            return;
        }

        for (int i = start; i < availableChars.size(); i++) {
            char alpha = availableChars.get(i);

            if (!visited[alpha - 'a']) {
                visited[alpha - 'a'] = true;
                learn(i + 1, nowK + 1, visited);
                visited[alpha - 'a'] = false;
            }
        }
    }

    private static void countWord(boolean[] visited) {
        int nowCnt = 0;

        for (String word : words) {
            boolean canRead = true;
            for (int i = 4; i < word.length() - 4; i++) {
                if (!visited[word.charAt(i) - 'a']) {
                    canRead = false;
                    break;
                }
            }
            if (canRead) {
                nowCnt++;
            }
        }

        maxCnt = Math.max(maxCnt, nowCnt);
    }
}

