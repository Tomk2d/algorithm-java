package 슬라이딩윈도우_투포인터;

import java.util.*;
import java.io.*;

public class b1593_다시풀기
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int wordN = Integer.parseInt(st.nextToken());
        int sentenceN = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String word = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String sentence = st.nextToken();

        Map<Character, Integer> wordCount = new HashMap<>();
        for(int i=0; i<wordN; i++){
            char c = word.charAt(i);
            wordCount.put(c, wordCount.getOrDefault(c, 0)+1);
        }

        int windowSize = wordN;
        int start = 0;
        int end = wordN-1;

        Map<Character, Integer> windowCount = new HashMap<>();
        for(int i=start; i<=end; i++){
            char c = sentence.charAt(i);
            windowCount.put(c, windowCount.getOrDefault(c, 0)+1);
        }

        int answer = 0;
        if(check(windowCount, wordCount)) answer ++;

        end++;
        while(end<sentenceN){
            // 윈도우에 추가
            windowCount.put(sentence.charAt(end), windowCount.getOrDefault(sentence.charAt(end), 0)+1);
            windowCount.put(sentence.charAt(start), windowCount.get(sentence.charAt(start))-1);

            if(check(windowCount, wordCount)) answer ++;

            start++;
            end++;
        }
        System.out.println(answer);
    }

    public static boolean check(Map<Character, Integer> windowCount, Map<Character, Integer> wordCount){

        for(char key : wordCount.keySet()){
            if(wordCount.get(key) != windowCount.getOrDefault(key, -1)){
                return false;
            }
        }

        return true;
    }

}

