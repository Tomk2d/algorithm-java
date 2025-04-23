package 브루스포스;

import java.util.*;
class p단속카메라_일정범위구하기 {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        int lastCamera = Integer.MIN_VALUE;

        for(int[] route : routes){
            int start = route[0];
            int end = route[1];

            if(start > lastCamera){
                answer ++;
                lastCamera =end;
            }
        }

        return answer;
    }
}
