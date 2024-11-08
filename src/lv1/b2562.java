package lv1;

import java.io.*;
import java.util.StringTokenizer;

public class b2562 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int idx = -1;
        for(int i =1; i <=9; i++){
            int n = Integer.parseInt(br.readLine());
            if(n > max) {
                max = n;
                idx = i;
            }
        }
        System.out.println(max);
        System.out.println(idx);
    }
}
