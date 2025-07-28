package 슬라이딩윈도우_투포인터;

import java.io.*;
import java.util.*;

/*
*   이 문제의 핵심은 일반적인 슬라이딩 윈도우 풀이가 아니란는 것과
*   결국 max 와 min 둘 다 시작과 끝이 같아야 한다는거,,,
* */
public class b20437_슬라이딩윈도우_부분문자열_여러조건 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int i=0; i<T; i++) {
            String W = br.readLine(); // 문자열 입력
            int K = Integer.parseInt(br.readLine()); // K 값 입력

            sb.append(solution(W, K)).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static String solution(String W, int K) {

        // ex) a : [1, 4, 7, 11]
        Map <Character, List<Integer>> charList = new HashMap<>();

        // 1. 문자열의 인덱스를 미리 저장.
        for(int i=0; i < W.length(); i++){
            char a = W.charAt(i);
            if(charList.containsKey(a)){
                charList.get(a).add(i);
            }else{
                charList.put(a, new ArrayList<>(List.of(i)));
            }
        }

        int minLen = Integer.MAX_VALUE;
        int maxLen = -1;

        // 2. 요구 개수(K) 만족시키는 최소 크기와 최대크기
        for(List<Integer> list : charList.values()){
            if(list.size() < K) continue;   //  만족 못하는 알파벳 건너뛰기

            for(int i=0; i <= list.size()-K; i++){
                int start = list.get(i);
                int end = list.get(i + K - 1);
                int length = end - start + 1;

                minLen = Math.min(minLen, length);
                // for 문 안에는 어차피 같은 애들끼리만 검사함.
                maxLen = Math.max(maxLen, length);
            }
        }

        return (minLen == Integer.MAX_VALUE) ? "-1" : (minLen + " " + maxLen);
    }
}
