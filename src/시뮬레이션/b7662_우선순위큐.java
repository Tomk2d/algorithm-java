package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b7662_우선순위큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> countMap = new HashMap<>(); // 값의 개수를 관리

            for (int i = 0; i < k; i++) {
                String[] command = br.readLine().split(" ");
                char operation = command[0].charAt(0);
                int value = Integer.parseInt(command[1]);

                if (operation == 'I') {
                    // 삽입 연산
                    minHeap.offer(value);
                    maxHeap.offer(value);
                    countMap.put(value, countMap.getOrDefault(value, 0) + 1);
                } else if (operation == 'D') {
                    if (value == 1) {
                        // 최대값 삭제
                        removeTop(maxHeap, countMap);
                    } else if (value == -1) {
                        // 최소값 삭제
                        removeTop(minHeap, countMap);
                    }
                }
            }

            // 최종 결과 출력
            removeInvalid(minHeap, countMap);
            removeInvalid(maxHeap, countMap);

            if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(maxHeap.peek()).append(" ").append(minHeap.peek()).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    // 우선순위 큐에서 유효하지 않은 값 제거
    private static void removeTop(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap) {
        while (!heap.isEmpty()) {
            int top = heap.peek();
            if (countMap.getOrDefault(top, 0) > 0) {
                countMap.put(top, countMap.get(top) - 1);
                if (countMap.get(top) == 0) {
                    countMap.remove(top);
                }
                heap.poll();
                break;
            } else {
                heap.poll();
            }
        }
    }

    // 우선순위 큐에서 유효하지 않은 값 제거 (정리용)
    private static void removeInvalid(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap) {
        while (!heap.isEmpty() && countMap.getOrDefault(heap.peek(), 0) == 0) {
            heap.poll();
        }
    }
}
