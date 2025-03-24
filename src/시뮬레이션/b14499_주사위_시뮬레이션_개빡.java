package 시뮬레이션;

import java.util.*;
import java.io.*;

public class b14499_주사위_시뮬레이션_개빡 {
    private static int N, M, startX, startY, turn;
    private static int[][] board;
    private static Deque<Integer> queue = new ArrayDeque<>();
    private static int[] dx = new int[]{0, 0, 0, -1, 1};
    private static int[] dy = new int[]{0, 1, -1, 0, 0};
    private static Map<String, Integer> dice = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        turn = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<turn; i++){
            queue.add(Integer.parseInt(st.nextToken()));
        }

        // 초기 주사위
        dice.put("위", 0);
        dice.put("아래", 0);
        dice.put("동쪽", 0);
        dice.put("서쪽", 0);
        dice.put("북쪽", 0);
        dice.put("남쪽", 0);

        int x = startX;
        int y = startY;

        while(!queue.isEmpty()){
            int direction = queue.pollFirst();
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

            switch (direction){
                case 1:
                    dice = move1(dice);
                    break;
                case 2:
                    dice = move2(dice);
                    break;
                case 3:
                    dice = move3(dice);
                    break;
                case 4:
                    dice = move4(dice);
                    break;
            }

            if(board[nx][ny] == 0){
                board[nx][ny] = dice.get("아래");
            }else{
                dice.put("아래", board[nx][ny]);
                board[nx][ny] = 0;
            }

            System.out.println(dice.get("위"));
            x = nx;
            y = ny;
        }
    }

    // 동쪽
    private static Map<String, Integer> move1(Map<String, Integer> dice){
        Map<String, Integer> newDice = new HashMap<>();

        newDice.put("위", dice.get("서쪽"));
        newDice.put("아래", dice.get("동쪽"));
        newDice.put("동쪽", dice.get("위"));
        newDice.put("서쪽", dice.get("아래"));
        newDice.put("북쪽", dice.get("북쪽"));
        newDice.put("남쪽", dice.get("남쪽"));

        return newDice;
    }
    // 동쪽
    private static Map<String, Integer> move2(Map<String, Integer> dice){
        Map<String, Integer> newDice = new HashMap<>();

        newDice.put("위", dice.get("동쪽"));
        newDice.put("아래", dice.get("서쪽"));
        newDice.put("동쪽", dice.get("아래"));
        newDice.put("서쪽", dice.get("위"));
        newDice.put("북쪽", dice.get("북쪽"));
        newDice.put("남쪽", dice.get("남쪽"));

        return newDice;
    }

    // 북쪽
    private static Map<String, Integer> move3(Map<String, Integer> dice){
        Map<String, Integer> newDice = new HashMap<>();

        newDice.put("위", dice.get("남쪽"));
        newDice.put("아래", dice.get("북쪽"));
        newDice.put("동쪽", dice.get("동쪽"));
        newDice.put("서쪽", dice.get("서쪽"));
        newDice.put("북쪽", dice.get("위"));
        newDice.put("남쪽", dice.get("아래"));

        return newDice;
    }

    // 남쪽
    private static Map<String, Integer> move4(Map<String, Integer> dice){
        Map<String, Integer> newDice = new HashMap<>();

        newDice.put("위", dice.get("북쪽"));
        newDice.put("아래", dice.get("남쪽"));
        newDice.put("동쪽", dice.get("동쪽"));
        newDice.put("서쪽", dice.get("서쪽"));
        newDice.put("북쪽", dice.get("아래"));
        newDice.put("남쪽", dice.get("위"));

        return newDice;
    }
}

