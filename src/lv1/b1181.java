package lv1;

import java.io.*;
import java.util.*;

public class b1181 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Set<String> list = new HashSet<>();
        for (int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        List<String> array = new ArrayList<>(list);

        Collections.sort(array, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length(); // 길이순 정렬
            } else {
                return a.compareTo(b); // 사전순 정렬
            }
        });

        for(String a : array){
            System.out.println(a);
        }
    }
}
