package DP;

import java.util.*;
import java.io.*;

// 메모리 제한이 4mb 여서 2차원 배열 2개를 쓸 수 없었다.
// 최초에는 max 값을 저장하는 2차원배열 1개, min 값을 저장하는 2차원배열 1개를 사용했다.
// 하지만 메모리 초과가 나서 이전 max 1차원배열 1개, 현재 max 1차원배열 1개, 이전 min 1차원배열 1개, 현재 min 1차원배열 1개
// 를 사용하여 문제를 풀었다. 그리고 dx 도 없앴다. 무조건 내려가기 때문에, dy 만 계산해 주었다.
// 이전과 현재를 사용하면 너무나도 헷갈린당 ㅠ

public class b_2096_시뮬레이션과_DP누적합 {
    private static int N;
    private static int[] dy = new int[]{-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][3];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] prevMax = board[0].clone();
        int[] prevMin = board[0].clone();

        for(int x=1; x<N; x++){
            int[] nowMax = new int[3];
            int[] nowMin = new int[3];

            Arrays.fill(nowMin, Integer.MAX_VALUE);

            for(int y=0; y<3; y++){
                // 위에까진 블럭 선택. 아래는 다음 방향
                for(int i=0; i<3; i++){
                    int ny = y + dy[i];

                    if(ny>=0 && ny<3){
                        nowMax[y] = Math.max(nowMax[y], prevMax[ny]+board[x][y]);
                        nowMin[y] = Math.min(nowMin[y], prevMin[ny]+board[x][y]);
                    }
                }
            }
            prevMax = nowMax;
            prevMin = nowMin;
        }

        int maxN = Arrays.stream(prevMax).max().getAsInt();
        int minN = Arrays.stream(prevMin).min().getAsInt();

        System.out.println(maxN + " "+ minN);

    }
}

