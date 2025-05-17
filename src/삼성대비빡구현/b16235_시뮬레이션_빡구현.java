package 삼성대비빡구현;

import java.util.*;
import java.io.*;

/*
*       처음 이 문제를 Map<String , List<Integer>> 의 구조로 풀었다가 틀렸다.
*       List 가 배열 보다 느리다는 것은 알고 있었지만 그렇게나 차이가 날까 ? 였다
*       그것도 차이가 나지만 더 큰 문제는 String 으로 되어있는 key 값을 매번 생성하고 파싱하는 문제였다
*       x + " " + y 처럼 문자열 리터럴로 매번 문자를 생성하고, put 한다. 이는 많은 시간을 야기한다
*       저번 코테도 그래서 틀렸나보다
*       문자열 자체가 비교할때 equals 로 비교하고, 이는 결국 반복문이다.
*       키의 비교에서도 , 생성에서도, put 동작에서도 많은 시간이 걸린다. put 을 많이 하는 작업에서는 Map 쓰지말자 !
*       결국 배열 구조를 애용하고, 그안에 자료구조를 담는 식으로 해야할 것 같다.
*       추가로 정렬이 매번 수행되어야 하는경우 LinkedList 가 유리하다
*
* */

public class b16235_시뮬레이션_빡구현 {
    private static int N, M, K;
    private static int[][] board;
    private static int[][] energy;
    private static LinkedList<Integer>[][] trees;

    private static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        energy = new int[N][N];
        trees = new LinkedList[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], 5);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                energy[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(age);
        }

        while (K-- > 0) {
            springAndSummer();
            autumn();
            winter();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += trees[i][j].size();
            }
        }
        System.out.println(answer);
    }

    private static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j].isEmpty()) continue;

                Collections.sort(trees[i][j]);
                LinkedList<Integer> newList = new LinkedList<>();
                int deadSum = 0;

                for (int age : trees[i][j]) {
                    if (board[i][j] >= age) {
                        board[i][j] -= age;
                        newList.add(age + 1);
                    } else {
                        deadSum += age / 2;
                    }
                }

                trees[i][j] = newList;
                board[i][j] += deadSum;
            }
        }
    }

    private static void autumn() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : trees[i][j]) {
                    if (age % 5 != 0) continue;
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        trees[nx][ny].addFirst(1);
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] += energy[i][j];
            }
        }
    }
}
