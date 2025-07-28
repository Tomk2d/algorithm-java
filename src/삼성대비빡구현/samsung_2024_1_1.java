package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class samsung_2024_1_1 {
    private static int N = 5;
    private static int K, M;        // 턴, 조각 유물 개수
    private static int[][] board = new int[5][5];
    private static Deque<Integer> nextBlocks = new ArrayDeque<>();
    private static List<Integer> answer = new ArrayList<>();
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static List<Point> bombBlocks = new ArrayList<>();
    private static int bombCnt, bombTurn, bombX, bombY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            nextBlocks.add(Integer.parseInt(st.nextToken()));
        }
        // bombCnt = 0;
        // bombTurn = Integer.MAX_VALUE;
        // bombX = Integer.MAX_VALUE;
        // bombY = Integer.MAX_VALUE;
        // turnBlock(1, 1);
        // System.out.println(bombCnt + " , " + bombTurn + " , " + bombX + " , " + bombY);

        while(K--> 0){
            int wholePoint = 0;
            bombBlocks = new ArrayList<>();
            bombCnt = 0;
            bombTurn = Integer.MAX_VALUE;
            bombX = Integer.MAX_VALUE;
            bombY = Integer.MAX_VALUE;

            // 1. 최대 경우의 수 추출
            for(int i=0; i<=2; i++){
                for(int j=0; j<=2; j++){
                    turnBlock(i, j);
                }
            }

            // 2. 해당 최대 수만큼 보드 회전
            updateTurnBoard(bombX, bombY, bombTurn);

            // 3. 터뜨리기
            wholePoint += bombUpdateBoard();


            if(wholePoint == 0) break;
            else answer.add(wholePoint);
        }
        for(int n : answer){
            System.out.print(n + " ");
        }
    }
    // 터뜨린 개수
    private static int bombUpdateBoard(){
        int result = 0;
        while(true){
            List<Point> bombs = findRemoveBlock(board);

            if(bombs.size() == 0) return result;

            // 터뜨리기
            for(Point now : bombs){
                board[now.x][now.y] = 0;
                result ++;
            }

            // 채우기
            for(int j=0; j<5; j++){
                for(int i=4; i>=0; i--){
                    if(board[i][j] == 0){
                        if(nextBlocks.isEmpty()) break;
                        int next = nextBlocks.pollFirst();
                        board[i][j] = next;
                    }
                }
            }

        }
    }

    private static void updateTurnBoard(int startX, int startY, int turn) {
        int[][] copyBlock = new int[3][3];

        // 1. 현재 3x3 블록 복사
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copyBlock[i][j] = board[startX + i][startY + j];
            }
        }

        // 2. turn 횟수만큼 회전 (시계 방향)
        turn %= 4; // 4번 돌면 제자리
        for (int t = 0; t < turn; t++) {
            int[][] temp = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = copyBlock[i][j];
                }
            }
            copyBlock = temp;
        }

        // 3. 회전된 블록을 보드에 반영
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[startX + i][startY + j] = copyBlock[i][j];
            }
        }
    }


    // 3 방향이 1묶음
    private static void turnBlock(int startX, int startY) {
        int[][] copyBoard = new int[5][5];
        int[][] copyBlock = new int[3][3];

        // 1. 원본 전체 복사
        for (int i = 0; i < 5; i++) {
            copyBoard[i] = board[i].clone();
        }

        // 2. 초기 3x3 블록 추출 (회전 시작 상태)
        for (int i = 0; i < 3; i++) {
            System.arraycopy(copyBoard[startX + i], startY + 0, copyBlock[i], 0, 3);
        }

        int maxSize = 0;
        int maxTurn = -1;
        List<Point> maxList = new ArrayList<>();

        // 3. 누적 회전 수행 (90°, 180°, 270° 순)
        for (int turn = 1; turn <= 3; turn++) {
            // 3-1. copyBlock 회전 → 새로운 블록에 저장
            int[][] rotated = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rotated[j][2 - i] = copyBlock[i][j];
                }
            }

            // 3-2. 회전 결과 copyBoard에 반영
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    copyBoard[startX + i][startY + j] = rotated[i][j];
                }
            }

            // 3-3. copyBlock을 갱신하여 누적 회전 가능하게
            copyBlock = rotated;

            List<Point> result = findRemoveBlock(copyBoard);
            if(result.size() > maxSize){
                maxSize = result.size();
                maxTurn = turn;
                maxList = new ArrayList<>();
                for(Point p : result){
                    maxList.add(p);
                }
            }

            // System.out.println(turn + "번째 턴 =========");
            // System.out.println(result);
        }

        boolean isChange = false;

        if(maxSize > bombCnt){
            isChange = true;
        }else if(maxSize == bombCnt){
            if(maxTurn < bombTurn){
                isChange = true;
            }else if(maxTurn == bombTurn){
                if(startY < bombY){
                    isChange = true;
                }else if(startY == bombY){
                    if(startX < bombX){
                        isChange = true;
                    }
                }
            }
        }

        if(isChange){
            bombBlocks = maxList;
            bombCnt = maxSize;
            bombTurn = maxTurn;
            bombX = startX;
            bombY = startY;
        }
    }

    // return : 터진 블럭 list
    private static List<Point> findRemoveBlock(int[][] b) {
        boolean[][] visited = new boolean[5][5];
        Deque<Point> queue = new ArrayDeque<>();
        List<Point> result = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) continue;

                List<Point> list = new ArrayList<>();
                queue.add(new Point(i, j));
                visited[i][j] = true;
                list.add(new Point(i, j));

                while (!queue.isEmpty()) {
                    Point now = queue.pollFirst();

                    for (int d = 0; d < 4; d++) {
                        int nx = now.x + dx[d];
                        int ny = now.y + dy[d];

                        if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 &&
                            !visited[nx][ny] &&
                            b[nx][ny] == b[now.x][now.y]) {

                            queue.add(new Point(nx, ny));
                            visited[nx][ny] = true;
                            list.add(new Point(nx, ny));
                        }
                    }
                }

                if (list.size() >= 3) {
                    result.addAll(list);
                }
            }
        }
        return result;
    }


    private static void print(int[][] b){
        System.out.println("=============================");
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
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
