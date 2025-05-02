package 배열과문자열;

import java.util.*;

class p방금그곡_시간문자열처리 {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxPlayTime = -1;
        m = replaceSharp(m);

        for (String info : musicinfos) {
            String[] arr = info.split(",");
            int start = getMinutes(arr[0]);
            int end = getMinutes(arr[1]);
            int playTime = end - start;

            String melody = replaceSharp(arr[3]);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < playTime; i++) {
                sb.append(melody.charAt(i % melody.length()));
            }

            String playedCode = sb.toString();

            if (playedCode.contains(m)) {
                if (playTime > maxPlayTime) {
                    maxPlayTime = playTime;
                    answer = arr[2];
                }
            }
        }

        return answer;
    }

    private int getMinutes(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    private String replaceSharp(String s) {
        return s.replaceAll("C#", "c")
            .replaceAll("D#", "d")
            .replaceAll("F#", "f")
            .replaceAll("G#", "g")
            .replaceAll("A#", "a")
            .replaceAll("B#", "b");
    }
}

