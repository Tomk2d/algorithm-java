package 슬라이딩윈도우_투포인터;

import java.util.*;

public class p메뉴리뉴얼_문자열빡쎈처리_슬라이딩윈도우 {
    Map<String, Integer> dict = new HashMap<>();
    List<String> result = new ArrayList<>();

    public String[] solution(String[] orders, int[] course) {
        for (int n : course) {
            dict.clear();
            for (String order : orders) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars); // 알파벳 순 정렬
                makeCombination(chars, new StringBuilder(), 0, n);
            }

            int max = 0;
            for (int count : dict.values()) {
                if (count > max) {
                    max = count;
                }
            }

            if (max >= 2) {
                for (String key : dict.keySet()) {
                    if (dict.get(key) == max) {
                        result.add(key);
                    }
                }
            }
        }

        Collections.sort(result);

        return result.toArray(new String[0]);
    }

    private void makeCombination(char[] order, StringBuilder combi, int idx, int targetLength) {
        if (combi.length() == targetLength) {
            String menu = combi.toString();
            dict.put(menu, dict.getOrDefault(menu, 0) + 1);
            return;
        }

        for (int i = idx; i < order.length; i++) {
            combi.append(order[i]);
            makeCombination(order, combi, i + 1, targetLength);
            combi.deleteCharAt(combi.length() - 1);
        }
    }
}
