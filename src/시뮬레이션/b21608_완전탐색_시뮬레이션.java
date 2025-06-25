package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b21608_완전탐색_시뮬레이션
{
    private static int N;
    private static int[][] board;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        Deque<int[]> queue = new ArrayDeque<>();

        for(int i=0; i<N*N; i++){
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[5];

            for(int j=0; j<5; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }

            queue.add(arr);
        }

        Map<Integer, int[]> likeMap = new HashMap<>();
        while(!queue.isEmpty()){
            int[] now = queue.pollFirst();
            findSeat(now);

            likeMap.put(now[0], new int[]{now[0], now[1], now[2], now[3], now[4]});
        }

        int answer = 0;
        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                int[] result = likeAndBlank(x, y, likeMap.get(board[x][y]));

                if(result[0] == 4) answer += 1000;
                else if(result[0] == 3) answer += 100;
                else if(result[0] == 2) answer += 10;
                else if(result[0] == 1) answer += 1;
            }
        }

        System.out.println(answer);

        //System.out.println("==========출력==========");
        //print();
    }

    private static void findSeat(int[] array){
        int likePeople = -1;
        int blank = -1;
        int seatX=-1, seatY=-1;

        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                if(board[x][y] == 0){
                    int[] result = likeAndBlank(x, y, array);
                    if(result[0] > likePeople){
                        likePeople = result[0];
                        blank = result[1];
                        seatX = x;
                        seatY = y;
                    }else if(result[0] == likePeople && result[1] > blank){
                        likePeople = result[0];
                        blank = result[1];
                        seatX = x;
                        seatY = y;
                    }
                }
            }
        }

        board[seatX][seatY] = array[0];
        return;
    }

    private static int[] likeAndBlank(int x, int y, int[] array){
        int blank = 0;
        int likePeople = 0;

        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

            // 빈칸 찾기
            if(board[nx][ny] == 0) blank ++;
                // 선호 학생 찾기
            else{
                for(int i=1; i<5; i++){
                    if(board[nx][ny] == array[i]){
                        likePeople ++;
                        break;
                    }
                }
            }

        }

        return new int[]{likePeople, blank};
    }

    private static void print(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

