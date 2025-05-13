package 배열과문자열;

import java.io.*;

/*
*       이 문제는 다른거 보다 일단 시간을 최소화 하는게 가장 중요해 보인다
*       먼저 문자의 최대 사이즈부터 검사하면서 break 를 반드시 시행해야하고,
*       그걸 시행하면서도, 조건보다 작은걸 검사할때 바로 break 한다
*       걍 예외처리만 잘하면 될 거 같다
* */

public class b2179_비슷한문자열찾기_시간최소화
{
    private static int minCnt = 0;
    private static String answer1, answer2;
    private static String[] arr;
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new String[N];

        for(int i=0; i<N; i++){
            arr[i] = br.readLine();
        }

        for(int i=0; i<N-1; i++){
            check(i);
        }

        System.out.println(answer1);
        System.out.println(answer2);

    }

    private static void check(int idx){
        String value1 = arr[idx];

        for(int i=idx+1; i<N; i++){
            String value2 = arr[i];

            for(int size=value1.length(); size>0; size--){
                if(value2.length()<size) continue;
                if(size <= minCnt) break;

                String val1 = value1.substring(0, size);
                String val2 = value2.substring(0, size);

                if(val1.equals(val2)){
                    minCnt = size;
                    answer1 = value1;
                    answer2 = value2;
                    break;
                }
            }
        }

    }
}

