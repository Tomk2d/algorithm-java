package lv1;

import java.io.*;
import java.util.*;

public class b1000 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Double N = Double.parseDouble(st.nextToken());
        Double M = Double.parseDouble(st.nextToken());

        System.out.println(N / M);
    }

}
