package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b12891_문자열슬라이딩윈도우 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String[] dna = br.readLine().split("");

        st = new StringTokenizer(br.readLine());

        int[] ACGT_count = new int[4];

        for(int i=0; i<4; i++){
            ACGT_count[i] = Integer.parseInt(st.nextToken());
        }

        int[] current_count = new int[4];

        for(int i=0; i<P; i++){
            addString(dna[i], current_count);
        }

        int answer = 0;

        if(checkString(current_count, ACGT_count)){
            answer ++;
        }

        for(int i=P; i<N; i++){
            addString(dna[i], current_count);
            removeString(dna[i-P], current_count);

            if(checkString(current_count, ACGT_count)){
                answer ++;
            }
        }
        System.out.println(answer);
    }

    private static void addString(String s, int[] current_count){
        switch(s){
            case "A": current_count[0]++; break;
            case "C": current_count[1]++; break;
            case "G": current_count[2]++; break;
            case "T": current_count[3]++; break;
        }
    }

    private static void removeString(String s, int[] current_count) {
        switch (s) {
            case "A": current_count[0]--; break;
            case "C": current_count[1]--; break;
            case "G": current_count[2]--; break;
            case "T": current_count[3]--; break;
        }
    }
    private static boolean checkString(int[] current_count, int[] ACGT_count) {
        for(int i=0; i<4; i++){
            if(current_count[i]<ACGT_count[i]){
                return false;
            }
        }
        return true;
    }
}

