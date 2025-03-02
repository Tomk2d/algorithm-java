package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b3078_부분배열과_조합과_슬라이딩윈도우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int gap = Integer.parseInt(st.nextToken()); // 윈도우 크기 (등수 차이)

        int[] rank = new int[N];
        for (int i = 0; i < N; i++) {
            rank[i] = br.readLine().length(); // 각 학생 이름의 길이를 저장
        }

        Map<Integer, Integer> friendCnt = new HashMap<>(); // 이름 길이별 학생 수
        long answer = 0;

        for (int i = 0; i < gap; i++) {
            int currentLength = rank[i];
            friendCnt.put(currentLength, friendCnt.getOrDefault(currentLength, 0) + 1);
            answer += friendCnt.get(currentLength) - 1;
        }

        for (int i = gap; i < N; i++) {
            int currentLength = rank[i];
            friendCnt.put(currentLength, friendCnt.getOrDefault(currentLength, 0) + 1);

            answer += friendCnt.get(currentLength) - 1;

            int preLength = rank[i - gap];
            friendCnt.put(preLength, friendCnt.get(preLength) - 1);
            if (friendCnt.get(preLength) == 0) {
                friendCnt.remove(preLength);
            }
        }

        System.out.println(answer);
    }
}

