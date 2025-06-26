package 삼성대비빡구현;

import java.util.*;
import java.io.*;

/*
*       매번 느끼는 거지만 무조건 작은 단위라도 분할해서 메서드화 하는게 중요하다
*       또한 설계 사항을 빼곡히 적고, 이를 구현해야 한다.
*       이 메서드들을 모듈 단위로 그때마다, 테스트케이스를 반드시 넣어봐야 한다. 검증을 반드시 그 단계에서 마치자
*       !!! 무엇보다 시간을 줄이기 위해, 회전이나 중력 등은 반드시 암기하자 !!!!!
*
* */
public class b21609_빡구현_반시계회전_중력_그룹선택 {
    private static int N, M;
    private static int[][] board;       // -2 : 빈칸, -1 : 블랙, 0 : 무지개, 자연수 : 일반 블럭
    private static boolean[][] visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static List<Point> selectedGroup;
    private static int selectedGroupRainbow;
    private static int selectedGroupX, selectedGroupY;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0. auto 플레이
        while(true){
            visited = new boolean[N][N];
            selectedGroup = new ArrayList<>();
            selectedGroupRainbow = 0;
            selectedGroupX = -1;
            selectedGroupY = -1;

            // 1. 블록 그룹 선정
            for(int x=0; x<N; x++){
                for(int y=0; y<N; y++){
                    // 여기서 선정된 x, y 가 기준 블록
                    if(board[x][y] > 0 && !visited[x][y]){
                        selectGroup(x, y);
                    }
                }
            }

            // 종료조건. 터트릴거 없을때
            if(selectedGroup.size() <= 0) break;

            // 2. 선정된 그룹 터뜨리기 & 점수 얻기.
            answer += (selectedGroup.size() * selectedGroup.size());
            bombBlock();

            // print();

            // 3. 중력작용
            moveDown();

            // print();

            // 4. 반시계 90도 회전
            turnBoard();

            // print();

            // 5. 중력작용
            moveDown();

            // print();
        }


        // for(Point p : makeGroup(1, 0)){
        //     System.out.println("(" +p.x + " , " + p.y + ")");
        // }

        System.out.println(answer);
    }

    private static void turnBoard() {
        int[][] rotated = new int[N][N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                rotated[N - 1 - x][y] = board[y][x];
            }
        }

        board = rotated;
    }


    private static void moveDown() {
        for (int x = 0; x < N; x++) {  // 각 열마다
            for (int y = N - 1; y >= 0; y--) {  // 아래에서 위로
                if (board[y][x] >= 0) {
                    int ny = y;
                    while (true) {
                        if (ny + 1 >= N) break;
                        if (board[ny + 1][x] != -2) break; // 빈 칸 아니면 멈춤

                        // swap
                        board[ny + 1][x] = board[ny][x];
                        board[ny][x] = -2;
                        ny++;
                    }
                }
            }
        }
    }


    // 빈칸을 -2 로 표시
    private static void bombBlock(){
        for(Point block : selectedGroup){
            board[block.x][block.y] = -2;
        }
        return;
    }

    private static void selectGroup(int x, int y){
        // x, y 가 기준 블록
        List<Point> group = makeGroup(x, y);

        // 1. 그룹 블록 수 2개 미만
        if(group.size() < 2) return;

        // 2. 그룹 블록 수 기존보다 작을때
        if(group.size() < selectedGroup.size()) return;

        int rainbow = 0;
        for(Point p : group){
            if(board[p.x][p.y] == 0) rainbow ++;
        }

        // 3. 기존꺼 보다 클때. 무조건 선택
        if(group.size() > selectedGroup.size()){
            selectedGroup = group;
            selectedGroupRainbow = rainbow;
            selectedGroupX = x;
            selectedGroupY = y;
            return;
        }

        // 4. 크기 같을때
        if(group.size() == selectedGroup.size()){
            // 4-1. 무지개 더 많을때
            if(rainbow > selectedGroupRainbow){
                selectedGroup = group;
                selectedGroupRainbow = rainbow;
                selectedGroupX = x;
                selectedGroupY = y;
                return;
                // 4-2. 무지개도 같을때
            }else if(rainbow == selectedGroupRainbow){
                // 4-2-1. 행이 더 클때
                if(x > selectedGroupX){
                    selectedGroup = group;
                    selectedGroupRainbow = rainbow;
                    selectedGroupX = x;
                    selectedGroupY = y;
                    return;
                    // 4-2-2. 열이 더 클때
                }else if(x == selectedGroupX){
                    if(y > selectedGroupY){
                        selectedGroup = group;
                        selectedGroupRainbow = rainbow;
                        selectedGroupX = x;
                        selectedGroupY = y;
                        return;
                    }
                }
            }
        }
    }

    private static List<Point> makeGroup(int startX, int startY){
        List<Point> result = new ArrayList<>();
        boolean[][] tempVisited = new boolean[N][N]; // 무지개 포함 그룹 내 임시 방문 체크

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startX, startY));
        tempVisited[startX][startY] = true;
        visited[startX][startY] = true;

        int color = board[startX][startY];

        while(!queue.isEmpty()){
            Point now = queue.pollFirst();
            result.add(now);

            for(int d=0; d<4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(inBox(nx, ny) && board[nx][ny] != -1 && board[nx][ny] != -2 && !tempVisited[nx][ny]){
                    // 무지개 블록
                    if(board[nx][ny] == 0){
                        tempVisited[nx][ny] = true; // 여기 중요
                        queue.add(new Point(nx, ny));
                    }
                    // 일반 블록 (같은 색)
                    else if(board[nx][ny] == color && !visited[nx][ny]){
                        tempVisited[nx][ny] = true;
                        visited[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }

        return result;
    }


    private static boolean inBox(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    private static void print(){
        System.out.println();
        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                System.out.print(board[x][y] + " ");
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

