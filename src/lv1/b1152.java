package lv1;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class b1152 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        List<String> list = Arrays.stream(str.split(" "))
            .filter(s -> !s.isEmpty()) // 빈 문자열 제거
            .collect(Collectors.toList());

        System.out.println(list.size());
    }
}
