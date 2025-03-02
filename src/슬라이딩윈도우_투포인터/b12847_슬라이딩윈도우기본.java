package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b12847_슬라이딩윈도우기본 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] days = new int[N];
        for(int i=0; i<N; i++){
            days[i] = Integer.parseInt(st.nextToken());
        }

        long maxPay = 0, currentPay =0;

        for(int i=0; i<P; i++){
            currentPay+= days[i];
        }
        maxPay = currentPay;

        for(int i=P; i<N; i++){
            currentPay += days[i];
            currentPay -= days[i-P];
            maxPay = Math.max(currentPay, maxPay);
        }

        System.out.println(maxPay);
    }
}

