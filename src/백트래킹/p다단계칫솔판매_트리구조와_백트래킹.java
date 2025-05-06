package 백트래킹;

import java.util.*;

class p다단계칫솔판매_트리구조와_백트래킹 {
    private int[] parents;
    private Map<String, Integer> index = new HashMap<>();
    private int[] result;

    public int[] solution(String[] members, String[] referral, String[] sellers, int[] amounts) {
        int n = members.length;
        result = new int[n];

        for (int i = 0; i < n; i++) {
            index.put(members[i], i + 1);
        }
        index.put("-", 0);

        parents = new int[n + 1];
        for (int i = 0; i < n; i++) {
            parents[i + 1] = index.get(referral[i]);
        }

        for (int i = 0; i < sellers.length; i++) {
            String seller = sellers[i];
            int sellerIdx = index.get(seller);
            int money = amounts[i] * 100;
            calMoney(sellerIdx, money);
        }

        return result;
    }

    private void calMoney(int idx, int money) {
        if (idx == 0 || money == 0) return;

        int payback = money / 10;
        int keep = money - payback;

        result[idx - 1] += keep;

        if (payback > 0) {
            calMoney(parents[idx], payback);
        }
    }
}

