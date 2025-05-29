package 삼성대비빡구현;

import java.util.*;
import java.io.*;

public class samsung_2022_1_1 {
    private static int[][] map;     // N x N
    private static int N, playerN, k;   // N, 사람수, 게임 턴수
    private static int[] dx = {-1, 0, 1, 0};       // 상 우 하 좌
    private static int[] dy = {0, 1, 0, -1};
    private static List<List<PriorityQueue<Integer>>> gunBoard = new ArrayList<>();
    private static List<Player> players = new ArrayList<>();
    private static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        playerN = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = new int[playerN];

        for(int i=0; i<N; i++){
            gunBoard.add(new ArrayList<>());
            for(int j=0; j<N; j++){
                gunBoard.get(i).add(new PriorityQueue<>(Comparator.reverseOrder()));
            }
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int n = Integer.parseInt(st.nextToken());
                if(n != 0){
                    gunBoard.get(i).get(j).add(n);
                }
            }
        }

        for(int i=0; i<playerN; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());

            players.add(new Player(x, y, d, power, 0));
        }

        while(k--> 0){
            for(int i=0; i<playerN; i++){
                Player now = players.get(i);
                // 1. 플레이어 이동.
                playerMove(now);   // {nx, ny}

                // 2. 총먹기
                int enemyIdx = checkAnotherPlayer(i);
                if(enemyIdx == -1){     // 적이 없음. 총먹기
                    getGun(i);
                }else{                  // 적이 있음.
                    int[] result = battle(i, enemyIdx);
                    int losePlayerIdx = result[1];
                    moveLosePlayer(losePlayerIdx);          // 패자 이동

                    int winPlayerIdx = result[0];
                    getGun(winPlayerIdx);                   // 승자 총 얻기

                    getGun(losePlayerIdx);                  // 패자 총 얻기

                }
            }

        }

        for(int n : answer){
            System.out.print(n + " ");
        }

    }

    private static void moveLosePlayer(int idx){
        Player now = players.get(idx);
        // 총 내려놓기
        gunBoard.get(now.x).get(now.y).add(now.gunPower);
        now.gunPower = 0;

        // 한칸 밀려나기
        for(int i=0; i<4; i++){
            int nextD = (now.d + i) % 4;
            int nx = now.x + dx[nextD];
            int ny = now.y + dy[nextD];

            // 나가지 않을때.
            if(outCheck(nx, ny)){
                now.x = nx;
                now.y = ny;
                int anotherPlayer = checkAnotherPlayer(idx);
                if(anotherPlayer == -1){        // 빈칸일때
                    now.d = nextD;
                    return;
                }else{
                    now.x -= dx[nextD];
                    now.y -= dy[nextD];
                }
            }

        }
    }

    // return = {승자 idx, 패자 idx}
    private static int[] battle(int aIdx, int bIdx){
        int[] result = new int[2];
        Player playerA = players.get(aIdx);
        Player playerB = players.get(bIdx);
        int sumPowerA = playerA.power + playerA.gunPower;
        int sumPowerB = playerB.power + playerB.gunPower;

        if(sumPowerA > sumPowerB){
            answer[aIdx] += sumPowerA - sumPowerB;
            result[0] = aIdx;
            result[1] = bIdx;
            return result;
        }else if(sumPowerB > sumPowerA){
            answer[bIdx] += sumPowerB - sumPowerA;
            result[0] = bIdx;
            result[1] = aIdx;
            return result;
        }else if(sumPowerA == sumPowerB){
            if(playerA.power > playerB.power){
                result[0] = aIdx;
                result[1] = bIdx;
                return result;
            }else{
                result[0] = bIdx;
                result[1] = aIdx;
                return result;
            }
        }
        return result;
    }

    private static void getGun(int i){
        Player now = players.get(i);
        PriorityQueue<Integer> guns = gunBoard.get(now.x).get(now.y);
        // 총이 없을때
        if(guns.size() == 0) return;

        // 보유한 총 없을때
        if(now.gunPower == 0){
            now.gunPower = guns.poll();
            return;
        }
        // 보유한 총보다 쌘총 있을때
        if(now.gunPower < guns.peek()){
            // 총내려 놓기
            guns.add(now.gunPower);
            // 총 획득
            now.gunPower = guns.poll();
            return;
        }
    }

    // 아무도 없을때 : -1, 있을때 : 상대방 idx
    private static int checkAnotherPlayer(int nowIdx){
        Player now = players.get(nowIdx);

        for(int i=0; i<playerN; i++){
            if(i == nowIdx) continue;       // 자기자신 제외
            Player enemy = players.get(i);

            if(now.x == enemy.x && now.y == enemy.y){
                return i;
            }
        }
        return -1;
    }

    // return : {nx, ny}
    private static void playerMove(Player now){
        int nx = now.x + dx[now.d];
        int ny = now.y + dy[now.d];

        if(outCheck(nx, ny)){
            now.x = nx;
            now.y = ny;

            return;
        }

        // 아웃일때. 다음 반대방향으로 이동
        int nextD = (now.d+2) % 4;
        nx = now.x + dx[nextD];
        ny = now.y + dy[nextD];

        now.x = nx;
        now.y = ny;
        now.d = nextD;

        return;
    }

    // false : out
    private static boolean outCheck(int x, int y){
        return x>=0 && y>=0 && x<N && y<N;
    }

    private static void printPlayer(){
        System.out.println("=======================");
        int i=0;
        for(Player now : players){
            System.out.println(i + "번째 : (" +now.x + " , " + now.y + ") " + now.d + " , " + now.power);
            i++;
        }
    }

    private static class Player{
        int x;
        int y;
        int d;
        int power;
        int gunPower;

        Player(int x, int y, int d, int power, int gunPower){
            this.x = x;
            this.y = y;
            this.d = d;
            this.power = power;
            this.gunPower = gunPower;
        }
    }
}
