package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class b21611_달팽이하드모드 {
    private static int N, turn;
    private static int[][] board;
    private static Point shark;
    private static int[][] orders;
    private static int[] dx = {0, 1, 0, -1};   // 좌 하 우 상
    private static int[] dy = {-1, 0, 1, 0};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        turn = Integer.parseInt(st.nextToken());

        shark = new Point((int) N/2, (int) N/2);

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        orders = new int[turn][2];
        for(int i=0; i<turn; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            // 인덱스에 맞게 변환
            if(d == 1)      d = 3;
            else if(d == 2) d = 1;
            else if(d == 3) d = 0;
            else if(d == 4) d = 2;

            int distance = Integer.parseInt(st.nextToken());

            orders[i][0] = d;
            orders[i][1] = distance;
        }

        for(int t=0; t<turn; t++){
            int[] order = orders[t];
            // 1. 공격하기
            attack(order[0], order[1]);

            // 2. 보드 채우고, 다시 4개 이상의 구슬 폭파하고, 채우기
            while(true){
                int result = fillBoardAndBomb();
                if(result == 0) break;
                answer += result;
            }

            // 3. 구슬의 변화
            transformBoard();
        }
        System.out.println(answer);
    }

    private static void transformBoard() {
        Deque<Integer> sequence = new ArrayDeque<>();

        // 1단계: board -> 구슬 번호 나선형 추출
        int d = 0;
        int cnt = 1;
        int nx = shark.x;
        int ny = shark.y;

        while (cnt <= N) {
            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny) && board[nx][ny] != 0) {
                    sequence.add(board[nx][ny]);
                }
            }
            d = (d + 1) % 4;

            if (cnt == N) break;

            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny) && board[nx][ny] != 0) {
                    sequence.add(board[nx][ny]);
                }
            }
            d = (d + 1) % 4;

            cnt++;
        }

        // 2단계: 같은 숫자 그룹 묶기 -> [count, number] 쌍 생성
        Deque<Integer> changed = new ArrayDeque<>();
        List<Integer> temp = new ArrayList<>();

        while (!sequence.isEmpty()) {
            int cur = sequence.poll();

            if (temp.isEmpty() || temp.get(0) == cur) {
                temp.add(cur);
            } else {
                changed.add(temp.size());      // 그룹 크기
                changed.add(temp.get(0));      // 그룹 숫자
                temp.clear();
                temp.add(cur);
            }
        }

        // 마지막 그룹 처리
        if (!temp.isEmpty()) {
            changed.add(temp.size());
            changed.add(temp.get(0));
        }

        // 3단계: board 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], 0);
        }

        // 4단계: changed 값을 나선형 순서로 board 에 다시 채움
        d = 0;
        cnt = 1;
        nx = shark.x;
        ny = shark.y;

        while (cnt <= N) {
            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny)) {
                    board[nx][ny] = changed.isEmpty() ? 0 : changed.poll();
                }
            }
            d = (d + 1) % 4;

            if (cnt == N) break;

            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny)) {
                    board[nx][ny] = changed.isEmpty() ? 0 : changed.poll();
                }
            }
            d = (d + 1) % 4;

            cnt++;
        }
    }


    private static int fillBoardAndBomb() {
        int result = 0;
        Deque<Integer> rawQueue = new ArrayDeque<>();

        // 1단계: 기존 보드에서 나선형 순서로 0이 아닌 값만 큐에 저장
        int d = 0;
        int cnt = 1;
        int nx = shark.x;
        int ny = shark.y;

        while (cnt <= N) {
            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny) && board[nx][ny] != 0) rawQueue.add(board[nx][ny]);
            }
            d = (d + 1) % 4;

            if (cnt == N) break;

            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny) && board[nx][ny] != 0) rawQueue.add(board[nx][ny]);
            }
            d = (d + 1) % 4;

            cnt++;
        }

        // 2단계: 4개 이상 연속된 숫자는 제거하고, 점수 추가. 나머지만 newQueue에 넣기
        Deque<Integer> newQueue = new ArrayDeque<>();
        List<Integer> temp = new ArrayList<>();

        while (!rawQueue.isEmpty()) {
            int cur = rawQueue.poll();

            if (temp.isEmpty() || temp.get(0) == cur) {
                temp.add(cur);
            } else {
                if (temp.size() >= 4) {
                    result += temp.size() * temp.get(0); // 점수 누적
                } else {
                    newQueue.addAll(temp); // 3개 이하만 유지
                }
                temp.clear();
                temp.add(cur);
            }
        }

        // 마지막 그룹 처리
        if (!temp.isEmpty()) {
            if (temp.size() >= 4) {
                answer += temp.size() * temp.get(0);
            } else {
                newQueue.addAll(temp);
            }
        }


        // 4단계: newQueue 값으로 나선형 순서로 board 채우기
        d = 0;
        cnt = 1;
        nx = shark.x;
        ny = shark.y;

        while (cnt <= N) {
            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny)) {
                    board[nx][ny] = newQueue.isEmpty() ? 0 : newQueue.poll();
                }
            }
            d = (d + 1) % 4;

            if (cnt == N) break;

            for (int j = 1; j <= cnt; j++) {
                nx += dx[d];
                ny += dy[d];
                if (inBoard(nx, ny)) {
                    board[nx][ny] = newQueue.isEmpty() ? 0 : newQueue.poll();
                }
            }
            d = (d + 1) % 4;

            cnt++;
        }
        return result;
    }




    private static void attack(int d, int distance){
        int nx = shark.x;
        int ny = shark.y;

        for(int i=0; i<distance; i++){
            nx = nx + dx[d];
            ny = ny + dy[d];

            if(inBoard(nx, ny)){
                board[nx][ny] = 0;
            }
        }
    }

    private static boolean inBoard(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    private static void print(){
        System.out.println("===================");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

