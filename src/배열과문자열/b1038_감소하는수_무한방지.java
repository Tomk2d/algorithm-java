package 배열과문자열;

import java.util.*;
import java.io.*;

public class b1038_감소하는수_무한방지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Long> decreasingNumbers = new ArrayList<>();
        generateDecreasingNumbers(decreasingNumbers);

        if (N >= decreasingNumbers.size()) {
            System.out.println(-1);
        } else {
            System.out.println(decreasingNumbers.get(N));
        }
    }

    private static void generateDecreasingNumbers(List<Long> list) {
        Queue<Long> queue = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            queue.add((long) i);
            list.add((long) i);
        }

        while (!queue.isEmpty()) {
            long num = queue.poll();
            int lastDigit = (int) (num % 10);

            for (int i = 0; i < lastDigit; i++) {
                long newNum = num * 10 + i;
                queue.add(newNum);
                list.add(newNum);

                if (list.size() > 1000000) {
                    return;
                }
            }
        }
    }
}

