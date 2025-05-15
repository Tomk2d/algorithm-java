package DP많이나오는유형;

import java.io.*;

/*
*       나는 보통 이런 경우를 만나면, 문자열까지 저장할 생각을 한다
*       Node 라는 객체를 만들어서 cnt 와 str 을 누적해서 계속 저장하였고,
*       이를 dp 배열에 담앗다. (int 대신 Node 타입의 2차원 배열)
*       이는 시간 초과를 야기했다.
*       Node 객체를 생성하는 비용이 아니라, 서로 다른 Node 에서 담는 String 객체를 매번 생성하는것이 문제이다
*       이를 개선하기 위해서 String[][] 을 하나더 생성해서 관리할까 했지만 같은 공간 복잡도를 가진다.
*       이런 경우 문자열을 역추적하는 과정을 거친다.
*       마지막 요소부터 문자열이 서로 같을때를 찾아가면서 문자열을 역추적 하는 것 이다.]
*       이후에 이 문자열을 리버스 하면 된다.
* */

public class b9252_LCS2_문자열역추적 {
    private static int N;
    private static int[] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word1 = br.readLine();
        String word2 = br.readLine();
        int N = word1.length();
        int M = word2.length();

        int[][] dp = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        String str = "";

        int i = N;
        int j = M;

        while(i>0 && j>0){
            if(word1.charAt(i-1) == word2.charAt(j-1)){
                str += word1.charAt(i-1);
                i--;
                j--;
            }else if(dp[i-1][j] > dp[i][j-1]){  // 위가 더 큰수
                i--;
            }else{
                j--;
            }
        }
        StringBuilder sb = new StringBuilder(str);
        System.out.println(dp[N][M]);
        System.out.println(sb.reverse().toString());
    }
}
