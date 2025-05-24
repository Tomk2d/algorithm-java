package 삼성대비빡구현;

import java.util.*;
import java.io.*;

/*
*       사실 이 문제는 한방향만 잘 풀면 된다.
*       그리고 그 움직이는 방향 메서드를 바탕으로 다른 메서드를 방향별로 짜면된다.
*       사실 리팩토링으로 코드의 중복을 줄이고, 재사용해야 할거 같다.
*       근데 너무 시간을 많이 쓸거 같아서 말았다.
*       이제부터 메서드를 짤때마다 단위테스트를 하자.
*       테스트 케이스를 생각하고 넣어줘서 문제를 조금씩 잡으면서 가자 !
* */

public class b12100_2048
{
    private static int N;
    private static int[][] board;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeCombination(new ArrayList<>());
        //print();

        System.out.println(answer);
    }

    private static void makeCombination(List<Integer> directions){
        if(directions.size() == 5){
            // 리스트로 이동 부르기
            simulation(directions);

            return;
        }
        // 상 , 우, 하 , 좌 순서
        for(int i=0; i<4; i++){
            directions.add(i);
            makeCombination(directions);
            directions.remove(directions.size()-1);
        }
    }

    private static void simulation(List<Integer> directions){
        int[][] origin = new int[N][N];
        for(int i=0; i<N; i++){
            origin[i] = board[i].clone();
        }

        int result = 0;
        for(int direction : directions){
            int num = 2;
            if(direction == 0){        // 상
                result = Math.max(result, moveUp());
            }else if(direction == 1){  // 우
                result = Math.max(result, moveRight());
            }else if(direction == 2){  // 하
                result = Math.max(result, moveDown());
            }else if(direction == 3){  // 좌
                result = Math.max(result, moveLeft());
            }

        }

        answer = Math.max(answer, result);
        board = origin;
    }
    private static int moveRight(){
        int maxNumber = 0;
        for(int x=0; x<N; x++){
            int[] row = board[x];

            // 0이 아닌 움직일 수들
            LinkedList<Integer> numbers = new LinkedList<>();
            for(int i=row.length-1; i>=0; i--){
                if(row[i] != 0) {
                    numbers.add(row[i]);
                    maxNumber = Math.max(maxNumber, row[i]);
                }
            }

            if(numbers.size() == 0){
                continue;
            }else if(numbers.size() == 1){
                row[row.length-1] = numbers.pop();
                for(int i=0; i<row.length-1; i++){
                    row[i] = 0;
                }
            }else{
                // 합쳐지는 부분 찾기
                LinkedList<Integer> sumNumbers = new LinkedList<>();
                while(!numbers.isEmpty()){
                    if(numbers.size() == 1){
                        sumNumbers.add(numbers.pop());
                        break;
                    }

                    int start = numbers.pop();
                    int end = numbers.pop();
                    if(start == end){
                        int sumNum = start + end;
                        sumNumbers.add(sumNum);
                        maxNumber = Math.max(maxNumber, sumNum);
                    }else{
                        // 처음꺼 그냥 넣고
                        sumNumbers.add(start);
                        // 뒤에꺼 다시
                        numbers.addFirst(end);
                    }
                }
                // 반영
                for(int i=row.length-1; i>=0; i--){
                    if(!sumNumbers.isEmpty()){
                        int num = sumNumbers.pop();
                        row[i] = num;
                    }else{
                        row[i] = 0;
                    }

                }

            }

        }
        return maxNumber;
    }

    private static int moveLeft(){
        int maxNumber = 0;
        for(int x=0; x<N; x++){
            int[] row = board[x];

            LinkedList<Integer> numbers = new LinkedList<>();
            for(int i=0; i<row.length; i++){
                if(row[i] != 0) {
                    numbers.add(row[i]);
                    maxNumber = Math.max(maxNumber, row[i]);
                }
            }

            if(numbers.size() == 0) continue;
            else if(numbers.size() == 1){
                row[0] = numbers.pop();
                for(int i=1; i<row.length; i++) row[i] = 0;
            }else{
                LinkedList<Integer> sumNumbers = new LinkedList<>();
                while(!numbers.isEmpty()){
                    if(numbers.size() == 1){
                        sumNumbers.add(numbers.pop());
                        break;
                    }

                    int start = numbers.pop();
                    int next = numbers.pop();
                    if(start == next){
                        int sumNum = start + next;
                        sumNumbers.add(sumNum);
                        maxNumber = Math.max(maxNumber, sumNum);
                    }else{
                        sumNumbers.add(start);
                        numbers.addFirst(next);
                    }
                }

                for(int i=0; i<row.length; i++){
                    if(!sumNumbers.isEmpty()){
                        row[i] = sumNumbers.pop();
                    }else{
                        row[i] = 0;
                    }
                }
            }
        }
        return maxNumber;
    }

    private static int moveUp(){
        int maxNumber = 0;
        for(int y=0; y<N; y++){
            LinkedList<Integer> numbers = new LinkedList<>();
            for(int x=0; x<N; x++){
                if(board[x][y] != 0){
                    numbers.add(board[x][y]);
                    maxNumber = Math.max(maxNumber, board[x][y]);
                }
            }

            if(numbers.size() == 0) continue;

            LinkedList<Integer> sumNumbers = new LinkedList<>();
            while(!numbers.isEmpty()){
                if(numbers.size() == 1){
                    sumNumbers.add(numbers.pop());
                    break;
                }

                int a = numbers.pop();
                int b = numbers.pop();
                if(a == b){
                    int sum = a + b;
                    sumNumbers.add(sum);
                    maxNumber = Math.max(maxNumber, sum);
                }else{
                    sumNumbers.add(a);
                    numbers.addFirst(b);
                }
            }

            for(int x=0; x<N; x++){
                if(!sumNumbers.isEmpty()){
                    board[x][y] = sumNumbers.pop();
                }else{
                    board[x][y] = 0;
                }
            }
        }
        return maxNumber;
    }

    private static int moveDown(){
        int maxNumber = 0;
        for(int y=0; y<N; y++){
            LinkedList<Integer> numbers = new LinkedList<>();
            for(int x=N-1; x>=0; x--){
                if(board[x][y] != 0){
                    numbers.add(board[x][y]);
                    maxNumber = Math.max(maxNumber, board[x][y]);
                }
            }

            LinkedList<Integer> sumNumbers = new LinkedList<>();
            while(!numbers.isEmpty()){
                if(numbers.size() == 1){
                    sumNumbers.add(numbers.pop());
                    break;
                }

                int a = numbers.pop();
                int b = numbers.pop();
                if(a == b){
                    int sum = a + b;
                    sumNumbers.add(sum);
                    maxNumber = Math.max(maxNumber, sum);
                }else{
                    sumNumbers.add(a);
                    numbers.addFirst(b);
                }
            }

            for(int x=N-1; x>=0; x--){
                if(!sumNumbers.isEmpty()){
                    board[x][y] = sumNumbers.pop();
                }else{
                    board[x][y] = 0;
                }
            }
        }
        return maxNumber;
    }




    private static void print(){
        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}

