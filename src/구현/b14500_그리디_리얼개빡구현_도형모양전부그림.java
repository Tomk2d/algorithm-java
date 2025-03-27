package 구현;

import java.util.*;
import java.io.*;

// 그냥 나올수 있는 모든 경우의 도형모양을 그리고, 직접 넣었다.
// 저 block 클래스를 구현하는게 살짝 헷갈렸지만,
// 생성자를 통해서 웬만한 통합과 매핑을 진행했다.
// 인스턴스 생성시에는 딱 보기 편하게 인자만 넣어주게 구성하자 !

public class b14500_그리디_리얼개빡구현_도형모양전부그림
{
    private static int N, M;
    private static int[][] board;
    private static List<Block> blockList = new ArrayList<>();
    private static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        // input
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 사각형
        blockList.add(new Block(0,0, 0,1, 1,0, 1,1));
        // 길쭉이
        blockList.add(new Block(0,0, 0,1, 0,2, 0,3));
        blockList.add(new Block(0,0, 1,0, 2,0, 3,0));
        // 뻐큐
        blockList.add(new Block(-1,-1, 0,-1, 1,-1, 0,0));
        blockList.add(new Block(0,0, 1,-1, 1,0, 1,1));
        blockList.add(new Block(0,0, -1,1, 0,1, 1,1));
        blockList.add(new Block(-1,-1, -1,0, -1,1, 0,0));
        // 번개
        blockList.add(new Block(0,0, 1,0, 1,1, 2,1));
        blockList.add(new Block(0,0, 1,-1, 1,0, 2,-1));
        blockList.add(new Block(0,0, 0,1, -1,1, -1,2));
        blockList.add(new Block(-1,-2, -1,-1, 0,-1, 0,0));
        // 니은
        blockList.add(new Block(0,0, -1,0, -1,1, -1,2));
        blockList.add(new Block(0,0, 0,1, 1,1, 2,1));
        blockList.add(new Block(1,-2, 1,-1, 1,0, 0,0));
        blockList.add(new Block(-2,-1, -1,-1, 0,-1, 0,0));
        // 기억
        blockList.add(new Block(-1,-2, -1,-1, -1,0, 0,0));
        blockList.add(new Block(-2,1, -1,1, 0,1, 0,0));
        blockList.add(new Block(0,0, 1,0, 1,1, 1,2));
        blockList.add(new Block(0,0, 0,-1, 1,-1, 2,-1));

        for(int x=0; x<N; x++){
            for(int y =0; y<M; y++){
                // 블록별로 검사
                for(Block block : blockList){
                    calculateBlock(block, x, y);
                }
            }
        }
        System.out.println(maxSum);
    }

    private static void calculateBlock(Block block, int x, int y){
        int sum = 0;
        for(int[] part : block.parts){
            int nx = x + part[0];
            int ny = y + part[1];

            if(check(nx, ny)){
                sum += board[nx][ny];
            }else{
                return;
            }
        }

        maxSum = Math.max(maxSum, sum);
    }

    private static boolean check(int x, int y){
        return x>=0 && y>=0 && x<N && y<M;
    }

    private static class Block{
        List<int[]> parts;

        Block(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
            this.parts = new ArrayList<>();
            parts.add(new int[]{x1,y1});
            parts.add(new int[]{x2,y2});
            parts.add(new int[]{x3,y3});
            parts.add(new int[]{x4,y4});
        }
    }
}

