package 스택;

import java.io.*;
import java.util.*;

public class b13305_최소값구하기지만_스택은쓰지않은_스택방법 {
    private static int N;
    private static int[] pays;
    private static int[] oilPays;
    private static int[] minPays;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        pays = new int[N-1];
        oilPays = new int[N];
        minPays = new int[N-1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++){
            pays[i] = Integer.parseInt(st.nextToken());
        }

        int preMinIdx = 0;
        long sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int oilPay = Integer.parseInt(st.nextToken());
            oilPays[i] = oilPay;

            if(i < N-1){
                int preMinPay = oilPays[preMinIdx];

                if(preMinPay > oilPay){
                    preMinIdx = i;
                    sum += (long) oilPay * pays[i];
                }else{      // 이전꺼가 쌀때
                    sum += (long) preMinPay * pays[i];
                }
            }
        }

        System.out.println(sum);
    }
}

