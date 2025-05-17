package 삼성대비빡구현;

import java.util.*;
import java.io.*;

/*
*       문제를 참 잘읽어야 한다.
*       근데 이 문제는 좀 설명이 불친절하긴 했다
*       모든 빈칸을 전염 시켜야 하지만, 비활성 바이러스도 전염시킬 수 있다.
*       하지만 비활성 바이러스는 전부 전염되지 않은 상태에서, 빈칸이 모두 전염됐다면 최소경로이다.
*       즉,
*       빈칸 : 필수조건
*       비활성칸 : 선택조건. 한개도 전염 안되어도 되지만, 경로로 쓰기는 가능
*
*       그래서 빈칸이 전염된 순간만 카운트하여 반영하고, 모든 빈칸이 전염되었는지도 체크해야 한다.
* */

public class b17142_백트래킹조합과_최소경로
{
    private static int N, maxVirus;
    private static int[][] board;       //  0 : 빈칸, -1 : 벽, -2 : 비활성 바이러스
    private static List<Virus> viruses = new ArrayList<>();
    private static int answer = Integer.MAX_VALUE;

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        maxVirus = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());

                if(num == 2){
                    board[i][j] = -2;
                    viruses.add(new Virus(i, j));
                }else if(num == 1){
                    board[i][j] = -1;
                }else if(num == 0){
                    board[i][j] = 0;
                }
            }
        }

        dfs(0, new LinkedList<>());

        System.out.println(answer==Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dfs(int idx, LinkedList<Integer> combi){
        if(combi.size() == maxVirus){
            bfs(combi);
            return;
        }

        for(int i=idx; i<viruses.size(); i++){
            combi.add(i);
            dfs(i+1, combi);
            combi.removeLast();
        }
    }

    private static void bfs(LinkedList<Integer> combi) {
        Deque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int[][] copyBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            copyBoard[i] = board[i].clone();
        }

        for (int idx : combi) {
            Virus v = viruses.get(idx);
            queue.add(new Node(v.x, v.y, 0));
            visited[v.x][v.y] = true;
        }

        int maxTime = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (copyBoard[nx][ny] == -1) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, now.cnt + 1));

                // 여기가 핵심 !!!!!
                // 빈 칸인 경우에만 시간 추적
                if (board[nx][ny] == 0) {
                    maxTime = Math.max(maxTime, now.cnt + 1);
                }
            }
        }

        // 여기도 핵심 !!
        // 모든 빈 칸이 visited 되었는지 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0 && !visited[i][j]) {
                    return;   // 빈 칸 중 방문하지 않은 곳 있음 → 실패
                }
            }
        }

        answer = Math.min(answer, maxTime);
    }


    private static void print(int[][] board){
        System.out.println();
        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }

    private static class Node{
        int x;
        int y;
        int cnt;

        Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    private static class Virus{
        int x;
        int y;

        Virus(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

