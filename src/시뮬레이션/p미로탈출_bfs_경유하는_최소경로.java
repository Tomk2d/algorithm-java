package 시뮬레이션;

import java.util.*;
class p미로탈출_bfs_경유하는_최소경로 {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    private char[][] board;
    private int startX, startY, labberX, labberY, endX, endY;
    public int solution(String[] maps) {
        int answer = 0;

        board = new char[maps.length][maps[0].length()];

        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[0].length(); j++){
                char c = maps[i].charAt(j);
                if(c == 'S'){
                    startX = i;
                    startY = j;
                }else if(c == 'L'){
                    labberX = i;
                    labberY = j;
                }else if(c == 'E'){
                    endX = i;
                    endY = j;
                }
                board[i][j] = c;
            }
        }

        int num1 = bfs(startX, startY, labberX, labberY);
        int num2 = bfs(labberX, labberY, endX, endY);

        if(num1 < 0 || num2 < 0){
            return -1;
        }

        return num1 + num2;
    }

    private int bfs(int firstX, int firstY, int goalX, int goalY){
        Deque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        queue.add(new Node(firstX, firstY, 0));
        visited[firstX][firstY] = true;

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();

            if(now.x == goalX && now.y == goalY){
                return now.cnt;
            }

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx>=0 && ny>=0 && nx<board.length && ny<board[0].length && !visited[nx][ny] && board[nx][ny]!='X'){
                    queue.add(new Node(nx, ny, now.cnt+1));
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }

    private class Node{
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}