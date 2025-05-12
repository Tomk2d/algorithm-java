package 플로이드_와샬;

import java.util.*;
import java.io.*;

/*
*       이 문제는 사이클을 형성해야 한다.
*       형성되는 싸이클 중에서 최소 비용을 갖는 애를 찾는것 이다
*       플로이드-와샬의 기본 문제 같다
* */
public class b1956_최소_사이클의_비용구하기
{
    private static int nodeN, lineN;
    private static int[][] minArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeN = Integer.parseInt(st.nextToken());
        lineN = Integer.parseInt(st.nextToken());

        minArray = new int[nodeN+1][nodeN+1];

        for(int i=1; i<=nodeN; i++){
            // i번째에서 다른 노드까지 갈때의 비용 저장. 초기값은 max
            Arrays.fill(minArray[i], Integer.MAX_VALUE);
        }


        for(int i=0; i<lineN; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 플로이드-와샬 비용 저장
            // 각 노드에서 다른 노드까지의 최소비용 저장
            // a 에서 b 까지의 비용
            minArray[a][b] = cost;
        }

        for(int k=1; k<=nodeN; k++){
            for(int i=1; i<=nodeN; i++){
                for(int j=1; j<=nodeN; j++){
                    // 경로 축소 검사
                    // a -> c 와 a -> b -> c 를 비교해서 더 저렴한거 사용
                    // MAX_VALUE 는 경로가 없는거니까 패스
                    if(minArray[i][k] != Integer.MAX_VALUE && minArray[k][j] != Integer.MAX_VALUE){
                        minArray[i][j] = Math.min(minArray[i][j], minArray[i][k] + minArray[k][j]);
                    }
                }
            }
        }

        // 최소 사이클 찾기. 결국 자기한테 돌아온 애중에 최소 비용
        int answer = Integer.MAX_VALUE;
        for(int i=1; i<nodeN; i++){
            if(minArray[i][i] < answer){
                answer = minArray[i][i];
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static class Node{
        int next;
        int cost;

        Node(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }

}

