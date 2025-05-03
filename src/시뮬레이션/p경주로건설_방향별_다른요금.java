package 시뮬레이션;

import java.util.*;
/*
* 이런 문제는 지금까지 진행된 cost 를 저장하면서 가야한다
* 처음에는 2차원 배열로 문제를 풀었는데, 일부 부분에서 맞지 않았다.
* 이유는 결국 bfs 형식으로 로직을 짯는데, 다음 cost 를 업데이트 할때,
* 다음 cost = 이전 cost + 추가비용
* 으로 계산했다. 하지만 이 경우, 다른 방향에서 오는 노드에 의해서, 이전 값이 갱신될 수 있다.
* 그렇기 때문에 이전 방향에서 올때의 최소값을 기준으로 저장해야한다.
* 3차원 배열로 하여, 방향 부분을 추가했다.
* */

class p경주로건설_방향별_다른요금 {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[][] board;

    public int solution(int[][] board) {
        this.board = board;
        return bfs(board.length);
    }

    private int bfs(int N) {
        int[][][] dp = new int[4][N][N];
        for (int dir = 0; dir < 4; dir++) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[dir][i], Integer.MAX_VALUE);
            }
        }

        Deque<Node> queue = new ArrayDeque<>();

        for (int i = 0; i < 4; i++) {
            int nx = dx[i];
            int ny = dy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < N && board[nx][ny] == 0) {
                dp[i][nx][ny] = 100;
                queue.add(new Node(nx, ny, i, 100));
            }
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && board[nx][ny] == 0) {
                    int cost = now.cost + (now.preDirection == i ? 100 : 600);

                    if (dp[i][nx][ny] > cost) {
                        dp[i][nx][ny] = cost;
                        queue.add(new Node(nx, ny, i, cost));
                    }
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            minCost = Math.min(minCost, dp[i][N - 1][N - 1]);
        }
        return minCost;
    }

    private class Node {
        int x, y, preDirection, cost;

        Node(int x, int y, int preDirection, int cost) {
            this.x = x;
            this.y = y;
            this.preDirection = preDirection;
            this.cost = cost;
        }
    }
}

/*
*   이전 오답 코드
* */
//import java.util.*;
//class Solution {
//    int[] dx = {1, -1, 0, 0};
//    int[] dy = {0, 0, 1, -1};
//    int[][] board;
//    public int solution(int[][] board) {
//        this.board = board;
//
//        return bfs(board.length);
//    }
//    private int bfs(int N){
//        int[][] dp = new int[N][N];
//        for (int i = 0; i < N; i++) {
//            Arrays.fill(dp[i], Integer.MAX_VALUE);
//        }
//        dp[0][0] = 0;
//        Deque<Node> queue = new ArrayDeque<>();
//        queue.add(new Node(0, 0, 1));
//        queue.add(new Node(0, 0, 2));
//
//        while(!queue.isEmpty()){
//            Node now = queue.pollFirst();
//
//            if(now.x == N-1 && now.y == N-1){
//                continue;
//            }
//
//            for(int i=0; i<4; i++){
//                int nx = now.x + dx[i];
//                int ny = now.y + dy[i];
//
//                if(nx>=0 && ny>=0 && nx<N && ny<N && board[nx][ny]==0){
//                    int nextCost = dp[now.x][now.y];
//                    if(i != now.preDirection){
//                        nextCost += 600;
//                    }else{
//                        nextCost += 100;
//                    }
//                    if(nextCost < dp[nx][ny]){
//                        dp[nx][ny] = nextCost;
//                        queue.add(new Node(nx, ny, i));
//                    }
//                }
//            }
//        }
//        print(dp);
//        return dp[N-1][N-1];
//
//    }
//    private void print(int[][] dp){
//        for(int[] b : dp){
//            for(int n : b){
//                System.out.print(n + " ");
//            }
//            System.out.println();
//        }
//    }
//    private class Node{
//        int x;
//        int y;
//        int preDirection;
//        Node(int x, int y, int preDirection){
//            this.x = x;
//            this.y = y;
//            this.preDirection = preDirection;
//        }
//    }
//}