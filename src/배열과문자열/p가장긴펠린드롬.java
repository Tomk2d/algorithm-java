package 배열과문자열;

class p가장긴펠린드롬 {
    public int solution(String s) {
        int maxLen = 1;

        for (int center = 0; center < s.length(); center++) {
            // 홀수
            maxLen = Math.max(maxLen, expandFromCenter(s, center, center));
            // 짝수
            maxLen = Math.max(maxLen, expandFromCenter(s, center, center + 1));
        }

        return maxLen;
    }

    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

