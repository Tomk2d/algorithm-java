package 슬라이딩윈도우;

import java.io.*;
import java.util.*;

public class b20437_슬라이딩윈도우_부분문자열_여러조건 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            String W = br.readLine(); // 문자열 입력
            int K = Integer.parseInt(br.readLine()); // K 값 입력

            sb.append(solution(W, K)).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static String solution(String W, int K) {
        Map<Character, List<Integer>> charIndices = new HashMap<>();

        // 1. 각 문자의 등장 인덱스를 저장
        for (int i = 0; i < W.length(); i++) {
            char c = W.charAt(i);
            charIndices.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }

        int shortestLen = Integer.MAX_VALUE;
        int longestLen = -1;

        // 2. 각 문자별로 K개의 연속된 부분 문자열을 찾음
        for (List<Integer> indices : charIndices.values()) {
            if (indices.size() < K) continue; // K개 미만이면 건너뜀

            // 3. K개의 연속된 부분을 슬라이딩 윈도우 방식으로 탐색
            for (int i = 0; i <= indices.size() - K; i++) {
                int start = indices.get(i);
                int end = indices.get(i + K - 1);
                int length = end - start + 1;

                // 3번 조건 (K개 포함된 가장 짧은 문자열)
                shortestLen = Math.min(shortestLen, length);

                // 4번 조건 (K개 포함되면서, 첫 문자와 마지막 문자가 같은 가장 긴 문자열)
                if (W.charAt(start) == W.charAt(end)) {
                    longestLen = Math.max(longestLen, length);
                }
            }
        }

        return (shortestLen == Integer.MAX_VALUE) ? "-1" : (shortestLen + " " + longestLen);
    }
}

