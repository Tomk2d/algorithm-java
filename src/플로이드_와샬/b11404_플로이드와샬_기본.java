package 플로이드_와샬;

import java.util.*;
import java.io.*;

/*
    여기서는 자기 자신을 (minArray[i][i]) 0으로 초기화 하는 부분이 중요하다
    싸이클의 비용을 구하는 문제에서는
    자기자신에 싸이클을 돌아서 오는 비용을 저장했지만,
    이 문제에서는 싸이클 비용이 아니다.
    즉 자기자신에게 오는 최소 비용은 가만히 있는 경우. 0 이다.
*/
public class b11404_플로이드와샬_기본 {
    private static int nodeN, lineN;
    private static int[][] minArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeN = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        lineN = Integer.parseInt(st.nextToken());

        minArray = new int[nodeN][nodeN];
        for(int i=0; i<nodeN; i++){
            Arrays.fill(minArray[i], Integer.MAX_VALUE);
        }

        // 자기 자신은 거리 0으로 초기화
        for (int i = 0; i < nodeN; i++) {
            minArray[i][i] = 0;
        }

        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());

            minArray[a][b] = Math.min(minArray[a][b], cost);
        }

        for(int k=0; k<nodeN; k++){
            for(int i=0; i<nodeN; i++){
                for(int j=0; j<nodeN; j++){
                    if(minArray[i][k] != Integer.MAX_VALUE && minArray[k][j] != Integer.MAX_VALUE){
                        minArray[i][j] = Math.min(minArray[i][j], minArray[i][k]+minArray[k][j]);
                    }
                }
            }
        }

        for(int i=0; i<nodeN; i++){
            for(int j=0; j<nodeN; j++){
                if(minArray[i][j] == Integer.MAX_VALUE){
                    minArray[i][j] = 0;
                }
            }
        }

        for(int i=0; i<nodeN; i++){
            for(int j=0; j<nodeN; j++){
                System.out.print(minArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}

