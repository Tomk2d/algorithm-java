package 시뮬레이션;

import java.util.*;

class p거리두기확인하기_빡구현 {
    int[] dx = {-1, 0, 0, 1};   // 상 좌 우 하
    int[] dy = {0, -1, 1, 0};
    int[] ddx = {-1, -1, 1, 1}; // 대각선
    int[] ddy = {-1, 1, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int idx = 0;
        for (String[] place : places) {
            answer[idx++] = simulation(place);
        }
        return answer;
    }

    private int simulation(String[] place) {
        List<Seat> seats = new ArrayList<>();
        char[][] board = new char[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char c = place[i].charAt(j);
                if (c == 'P') seats.add(new Seat(i, j));
                board[i][j] = c;
            }
        }

        for (Seat seat : seats) {
            if (!checkSeat(seat.x, seat.y, board)) return 0;
        }
        return 1;
    }

    private boolean checkSeat(int x, int y, char[][] board) {
        // 1. 상하좌우 검사
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inRange(nx, ny)) {
                if (board[nx][ny] == 'P') return false;
                if (board[nx][ny] == 'O') {
                    int nnx = nx + dx[i];
                    int nny = ny + dy[i];
                    if (inRange(nnx, nny) && board[nnx][nny] == 'P') return false;
                }
            }
        }

        // 2. 대각선 검사
        if (inRange(x + ddx[0], y + ddy[0]) && board[x + ddx[0]][y + ddy[0]] == 'P'
            && (board[x + dx[0]][y + dy[0]] == 'O' || board[x + dx[1]][y + dy[1]] == 'O')) return false;
        if (inRange(x + ddx[1], y + ddy[1]) && board[x + ddx[1]][y + ddy[1]] == 'P'
            && (board[x + dx[0]][y + dy[0]] == 'O' || board[x + dx[2]][y + dy[2]] == 'O')) return false;
        if (inRange(x + ddx[2], y + ddy[2]) && board[x + ddx[2]][y + ddy[2]] == 'P'
            && (board[x + dx[1]][y + dy[1]] == 'O' || board[x + dx[3]][y + dy[3]] == 'O')) return false;
        if (inRange(x + ddx[3], y + ddy[3]) && board[x + ddx[3]][y + ddy[3]] == 'P'
            && (board[x + dx[2]][y + dy[2]] == 'O' || board[x + dx[3]][y + dy[3]] == 'O')) return false;

        return true;
    }

    private boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }

    private class Seat {
        int x, y;

        Seat(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

