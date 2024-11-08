package lv1;

import java.io.*;
import java.util.StringTokenizer;


public class b2739 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 9; i++) {
            System.out.println(n + " * "+ i + " = "+ n*i);
        }
    }
}
