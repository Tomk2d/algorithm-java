package 정렬;

import java.util.*;
import java.io.*;

public class b10814 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[][] array = new String[N][2];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            String[] arr = input.split(" ");

            array[i][0] = arr[0];
            array[i][1] = arr[1];
        }

        Arrays.sort(array, (a, b) -> Integer.parseInt(a[0]) - Integer.parseInt(b[0]));

        for(int i = 0; i < N; i++) {
            System.out.println(array[i][0] + " " + array[i][1]);

        }
    }
}
