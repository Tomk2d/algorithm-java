package 조합;

import java.util.*;
import java.io.*;

public class b1497_문자열과조합 {
    private static int maxSong = 0;
    private static int minGuitar = Integer.MAX_VALUE;
    private static String[] possibleSongs;
    private static int guitarN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        guitarN = Integer.parseInt(st.nextToken());
        int songN = Integer.parseInt(st.nextToken());

        possibleSongs = new String[guitarN];

        for(int i=0; i<guitarN; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            possibleSongs[i] = st.nextToken();
        }

        for(int i=0; i<guitarN; i++){
            List<Integer> guitars = new ArrayList<>();
            guitars.add(i);
            dfs(guitars, i + 1);
        }

        System.out.println(maxSong == 0 ? -1 : minGuitar);
    }

    public static void dfs(List<Integer> guitars, int idx){
        check(guitars);

        for(int i=idx; i<guitarN; i++){
            guitars.add(i);
            dfs(guitars, i + 1);
            guitars.remove(guitars.size() - 1);
        }
    }

    public static void check(List<Integer> guitars){
        Set<Integer> countSet = new HashSet<>();

        for(int n : guitars){
            String possibleSong = possibleSongs[n];

            for(int i=0; i<possibleSong.length(); i++){
                if(possibleSong.charAt(i) == 'Y'){
                    countSet.add(i);
                }
            }
        }

        int possibleCnt = countSet.size();
        if(maxSong < possibleCnt){
            maxSong = possibleCnt;
            minGuitar = guitars.size();
        } else if(maxSong == possibleCnt){
            minGuitar = Math.min(minGuitar, guitars.size());
        }
    }
}

