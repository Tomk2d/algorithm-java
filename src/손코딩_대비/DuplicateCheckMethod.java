package 손코딩_대비;

import java.util.HashMap;
import java.util.Map;

public class DuplicateCheckMethod {
    public void duplicateCheck(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : array){
            if (map.containsKey(n)){
                map.put(n, map.get(n)+1);
            }else{
                map.put(n, 1);
            }
            // 이 한줄로 끝낼 수 있긴함.
            // map.put(n, map.getOrDefault(n, 0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            System.out.println("Element: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}
