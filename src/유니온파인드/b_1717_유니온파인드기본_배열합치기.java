package 유니온파인드;

import java.util.*;
import java.io.*;

public class b_1717_유니온파인드기본_배열합치기{
    private static int[] my_parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 나의 바로위 부모 기록
        my_parent = new int[N+1];
        // 처음에는 각각의 집합이니까, 내가 내 부모
        for(int i=1; i<N+1; i++){
            my_parent[i] = i;
        }

        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int func = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(func == 0){
                union(a, b);
            }else if(func == 1){
                if(find(a) == find(b)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }
    // 결국 최상위 부모 1개에 동일 레벨 자식 여러개 있는 형태.
    public static int find(int num){
        // 내가 최상위가 아닐때. 부모가 있을때.
        if(my_parent[num] != num){
            my_parent[num] = find(my_parent[num]);
        }
        return my_parent[num];
    }
    // 합치기. 부모 다르면, 니부모가 내 부모 밑으로와.
    // 어차피 같은곳에 있는지만 검사하면 되니까. 누가 밑으로 가든 상관 X
    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB){
            my_parent[rootB] = rootA;
        }
    }
}

