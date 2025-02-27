package 구현;

import java.util.Scanner;

public class b2877_이진수구현 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        System.out.println(findKthLuckyNumber(K));
        sc.close();
    }

    private static String findKthLuckyNumber(int K) {
        String binary = Integer.toBinaryString(K + 1).substring(1); // K+1의 이진수 변환 후 첫 번째 비트 제거
        return binary.replace('0', '4').replace('1', '7'); // 0 → 4, 1 → 7 변환
    }
}

