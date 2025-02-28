package 슬라이딩윈도우;

import java.io.*;

// totalA는 문자열 전체에서 'a'의 개수를 의미
// 우리는 'a'들을 연속으로 모아야 하므로, 적어도 'a' 개수만큼의 연속된 부분을 찾아야 함
// 예를 들어, 문자열이 aabbaaabaaba라면: 'a' 개수는 7개
// 즉, 'a' 7개를 연속으로 만들기 위해 크기 7짜리 구간을 검사
// 이 구간에 있는 'b'를 'a'로 바꾸면 되므로 최소 몇 개의 'b'를 바꿔야 하는지 찾으면 됨

public class b1522_문자열_슬라이딩윈도우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int n = input.length();

        int totalA = 0;
        for (char c : input.toCharArray()) {
            if (c == 'a') totalA++;
        }

        int minSwap = Integer.MAX_VALUE;
        int countB = 0;

        for (int i = 0; i < totalA; i++) {
            if (input.charAt(i) == 'b') countB++;
        }
        minSwap = countB;

        int start = 0;
        int end = totalA % n;

        while(start < n){
            // 윈도우에서 나가는 놈
            if(input.charAt(start) == 'b') countB--;
            // 윈도우에 들오는 놈
            if(input.charAt(end) == 'b') countB++;

            minSwap = Math.min(minSwap, countB);

            start++;
            end = (end+1) % n;
        }

        System.out.println(minSwap);
    }
}

