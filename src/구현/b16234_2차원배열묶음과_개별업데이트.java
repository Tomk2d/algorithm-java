package 구현;

import java.util.*;
import java.io.*;

public class b16234_2차원배열묶음과_개별업데이트
{
    private static int N;
    private static int minCost, maxCost;
    private static int[][] board;
    private static boolean[][] visited;
    private static List<Point> group;
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        minCost = Integer.parseInt(st.nextToken());
        maxCost = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        boolean isMove = true;

        while(isMove){
            isMove = false;
            visited = new boolean[N][N];

            // 1. 묶음 찾기
            for(int x=0; x<N; x++){
                for(int y=0; y<N; y++){
                    if(!visited[x][y]){
                        group = new ArrayList<>();
                        dfs(x, y);
                        // 2. 업데이트
                        if(group.size() > 1){
                            isMove = true;
                            update(group);
                        }
                    }
                }
            }

            // 오류 검출용 프린트
            // for(int x=0; x<N; x++){
            //     for(int y=0; y<N; y++){
            //         System.out.print(board[x][y]);
            //     }
            //     System.out.println();
            // }

            if(isMove){
                day++;
            }

        }

        System.out.println(day);
    }

    private static void dfs(int x, int y){
        visited[x][y] = true;
        group.add(new Point(x, y));

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny >=0 && nx<N && ny<N && !visited[nx][ny]){
                int gap = Math.abs(board[x][y] - board[nx][ny]);
                if(gap>=minCost && gap<= maxCost){
                    dfs(nx, ny);
                }
            }
        }

    }

    private static void update(List<Point> group){
        int sum = 0;

        for(Point point : group){
            sum += board[point.x][point.y];
        }

        int avg = sum / group.size();

        for(Point point : group){
            board[point.x][point.y] = avg;
        }
    }

    private static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

