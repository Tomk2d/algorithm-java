package 삼성대비빡구현;

import java.util.*;
import java.io.*;

/*
*       꽤나 난이도 있는 시뮬레이션이나 빡구현중에서 처음으로 테스트케이스에 걸리지 않았다.
*       확실히
*       1. 단계를 차분히 미리 정리
*       2. 메서드 구현하고, 그 메서드 즉각 검증 (이때 테스트 케이스나 예외 미리 만들어서 넣어보기)
*       3. 메서드 재활용 할 수 있을지 생각 (이 부분이 시간 단축)
*
*       이런 절차를 거치니까 어려운 문제도 나름 체계적으로 풀린다.
*       이 과정을 습관적으로 구성하고 연습하다 보면 시간이 절약될 것 같다.
*       코테 볼때 긴장하지말고 이러한 순서를 지키고, 문제를 열심히 읽어보자 !!!!
*
* */

public class b19238_여러최단경로
{
    private static int N, customerN, energy;
    private static int[][] board;
    private static Point taxi;
    private static List<Point> customers = new ArrayList<>();
    private static List<Point> goals = new ArrayList<>();
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        customerN = Integer.parseInt(st.nextToken());
        energy = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int taxiX = Integer.parseInt(st.nextToken())-1;
        int taxiY = Integer.parseInt(st.nextToken())-1;
        taxi = new Point(taxiX, taxiY);

        for(int i=0; i<customerN; i++){
            st = new StringTokenizer(br.readLine());
            int customerX = Integer.parseInt(st.nextToken())-1;
            int customerY = Integer.parseInt(st.nextToken())-1;
            customers.add(new Point(customerX, customerY));

            int goalX = Integer.parseInt(st.nextToken())-1;
            int goalY = Integer.parseInt(st.nextToken())-1;
            goals.add(new Point(goalX, goalY));
        }

        // ---- 시뮬레이션 시작 ------

        while(!customers.isEmpty()){
            // 1. 가까운 손님 탐색
            int[] result = bfs(customers);
            int distance = result[0];
            int customerIdx = result[1];
            int customerX = result[2];
            int customerY = result[3];

            // 2. 손님 태우기
            if(customerIdx == -1){    // 2-1. 가까운 손님 없음
                System.out.println(-1);
                return;
            }else if(distance > energy){   // 2-2. 연료 부족
                System.out.println(-1);
                return;
            }else{                      // 2-3. 손님 태우기 가능
                energy -= distance;
                customers.remove(customerIdx);
                taxi.x = customerX;
                taxi.y = customerY;
            }

            // 3. 목적지로 이동
            Point goal= goals.get(customerIdx);
            List<Point> list = new ArrayList<>();
            list.add(goal);
            int[] goalResult = bfs(list);

            // 4. 목적지 완료
            if(goalResult[1] == -1){    // 4-1. 목적지 갈 수 없음
                System.out.println(-1);
                return;
            }else if(goalResult[0] > energy){   // 4-2. 연료 부족
                System.out.println(-1);
                return;
            }else{                      // 4-3. 손님 태우기 가능
                energy += goalResult[0];
                goals.remove(customerIdx);
                taxi.x = goalResult[2];
                taxi.y = goalResult[3];
            }

        }

        System.out.println(energy);

        //System.out.println("거리 : " + result[0]+ " 손님 인덱스 : "+ result[1]+ " 위치 : (" + result[2] +" , "+ result[3] + ")");

    }
    // result : 가장 가까운 놈의 [거리, 인덱스, x, y]
    // 가능한거 없으면 = 인덱스 -1로 리턴
    private static int[] bfs(List<Point> spots){
        Deque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new Node(taxi.x, taxi.y, 0));
        visited[taxi.x][taxi.y] = true;

        int minDistance = Integer.MAX_VALUE;
        int minX = -1;
        int minY = -1;
        int minIdx = -1;

        while(!queue.isEmpty()){
            Node now = queue.pollFirst();

            // 이미 넘어섬
            if(now.cnt > minDistance){
                break;
            }

            for(int i=0; i<spots.size(); i++){
                Point spot = spots.get(i);
                // 일단 도착
                if(spot.x == now.x && spot.y == now.y){
                    if(now.cnt < minDistance){
                        minDistance = now.cnt;
                        minX = now.x;
                        minY = now.y;
                        minIdx = i;
                    }else if(now.cnt == minDistance){
                        // 더 위쪽
                        if(now.x < minX){
                            minDistance = now.cnt;
                            minX = now.x;
                            minY = now.y;
                            minIdx = i;
                            // 높이는 같은데 더 왼쪽
                        }else if(now.x == minX && now.y < minY){
                            minDistance = now.cnt;
                            minX = now.x;
                            minY = now.y;
                            minIdx = i;
                        }
                    }

                    break;
                }
            }

            for(int d=0; d<4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx>=0 && ny>=0 && nx<N && ny<N && board[nx][ny]==0 && !visited[nx][ny]){
                    queue.add(new Node(nx, ny, now.cnt+1));
                    visited[nx][ny] = true;
                }
            }

        }

        int[] result = new int[4];
        result[0] = minDistance;
        result[1] = minIdx;
        result[2] = minX;
        result[3] = minY;

        return result;
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

    private static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}

