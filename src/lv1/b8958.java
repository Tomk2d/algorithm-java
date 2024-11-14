package lv1;

import java.util.*;
import java.io.*;

public class b8958 {

    public static int calculate(String str){
        int point = 0;
        int cnt = 0;

        for(char c : str.toCharArray()){
            if(c == 'O'){
                cnt++;
                point += cnt;
            }if(c == 'X'){
                cnt = 0;
            }
        }
        return point;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            System.out.println(calculate(str));
        }
    }
}
