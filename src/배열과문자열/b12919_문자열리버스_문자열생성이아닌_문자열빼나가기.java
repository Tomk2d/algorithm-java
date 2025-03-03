package 배열과문자열;

import java.io.*;

// 처음 이 문제를 풀때, 작은 문자열에 더하면서 큰 문자열을 만들었는데 시간초과가 났다.
// 이럴땐 큰 문자열에서 빼는 방식이 더 빠르다고 한다

public class b12919_문자열리버스_문자열생성이아닌_문자열빼나가기 {

    public static String S, T;
    public static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        dfs(T);

        System.out.println(answer);

        br.close();
    }

    public static void dfs(String t) {
        int lenT = t.length();

        if (lenT == S.length()) {
            if (t.equals(S)) {
                answer = 1;
            }
            return;
        }

        if (t.endsWith("A")) {
            dfs(t.substring(0, lenT - 1));
        }

        if (t.startsWith("B")) {
            dfs(new StringBuilder(t.substring(1)).reverse().toString());
        }
    }
}
