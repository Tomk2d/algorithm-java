package 유니온파인드;

import java.util.*;
import java.io.*;

public class b1922_최소스패닝트리 {
    private static int N, M;
    private static int[][] lines;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        lines = new int[M][3];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            if(a == b){
                continue;
            }
            lines[i] = new int[]{a, b, weight};
        }
        parent = new int[N];
        for(int i=0; i<N; i++){
            parent[i] = i;
        }

        Arrays.sort(lines, (a, b) -> Integer.compare(a[2], b[2]));

        int sum = 0;
        int cnt = 0;

        for(int[] line : lines){
            int a = line[0];
            int b = line[1];
            int weight = line[2];

            // 여기서 parent[a] != paret[b] 를 하게 되면
            // 바로 위의 부모를 검사하기 때문에 x
            // 무조건 루트를 검사해야함.
            if(findParent(a) != findParent(b)){
                unionParent(a, b);
                sum += weight;
                cnt ++;

                if(cnt == N-1){
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    private static int findParent(int x){
        if(x != parent[x]){
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    private static void unionParent(int a, int b){
        int rootA = parent[a];
        int rootB = parent[b];

        if(rootA != rootB){
            if(rootA<rootB){
                parent[rootB] = rootA;
            }
            else{
                parent[rootA] = rootB;
            }
        }
    }
}

