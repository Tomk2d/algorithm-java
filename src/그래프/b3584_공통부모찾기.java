package 그래프;

import java.io.*;
import java.util.*;

// 원래는 dfs 로 공통부모찾고, 그 레벨을 기록하는 문제 같다.
// 하지만 부모와 자식 정보를 주길래, 한 노드에서 방문한적 있는 부모를 저장하고,
// 다른 노드에서 위로 올라가면서 방문되어 있으면 그걸 출력한다. (최초로 공통되는에)
public class b3584_공통부모찾기
{
    private static int[] parent;
    private static int targetA;
    private static int targetB;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Case = Integer.parseInt(st.nextToken());

        while(Case-- >0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            parent = new int[N];
            for(int i=0; i<N; i++){
                parent[i] = i;
            }

            visited = new boolean[10001];

            while(N-- >1){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                parent[b] = a;
            }
            st = new StringTokenizer(br.readLine());
            targetA = Integer.parseInt(st.nextToken()) - 1;
            targetB = Integer.parseInt(st.nextToken()) - 1;

            checkParent(targetA);
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(targetB);
            while(!queue.isEmpty()){
                int now = queue.pollFirst();

                if(visited[parent[now]]){
                    System.out.println(parent[now] + 1);
                    break;
                }
                queue.add(parent[now]);
            }
        }

    }

    private static void checkParent(int x){
        visited[x] = true;
        if(x != parent[x]){
            checkParent(parent[x]);
        }
    }
}

