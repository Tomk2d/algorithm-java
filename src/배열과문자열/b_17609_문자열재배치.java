package 배열과문자열;

import java.io.*;
import java.util.*;

public class b_17609_문자열재배치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            String[] str = br.readLine().split("");
            int resultIdx = check(str);
            if(resultIdx == 100001){
                // 펠린드롭인 경우
                System.out.println(0);
            }else{
                // 아닌경우, 유사회문 체크
                // 1. 앞에서 제거
                String[] copyStr = makeCopy(str, resultIdx);
                if(check(copyStr) == 100001){
                    System.out.println(1);
                }else{
                    copyStr = makeCopy(str, str.length - 1 - resultIdx);
                    if(check(copyStr) == 100001){
                        System.out.println(1);
                    }
                    else{
                        System.out.println(2);
                    }
                }
            }
        }
    }

    public static int check(String[] str){

        for(int i=0; i<str.length/2; i++){
            int start = i;
            int end = str.length - 1 - i;

            if(!str[start].equals(str[end])){
                return i;
            }
        }
        return 100001;
    }
    public static String[] makeCopy(String[] str, int resultIdx){
        String a = str[resultIdx];
        List<String> copyStr = new ArrayList<>();

        for(int i=0; i<str.length; i++){
            if(i == resultIdx){
                continue;
            }
            copyStr.add(str[i]);
        }
        return copyStr.toArray(new String[0]);
    }
}

