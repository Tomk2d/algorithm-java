package lv1;

import java.util.*;
import java.io.*;

public class b2675 {

    public static String make_string(int n, String str){
        String answer = "";
        for (char c : str.toCharArray()){
            for (int j=0; j<n; j++){
                answer += c;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            String str = input[1];
            System.out.println(make_string(n, str));
        }
    }
}
