package 백트래킹;

import java.util.*;
import java.io.*;

// 해당 문제는 bfs 를 활용하되, 싸이클을 찾아야 한다
// 무한으로 같은 부분을 반복하는 부분 말이다.
// 문자열로 visited 를 생성하고, 이부분을 검증한다.
// 뭐 정렬된 배열 같은걸로 해도 될거 같긴하다.
// 근데 이게 제일 빠르고 보기 쉬워 보인다

public class b12886_bfs와_싸이클찾기 {
    private static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        if ((A + B + C) % 3 != 0) {
            System.out.println(0);
            return;
        }

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(A, B, C));

        Set<String> visited = new HashSet<>(); // 방문 체크를 위한 Set
        visited.add(A + "," + B + "," + C);

        boolean isfirst = true;

        while (!queue.isEmpty()) {
            Point now = queue.pollFirst();

            if (isfirst) {
                isfirst = false;
            } else if (now.a == A && now.b == B && now.c == C) {
                System.out.println(0);
                return;
            }

            if (now.a == now.b && now.b == now.c) {
                System.out.println(1);
                return;
            }

            // a와 b, a와 c, b와 c를 비교
            processAndAdd(queue, visited, now.a, now.b, now.c);
            processAndAdd(queue, visited, now.a, now.c, now.b);
            processAndAdd(queue, visited, now.b, now.c, now.a);
        }

        System.out.println(0);
    }

    // 두 값을 비교하여 계산 후 큐에 추가하는 메서드
    private static void processAndAdd(Deque<Point> queue, Set<String> visited, int x, int y, int z) {
        if (x != y) {
            int min = Math.min(x, y);
            int max = Math.max(x, y);
            int newX = min + min;
            int newY = max - min;
            String state = newX + "," + newY + "," + z;
            if (!visited.contains(state)) {
                visited.add(state);
                queue.add(new Point(newX, newY, z));
            }
        }
    }

    private static class Point {
        int a, b, c;

        Point(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
