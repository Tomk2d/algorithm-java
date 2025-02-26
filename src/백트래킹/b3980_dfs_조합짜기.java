package 백트래킹;

import java.io.*;

public class b3980_dfs_조합짜기
{
    private static int[][] board = new int[11][11];
    private static int maxPoint = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while(N --> 0){
            for(int i =0; i<11; i++){
                String[] input = br.readLine().split(" ");
                for(int j=0; j<11; j++){
                    board[i][j] = Integer.parseInt(input[j]);
                }
            }

            boolean[] visited = new boolean[11];
            dfs(0, visited, 0);
            System.out.println(maxPoint);
            maxPoint = 0;
        }
    }

    private static void dfs(int idx, boolean[] visited, int point){
        if(idx >= 11){
            maxPoint = Math.max(maxPoint, point);
            return;
        }
        for(int i=0; i<11; i++){
            if(!visited[i] && board[idx][i]!=0){
                visited[i] = true;
                dfs(idx+1, visited, point+board[idx][i]);
                visited[i] = false;
            }
        }
    }
}

