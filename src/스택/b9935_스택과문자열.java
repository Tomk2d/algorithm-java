package 스택;

import java.io.*;

// 이 문제의 핵심은 스택이다.
// 스택으로 하나씩 쌓아가면서 폭발문자열시, 부분 문자열을 제거한다
// 파이썬으로 하면 슬라이싱으로 쉽게 풀 수 있을거 같지만
// 자바라서 StringBuilder 의 사용법을 숙지해야 한다.

public class b9935_스택과문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder(); // 결과를 저장할 가변 문자열
        int bombLen = bomb.length();

        for (char c : str.toCharArray()) {
            sb.append(c);

            // 폭발 문자열이 만들어지면 삭제
            // 문자열 길이가 조건 이상이고,
            // 시작인덱스 (폭발 문자열 시작 인덱스 = 전체 길이 - 조건 길이) 부터 끝까지 가 폭발과 같은 문자열이면 제거.
            // 끝이 어차피 폭발 문자열 끝이니까
            if (sb.length() >= bombLen && sb.substring(sb.length() - bombLen).equals(bomb)) {
                sb.delete(sb.length() - bombLen, sb.length()); // ex) 4 ~ 8 의 인덱스 제거
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}

