package 백트래킹;

import java.io.*;
import java.util.*;

// toCharArray() 나 for 문으로 Map 초기화 하는 정도 보면 될거 같다.

public class b1987_백트래킹_시뮬레이션_문자검증조건 {
    private static int N, M;
    private static char[][] board;
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for(int i=0; i<N; i++){
            char[] input = br.readLine().toCharArray();
            board[i] = input;
        }

        Map<Character, Boolean> dict = new HashMap<>();

        for(char c='A'; c<='Z'; c++){
            dict.put(c, false);
        }
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        char a = board[0][0];
        dict.put(a, true);
        dfs(0, 0, visited, dict, 1);

        System.out.println(answer);
    }

    private static void dfs(int x, int y, boolean[][] visited, Map<Character, Boolean> dict, int cnt){
        answer = Math.max(answer, cnt);

        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<M && !visited[nx][ny]){
                char a = board[nx][ny];

                if(!dict.get(a)){
                    visited[nx][ny] = true;
                    dict.put(a, true);
                    dfs(nx, ny, visited, dict, cnt+1);
                    visited[nx][ny] = false;
                    dict.put(a, false);
                }
            }
        }
    }
}

