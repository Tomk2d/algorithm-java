package lv1;

import java.io.*;
import java.util.StringTokenizer;

public class b1330{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (n > m) System.out.println(">");
        else if (n < m) System.out.println("<");
        else System.out.println("==");
    }
}
