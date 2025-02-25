package 구현;


import java.io.*;
import java.util.*;

public class b14719_2차원배열_시뮬레이션구현_그리디 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 최대 높이
        int M = Integer.parseInt(st.nextToken()); // 블록 개수
        int[] heights = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        boolean inBox;
        int totalWater = 0;

        // 층마다 확인
        for (int height = 1; height <= N; height++) {
            int heightWater = 0;
            inBox = false; // 매 층별로 초기화

            for (int block = 0; block < M; block++) {
                if (heights[block] >= height) {     // 벽을 만났을 때
                    if (inBox) {    // 이전에 벽이 있었다면, 물을 저장
                        totalWater += heightWater;
                    }
                    // 새로운 벽을 만났으므로 heightWater 초기화
                    heightWater = 0;
                    inBox = true;
                } else if (inBox) {     // 벽이 닫히기 전이면 물을 쌓음
                    heightWater++;
                }
            }
        }

        System.out.println(totalWater);
    }
}

