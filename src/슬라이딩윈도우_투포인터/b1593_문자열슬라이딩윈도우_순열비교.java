package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

// 이 문제의 핵심은 슬라이딩 윈도우를 쓰는가 ? 이다.
// 먼저 부분 문자열과 순열이라는 함정을 두었지만,
// word 의 순열이 부분문자열과 하나라도 같다면 --> 이 조건은 그냥 부분문자열의 문자개수와 단어의 문자개수가 같으면 된다.
// 순열은 결국 가능한 모든 경우로 문자열을 만들기 때문이다.
// 또한 메모리 초과때문에 문자열 배열이 아닌, charArray(문자 배열)을 사용해야 했다.

public class b1593_문자열슬라이딩윈도우_순열비교 {

    private static Map<Character, Integer> wordSet = new HashMap<>();
    private static Map<Character, Integer> strSet = new HashMap<>();
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int wordLen = Integer.parseInt(st.nextToken());
        int strLen = Integer.parseInt(st.nextToken());

        char[] word = br.readLine().toCharArray();
        char[] str = br.readLine().toCharArray();

        // wordSet에 각 문자 개수 저장
        for (char c : word) {
            wordSet.put(c, wordSet.getOrDefault(c, 0) + 1);
        }

        // 초기 윈도우 세팅
        int start = 0;
        int end = wordLen - 1;
        for (int i = start; i <= end; i++) {
            char nowChar = str[i];
            strSet.put(nowChar, strSet.getOrDefault(nowChar, 0) + 1);
        }

        // 첫 번째 윈도우 검사
        if (check()) {
            cnt++;
        }

        // 슬라이딩 윈도우 진행
        while (end < strLen - 1) {
            char removeChar = str[start];  // 제거할 문자
            char addChar = str[end + 1];   // 새롭게 추가할 문자
            start++;
            end++;

            // 제거할 문자 개수 감소
            if (strSet.get(removeChar) == 1) {
                strSet.remove(removeChar);
            } else {
                strSet.put(removeChar, strSet.get(removeChar) - 1);
            }

            // 추가할 문자 개수 증가
            strSet.put(addChar, strSet.getOrDefault(addChar, 0) + 1);

            // 검사
            if (check()) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean check() {
        for (char nowChar : wordSet.keySet()) {
            if (!strSet.containsKey(nowChar) || !strSet.get(nowChar).equals(wordSet.get(nowChar))) {
                return false;
            }
        }
        return true;
    }
}

