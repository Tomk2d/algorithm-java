package lv1;

import java.io.*;
import java.util.StringTokenizer;

public class b11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        String[] array = s.split("");
        int sum = 0;
        for (String a : array) {
            sum += Integer.parseInt(a);
        }
        System.out.println(sum);
    }
}
