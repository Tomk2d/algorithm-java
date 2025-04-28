package 슬라이딩윈도우_투포인터;

import java.util.*;

class p보석쇼핑_슬라이딩윈도우_0카운트따로관리 {
    public int[] solution(String[] gems) {
        Map<String, Integer> dict = new HashMap<>();
        for (String gem : gems) {
            dict.put(gem, 0);
        }

        int N = dict.size();
        int zeroCnt = N;

        int start = 0;
        int end = 0;
        int minSize = Integer.MAX_VALUE;
        int minStart = -1;
        int minEnd = -1;

        while (start <= end && end < gems.length) {
            String now = gems[end];
            if (dict.get(now) == 0) {
                zeroCnt--;  // 처음 등장한 보석
            }
            dict.put(now, dict.get(now) + 1);

            while (zeroCnt == 0) {  // 모든 보석이 있을 때
                int size = end - start + 1;
                if (size < minSize) {
                    minSize = size;
                    minStart = start;
                    minEnd = end;
                }
                String willRemove = gems[start];
                dict.put(willRemove, dict.get(willRemove) - 1);
                if (dict.get(willRemove) == 0) {
                    zeroCnt++;
                }
                start++;
            }
            end++;
        }

        return new int[]{minStart + 1, minEnd + 1};
    }
}
