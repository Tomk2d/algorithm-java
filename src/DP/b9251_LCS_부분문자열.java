package DP;

import java.io.*;

// 이 문제는 먼저 LCS 라는것에 대해서 알아야 한다.
// 두 문자열이 주어졌을때, 연속된 거에 상관없이 !!!
// 앞에서 부터 진행되면서 같은 문자들을 구하는 것이다.
// ACAYKP
// CAPCAK
// 이 2개 가 있을때, 최장 공통부분 수열은 ACAK 이다
// 첫번째 문자열의 A 를 두번째 문자열에서 찾고, (idx=1, idx=4)
// 이후 C를 를 두번째 문자열의 1 이후, 4 이후 에서 찾는다.
// 다음과 같은 과정으로 일치하는 문자 이후에서 일치하는 문자를 찾는것이다.
// DP 로 표를 그리며 해보면 된다. 양쪽의 경우를 모두 고려해야 하기에,
// 윗열(2번째 문자열의 이전값)과 왼쪽열(1번째 문자열의 이전값) 을 고려하며 최대 길이를 찾는다.
// 일치할때 카운트는 대각 왼쪽(x-1, y-1) 의 개수에 +1 을 한다. 왜냐하면 두 문자열 모두 현재를 고려하여, 이전 최대값에서 더해야하기 때문이다.
// 하지만 일치하지 않을경우는 : 윗열과 왼쪽열 중 최대값을 저장한다. 이는 최대값 계산을 위한 갱신의 용도이다.

public class b9251_LCS_부분문자열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input1 = br.readLine();
        String input2 = br.readLine();

        int[][] dp = new int[input1.length() + 1][input2.length() + 1];

        for(int i=1; i<=input1.length(); i++){
            for(int j=1; j<=input2.length(); j++){
                if(input1.charAt(i-1) == input2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[input1.length()][input2.length()]);
    }
}

