package 슬라이딩윈도우;

import java.util.*;
import java.io.*;

public class b15686_문자열슬라이딩윈도우 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[] dna = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        int[] minCounts = new int[4]; // A, C, G, T 최소 개수
        for (int i = 0; i < 4; i++) {
            minCounts[i] = Integer.parseInt(st.nextToken());
        }

        int[] currentCnt = new int[4];
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            addChar(dna[i], currentCnt);
        }

        if (check(currentCnt, minCounts)) {
            cnt++;
        }

        for (int i = M; i < N; i++) {
            addChar(dna[i], currentCnt);
            removeChar(dna[i - M], currentCnt);

            if (check(currentCnt, minCounts)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void addChar(char c, int[] counts) {
        switch (c) {
            case 'A': counts[0]++; break;
            case 'C': counts[1]++; break;
            case 'G': counts[2]++; break;
            case 'T': counts[3]++; break;
        }
    }

    private static void removeChar(char c, int[] counts) {
        switch (c) {
            case 'A': counts[0]--; break;
            case 'C': counts[1]--; break;
            case 'G': counts[2]--; break;
            case 'T': counts[3]--; break;
        }
    }

    private static boolean check(int[] currentCnt, int[] minCounts) {
        for (int i = 0; i < 4; i++) {
            if (currentCnt[i] < minCounts[i]) {
                return false;
            }
        }
        return true;
    }
}

