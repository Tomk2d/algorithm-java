package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b13460_시뮬레이션_빡구현
{
    private static int N, M;
    private static char[][] board;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int redX=0;
        int redY=0;
        int blueX=0;
        int blueY=0;
        for(int i=0; i<N; i++){
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                if(input[j] == 'R'){
                    board[i][j] = '.';
                    redX = i;
                    redY = j;
                }else if(input[j] == 'B'){
                    board[i][j] = '.';
                    blueX = i;
                    blueY = j;
                }else{
                    board[i][j] = input[j];
                }
            }
        }

        int answer = bfs(redX, redY, blueX, blueY);
        System.out.println(answer);

    }

    private static int bfs(int redX, int redY, int blueX, int blueY){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(redX, redY, blueX, blueY, 0));

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();

            if(now.cnt == 10) continue;

            for(int d=0; d<4; d++){
                // 빨간공
                int[] blue = findPosition(now.blueX, now.blueY, d);
                if(blue[0] == -1){  //   파란공 빠졌을때
                    continue;
                }
                int[] red = findPosition(now.redX, now.redY, d);
                if(red[0] == -1){   //  빨간공 빠졌을때
                    return now.cnt+1;
                }
                // 둘이 위치 같을때, 하나 덜가야함
                if(red[0] == blue[0] && red[1] == blue[1]){
                    if(d == 0){         // 우. y 더 작은놈이 하나 덜가
                        if(now.redY < now.blueY){
                            red[1] --;
                        }else{
                            blue[1] --;
                        }
                    }else if(d == 1){   // 좌. y 큰놈이 하나 덜가
                        if(now.redY < now.blueY){
                            blue[1] ++;
                        }else{
                            red[1] ++;
                        }
                    }else if(d == 2){   // 하. x 작은놈이 하나 덜가
                        if(now.redX < now.blueX){
                            red[0] --;
                        }else{
                            blue[0] --;
                        }
                    }else if(d == 3){   // 상. x 큰놈이 하나 덜가
                        if(now.redX < now.blueX){
                            blue[0] ++;
                        }else{
                            red[0] ++;
                        }
                    }
                }

                queue.add(new Node(red[0], red[1], blue[0], blue[1], now.cnt+1));

            }

        }
        return -1;

    }

    private static int[] findPosition(int x, int y, int d){
        int[] result = new int[2];

        int nx = x;
        int ny = y;

        while(true){
            nx = nx + dx[d];
            ny = ny + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                break;
            }

            if(board[nx][ny] == '#'){
                result[0] = nx - dx[d];
                result[1] = ny - dy[d];
                break;
            }else if(board[nx][ny] == 'O'){
                result[0] = -1;
                result[1] = -1;
                break;
            }
        }
        return result;
    }

    private static class Node{
        int redX, redY;
        int blueX, blueY;
        int cnt;

        Node(int redX, int redY, int blueX, int blueY, int cnt){
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.cnt = cnt;
        }
    }
}

