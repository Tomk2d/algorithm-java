package lv1;

import java.io.*;
import java.util.StringTokenizer;


public class b2439 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++) {
            int blank = n-i;
            for(int j = 1; j <= n; j++) {
                if (j > blank){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
