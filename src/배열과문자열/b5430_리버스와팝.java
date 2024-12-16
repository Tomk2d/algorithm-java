package 배열과문자열;

import java.util.*;
import java.io.*;

public class b5430_리버스와팝 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int turn_n = Integer.parseInt(br.readLine());
        for (int i = 0; i < turn_n; i++) {
            String[] function = br.readLine().split("");
            int N = Integer.parseInt(br.readLine());
            String input = br.readLine();

            // 입력받고 큐에 추가
            Deque<String> queue = new ArrayDeque<>();
            if (N > 0) {
                String[] elements = input.substring(1, input.length() - 1).split(",");
                queue.addAll(Arrays.asList(elements));
            }

            boolean reverse = false;
            boolean error = false;

            for (String func : function) {
                if (func.equals("R")) {
                    reverse = !reverse; // 리버스 상태 토글
                } else if (func.equals("D")) {
                    if (queue.isEmpty()) {
                        System.out.println("error");
                        error = true;
                        break;
                    }
                    if (reverse) {
                        queue.pollLast(); // 리버스 상태면 뒤에서 제거
                    } else {
                        queue.pollFirst(); // 기본 상태면 앞에서 제거
                    }
                }
            }

            if (!error) {
                StringBuilder result = new StringBuilder("[");
                if (reverse) {
                    while (!queue.isEmpty()) {
                        result.append(queue.pollLast()).append(",");
                    }
                } else {
                    while (!queue.isEmpty()) {
                        result.append(queue.pollFirst()).append(",");
                    }
                }

                if (result.length() > 1) {
                    result.deleteCharAt(result.length() - 1); // 마지막 쉼표 제거
                }
                result.append("]");
                System.out.println(result);
            }
        }
    }
}
