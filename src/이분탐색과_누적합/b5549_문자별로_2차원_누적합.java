package 이분탐색과_누적합;

import java.util.*;
import java.io.*;

// 이건 배열에 객체만들어서 저장하는 방법을 사용했다.
// 근데 실전에서는 살짝 헷갈릴수도 있을거 같긴 하다.
// 그래도 배열 각각 3개에, 메서드 각각 만드는거 보다는 나을듯

public class b5549_문자별로_2차원_누적합 {
    private static int N, M, caseN;
    private static char[][] board;
    private static Point[][] calBoard;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        caseN = Integer.parseInt(st.nextToken());

        int[][] orders = new int[caseN][4];

        board = new char[N][M];
        calBoard = new Point[N][M];

        for(int i=0; i<N; i++){
            board[i]= br.readLine().toCharArray();
        }

        for(int i=0; i<caseN; i++){
            st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            orders[i][0] = startX-1;
            orders[i][1] = startY-1;
            orders[i][2] = endX-1;
            orders[i][3] = endY-1;
        }

        for(int y=0; y<M; y++){
            char a = board[0][y];

            if(a == 'J'){
                calBoard[0][y] = new Point(1, 0, 0);
            }else if(a == 'O'){
                calBoard[0][y] = new Point(0, 1, 0);
            }else if(a == 'I'){
                calBoard[0][y] = new Point(0, 0, 1);
            }
        }

        for(int x=1; x<N; x++){
            for(int y=0; y<M; y++){
                Point pre = calBoard[x-1][y];

                char a = board[x][y];

                if(a == 'J'){
                    calBoard[x][y] = new Point(pre.J+1, pre.O, pre.I);
                }else if(a == 'O'){
                    calBoard[x][y] = new Point(pre.J, pre.O+1, pre.I);
                }else if(a == 'I'){
                    calBoard[x][y] = new Point(pre.J, pre.O, pre.I+1);
                }
            }
        }

        for(int[] order : orders){
            int[] result = calculate(order[0], order[1], order[2], order[3]);
            System.out.println(result[0] + " "+ result[1] + " "+ result[2]);
        }

    }

    private static int[] calculate(int startX, int startY, int endX, int endY){
        int J = 0;
        int O = 0;
        int I = 0;

        // 합하기
        for(int y=startY; y<=endY; y++){
            Point now = calBoard[endX][y];
            J += now.J;
            O += now.O;
            I += now.I;
        }

        if(startX == 0){
            int[] answer = {J, O, I};
            return answer;
        }

        // 빼기
        for(int y=startY; y<=endY; y++){
            Point now = calBoard[startX-1][y];
            J -= now.J;
            O -= now.O;
            I -= now.I;
        }

        int[] answer = {J, O, I};
        return answer;
    }

    private static class Point{
        int J;
        int O;
        int I;

        Point(int J, int O, int I){
            this.J = J;
            this.O = O;
            this.I = I;
        }
    }

}

