package 구현;

import java.io.*;

// 이 문제는 문제 이해가 좀 어려웠다 (개인적으로 이런 그지 같은 문제 싫어한다)
// 이중 살펴봐야 할 부분은
// 문자열을 세개의 부분문자열로 분할하는 부분이다.
// 2중 for 문을 사용하여 string.substring(0,i), string.substring(i,j) , string.substring(j,N)
// 의 방법을 활용하면 모든 경우의 수(순열) 의 부분집합을 구할 수 있다.

public class b20164_문자열과정수_구현조건_dfs {
    private static int minCnt = Integer.MAX_VALUE;
    private static int maxCnt = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // dfs 함수 호출
        dfs(input, 0);

        // 결과 출력
        System.out.println(minCnt + " " + maxCnt);
    }

    private static void dfs(String str, int cnt) {
        int nowLen = str.length();

        if (nowLen == 1) {
            if (Integer.parseInt(str) % 2 == 1) {
                cnt++;
            }
            maxCnt = Math.max(maxCnt, cnt);
            minCnt = Math.min(minCnt, cnt);
            return;
        }else if (nowLen == 2) {
            int sum = 0;
            for (int i = 0; i < 2; i++) {
                int num = str.charAt(i) - '0';
                sum += num;
                if (num % 2 == 1) {
                    cnt++;
                }
            }
            dfs(Integer.toString(sum), cnt);  // 새로운 수로 재귀 호출
            return;
        }else if (nowLen >= 3) {
            for (int i = 1; i < nowLen; i++) {  // i는 1부터 시작 (첫 번째 분할)
                for (int j = i + 1; j < nowLen; j++) {  // j는 i+1부터 시작 (두 번째 분할)
                    String part1 = str.substring(0, i);
                    String part2 = str.substring(i, j);
                    String part3 = str.substring(j);

                    // 각 부분에서 홀수 개수를 카운트
                    int nowCnt = cnt;
                    nowCnt += countOdds(part1);
                    nowCnt += countOdds(part2);
                    nowCnt += countOdds(part3);

                    int sum = Integer.parseInt(part1) + Integer.parseInt(part2) + Integer.parseInt(part3);
                    dfs(Integer.toString(sum), nowCnt);
                }
            }
        }
    }

    private static int countOdds(String numStr) {
        int count = 0;
        for (int i = 0; i < numStr.length(); i++) {
            if ((numStr.charAt(i) - '0') % 2 == 1) {
                count++;
            }
        }
        return count;
    }
}

