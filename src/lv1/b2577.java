package lv1;

import java.util.*;
import java.io.*;

public class b2577 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine());
        String str = ""+n;
        List<String> list = List.of(str.split(""));

        int[] arr = new int[10];
        for (String s : list){
            arr[Integer.parseInt(s)]++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }
}
