package 유니온파인드;

import java.util.*;
import java.io.*;
public class b1043_유니온파인드_같은집합으로묶기
{
    private static int peopleN, partyN;
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        peopleN = Integer.parseInt(st.nextToken());
        partyN = Integer.parseInt(st.nextToken());

        parent = new int[peopleN+1];
        for(int i=0; i<=peopleN; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int truePeopleN = Integer.parseInt(st.nextToken());

        int prePeople = 0;
        // 진실 피플. 0이랑 합쳐서, 진실아는 애들 0으로 만듬.
        for(int i=0; i<truePeopleN; i++){
            int p = Integer.parseInt(st.nextToken());
            union(prePeople, p);
            prePeople = p;
        }

        if(truePeopleN == 0){
            System.out.println(partyN);
            return;
        }

        int answer = 0;

        List<Integer> partyFirstPeople = new ArrayList<>();

        while(partyN -->0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            if(N == 0) continue;

            // 첫번째 놈만 저장. 어차피 진실알면 0으로 되게 해놨음
            int firstPeople = Integer.parseInt(st.nextToken());
            partyFirstPeople.add(firstPeople);

            // 같은 모임인 놈들 모두 같은 조상으로 묶음.
            // 어차피 0이 하나라도 있으면 0됨.
            for(int i=1; i<N; i++){
                int p = Integer.parseInt(st.nextToken());
                union(firstPeople, p);
            }
        }

        for(int p : partyFirstPeople){
            if(find(p) != 0) answer ++;
        }

        System.out.println(answer);
    }

    private static int find(int x){
        if(x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA < rootB){
            parent[rootB] = rootA;
        }else{
            parent[rootA] = rootB;
        }
    }
}

