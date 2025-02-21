package 스택;

import java.io.*;
import java.util.*;

// 해당 문제는 스택 구조를 활용해야 했다.(스택 잘 안써서 deque 를 쓰긴 했지만)
// 타워는 결국 좌측에 있는 타워중 작은건 무시되니까, (계단식 구조가 완성됨)
// 그중 가장 작은거 부터 비교하면서 닿을 곳을 찾는다.

public class b2493_스택으로_최대값만구하기 {
    private static int maxTemp = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] towers = new int[N];
        int[] result = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            towers[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> queue = new ArrayDeque<>();

        for(int i=0; i<N; i++){
            while(!queue.isEmpty() && towers[queue.peekLast()] < towers[i]){
                queue.pollLast();
            }

            if(!queue.isEmpty()){
                result[i] = queue.peekLast() + 1;
            }

            queue.add(i);
        }

        for(int i=0; i<N; i++){
            System.out.print(result[i] + " ");
        }
    }
}

