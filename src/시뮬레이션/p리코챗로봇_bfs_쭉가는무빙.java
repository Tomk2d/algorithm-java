package 시뮬레이션;

import java.util.*;
class p리코챗로봇_bfs_쭉가는무빙 {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    private char[][] charBoard;
    private int N;
    private int M;
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        charBoard = new char[N][M];

        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length(); j++){
                if(board[i].charAt(j) == 'R'){
                    startX = i;
                    startY = j;
                }else if(board[i].charAt(j) == 'G'){
                    endX = i;
                    endY = j;
                }
                charBoard[i][j] = board[i].charAt(j);
            }
        }

        answer = bfs(startX, startY, endX, endY);

        return answer;
    }

    private int bfs(int startX, int startY, int endX, int endY){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(startX, startY, -1, 0));
        boolean[][] visited = new boolean[N][M];
        visited[startX][startY] = true;

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();

            if(now.x == endX && now.y == endY){
                return now.cnt;
            }

            for(int i=0; i<4; i++){
                if(i == now.direction) continue;

                int[] result = move(now.x, now.y, i);
                int nx = result[0];
                int ny = result[1];

                if(!visited[nx][ny]){
                    queue.add(new Node(nx, ny, i, now.cnt+1));
                    visited[nx][ny] = true;
                }

            }

        }
        return -1;
    }

    private int[] move(int x, int y, int direction){
        while(true){
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx<0 || ny<0 || nx>=N || ny>=M || charBoard[nx][ny] == 'D'){
                return new int[]{x, y};
            }

            x = nx;
            y = ny;
        }
    }

    private class Node{
        int x;
        int y;
        int direction;
        int cnt;

        Node(int x, int y, int direction, int cnt){
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cnt = cnt;
        }
    }
}
