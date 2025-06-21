package 삼성대비빡구현;
import java.util.*;

/*
*       상당히 까다로운 문제였다. 나는 실제 코딩테스트에서 히든 케이스나, 이상치 등을 매번 잡지 못해서 탈락했다.
*       그걸 잡아보려고 해당 문제를 풀었는데, 역시나 히든 케이스를 생각하지 못하고 구현하기 급급했다.
*       그러다 보니 초반 설계도 잘못된 설계였고, 처음부터 코드를 다시 짜야했다.
*       가장 먼저 느낀점은 먼저, 문제를 잘읽자 이다.
*       간단해 보이는 설명이라도 먼저 문제를 읽고 이를 필기하자. 필기하면서 히든 케이스를 생각해보자. 동시에 이상치나 최대치도 확인하자
*       그걸 다 끝낸후 코드를 작성하자. 오히려 돌다리를 두드려 보는게 더 빠른 길인거 같다
*
*
* */
public class pccp실전문제_2_3 {

    class Solution {
        // 4 방향
        private int[] dx = {1, -1, 0, 0};
        private int[] dy = {0, 0, 1, -1};
        private int[][] nodes;
        private int[][] routes;
        private int N;
        public int solution(int[][] nodes, int[][] routes) {

            this.nodes = nodes;
            for(int[] route : routes){
                for(int i=0; i<route.length; i++){
                    route[i] --;
                }
            }
            this.routes = routes;
            N = routes.length;

            return solution();
        }

        private int solution(){
            List<List<int[]>> result = new ArrayList<>();
            // 요소 하나씩 돌림
            for(int[] route : routes){
                // 여러 경유지 있는 경우 고려
                List<int[]> wholeRoute = new ArrayList<>();
                wholeRoute.add(nodes[route[0]]);

                for(int i=1; i<route.length; i++){
                    int start = i-1;
                    int end = i;

                    // System.out.println(route[start] + " , "+ route[end]);

                    int[] startLocation = nodes[route[start]];
                    int[] endLocation = nodes[route[end]];


                    List<int[]> routeResult = findRoute(startLocation, endLocation);

                    for(int j=1; j<routeResult.size(); j++){
                        wholeRoute.add(routeResult.get(j));
                    }
                }

                // =========
                // System.out.println();
                // for(int[] el : wholeRoute){
                //     System.out.print(" (" + el[0]+ " , " + el[1] + ") ");
                // }
                // =========


                result.add(wholeRoute);
            }

            int maxLen = 0;
            for(List<int[]> el : result){
                maxLen = Math.max(maxLen, el.size());
            }
            int answer = 0;
            for(int i=0; i<maxLen; i++){
                Map<String, Integer> counter = new HashMap<>();

                // 겹치는 곳 카운트
                for(List<int[]> el : result){
                    if(el.size() <= i) continue;

                    int[] location = el.get(i);
                    String key = location[0]+ "," + location[1];
                    counter.put(key, counter.getOrDefault(key, 0)+1);
                }

                // 카운트 세어보기
                for(int cnt : counter.values()){
                    if(cnt >= 2) answer ++;
                }
            }

            return answer;
        }

        private List<int[]> findRoute(int[] start, int[] goal){
            Deque<Point> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[102][102];
            List<int[]> list = new ArrayList<>();
            list.add(start);
            queue.add(new Point(start, goal, list));

            // 최소 경로 찾기.
            while(!queue.isEmpty()){
                Point now = queue.pollFirst();
                // 도착했을때.
                if(now.my[0] == now.goal[0] && now.my[1] == now.goal[1]){
                    return now.route;
                }

                for(int d=0; d<4; d++){
                    int nx = now.my[0] + dx[d];
                    int ny = now.my[1] + dy[d];

                    if(nx>=0 && ny>=0 && nx<102 && ny<102 && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        int[] newLocation = {nx, ny};
                        List<int[]> newList = new ArrayList<>(now.route);
                        newList.add(newLocation);
                        queue.add(new Point(newLocation, now.goal, newList));
                    }
                }
            }
            return list;
        }

        private class Point{
            int[] my;
            int[] goal;
            List<int[]> route;

            Point(int[] my, int[] goal, List<int[]> route){
                this.my = my;
                this.goal = goal;
                this.route = route;
            }
        }
    }
}
