package 시뮬레이션;

public class pccp모의고사1 {

    class Solution {
        char[] orders;
        // 상 우 하 좌
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        public int[] solution(String command) {

            orders = command.toCharArray();

            return simulation(0, 0, 0);
        }

        private int[] simulation(int startX, int startY, int d){
            int x = startX;
            int y = startY;

            for(char order : orders){
                if(order == 'R'){
                    d = (d+1) % 4;
                }else if(order == 'L'){
                    d = (d-1+4) % 4;
                }else if(order == 'G'){
                    x = x + dx[d];
                    y = y + dy[d];
                }else if(order == 'B'){
                    x = x - dx[d];
                    y = y - dy[d];
                }
                //System.out.println(order);
                //System.out.println("X : " + x + " Y : " + y + " D : " + d);
            }

            int[] result = {y, x};
            return result;
        }
    }
}
