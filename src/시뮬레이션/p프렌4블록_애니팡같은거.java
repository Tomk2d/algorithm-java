package 시뮬레이션;

import java.util.*;

class Solution {
    private final int[] dx = {0, 1, 1};
    private final int[] dy = {1, 0, 1};
    private Set<Node> bombBlocks;
    private int N, M;
    private int answer = 0;
    private char[][] board;

    public int solution(int n, int m, String[] firstBoard) {
        N = n;
        M = m;
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = firstBoard[i].toCharArray();
        }

        while (true) {
            bombBlocks = new HashSet<>();

            for (int x = 0; x < N - 1; x++) {
                for (int y = 0; y < M - 1; y++) {
                    findBomb(x, y, board[x][y]);
                }
            }

            int breakedBlock = breakBlock();
            if (breakedBlock == 0) break;

            answer += breakedBlock;
            updateBoard();
        }

        return answer;
    }

    private void updateBoard() {
        for (int y = 0; y < M; y++) {
            List<Character> column = new ArrayList<>();

            for (int x = N - 1; x >= 0; x--) {
                if (board[x][y] != ' ') {
                    column.add(board[x][y]);
                }
            }

            for (int x = N - 1, i = 0; x >= 0; x--, i++) {
                board[x][y] = (i < column.size()) ? column.get(i) : ' ';
            }
        }
    }

    private int breakBlock() {
        int breakedBlock = 0;
        for (Node block : bombBlocks) {
            if (board[block.x][block.y] != ' ') {
                breakedBlock++;
                board[block.x][block.y] = ' ';
            }
        }
        return breakedBlock;
    }

    private void findBomb(int x, int y, char block) {
        if (block == ' ') return;

        List<Node> result = new ArrayList<>();
        result.add(new Node(x, y));
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == block) {
                result.add(new Node(nx, ny));
            } else {
                return;
            }
        }

        bombBlocks.addAll(result); // Set이므로 중복 자동 제거
    }

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 중복 제거를 위한 equals/hashCode 구현
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

