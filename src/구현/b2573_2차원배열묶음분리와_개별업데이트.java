package 구현;

import java.util.*;
import java.io.*;

// 처음 문제를 풀때, 녹는부분을 바로 마이너스 해줘서 오답이었다.
// 얼음이 한번에 녹는다는 가정이기때문에, 과정을 나누어 한번에 체크하고, 한번에 업데이트 한다.
// 배열이 10000칸 즉, 100 x 100 이라서 메모리에 문제가 없을것이라고 생각했다.
// 이후, 음수처리 된 부분까지 세던, 아님 0으로 애초에 업데이트하던 하면 된다.

public class b2573_2차원배열묶음분리와_개별업데이트
{
    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[][] minusBoard;
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while(true){
            day ++;
            visited = new boolean[N][M];
            minusBoard = new int[N][M];

            // 1. 맏닿아 있는 0개수 체크
            visited = new boolean[N][M];
            for(int x=0; x<N; x++){
                for(int y=0; y<M; y++){
                    if(board[x][y]>0 && !visited[x][y]){
                        visited[x][y] = true;
                        checkZeroCount(x, y);
                    }

                }
            }
            // 2. 전체 보드를 한번에 업데이트
            for(int x=0; x<N; x++){
                for(int y=0; y<M; y++){
                    if(visited[x][y]){
                        visited[x][y] = false;
                        board[x][y] -= minusBoard[x][y];
                    }

                }
            }

            // 3. 그룹이 나뉘었는지 체크
            int group = 0;
            visited = new boolean[N][M];
            for(int x=0; x<N; x++){
                for(int y=0; y<M; y++){
                    if(board[x][y]>0 && !visited[x][y]){
                        dfs(x, y);
                        group++;
                    }

                }
            }

            if(group>1){
                System.out.println(day);
                break;
            }else if(group == 0){
                System.out.println(0);
                break;
            }

        }


    }

    private static void checkZeroCount(int x, int y){
        int minus = 0;

        for(int i=0; i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 녹아서 음수값 된것도 체크함
            if(nx>=0 && ny>=0 && nx<N && ny<M && board[nx][ny] <= 0){
                minus ++;
            }
        }

        minusBoard[x][y] = minus;
    }

    private static void dfs(int x, int y){
        visited[x][y] = true;

        for(int i=0; i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<M && board[nx][ny]>0 && !visited[nx][ny]){
                dfs(nx, ny);
            }
        }
    }
}

