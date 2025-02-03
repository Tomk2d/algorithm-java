package 배열과문자열;

import java.io.*;

// 어차피 2번만 해봐도 앎.
public class b_15927_부분집합_펠린드롬 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strArr = br.readLine().split("");

        int strLen = strArr.length;
        if(!check(strArr, strLen)){
            System.out.println(strLen);
        }else if(!check(strArr, strLen-1)){
            System.out.println(strLen-1);
        }else{
            System.out.println(-1);
        }
    }

    public static boolean check(String[] strArr, int n){
        for(int i=0; i<n/2; i++){
            int start = i;
            int end = n-i-1;

            // 펠린드롬 아닐때.
            if(!strArr[start].equals(strArr[end])){
                return false;
            }
        }
        return true;
    }
}

