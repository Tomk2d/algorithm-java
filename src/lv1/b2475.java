package lv1;

import java.io.*;
import java.util.StringTokenizer;

public class b2475 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i =0; i<=4; i++){
            int n = Integer.parseInt(st.nextToken());
            sum += n * n;
        }
        System.out.println(sum%10);
    }

}
