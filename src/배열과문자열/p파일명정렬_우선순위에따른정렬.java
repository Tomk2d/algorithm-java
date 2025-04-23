package 배열과문자열;

import java.util.*;

class Solution {
    List<FileInfo> sortedFile = new ArrayList<>();
    public String[] solution(String[] files) {
        parsingFile(files);

        // 여기 이렇게 정렬하는거 암기
        Collections.sort(sortedFile, Comparator
            .comparing((FileInfo f) -> f.head)
            .thenComparing(f -> f.number)
            .thenComparing(f -> f.idx)
        );
        List<String> answer = new ArrayList<>();

        for(FileInfo file : sortedFile){
            answer.add(file.name);
        }


        return answer.toArray(new String[0]);
    }

    private void parsingFile(String[] files){

        // 파일 하나씩
        for(int idx=0; idx<files.length; idx++){
            String file = files[idx];

            int numberStart = -1;
            int numberEnd = -1;

            // 파일의 한글자씩
            for(int i=1; i<file.length(); i++){
                char c = file.charAt(i);
                // 숫자일때.
                if(Character.isDigit(c)){
                    if(numberStart==-1){
                        numberStart = i;
                        numberEnd = i;
                    }else{
                        numberEnd++;
                    }
                }else if(numberStart>-1){     // 숫자도 아닌데, 숫자 진행 끝났을때
                    break;
                }
            }
            int headStart = 0;
            int headEnd = numberStart -1;

            String head = file.substring(headStart, headEnd+1);
            String number = file.substring(numberStart, numberEnd+1);
            sortedFile.add(new FileInfo(head, number, idx, file));
        }
    }

    private class FileInfo{
        String head;
        int number;
        int idx;
        String name;

        FileInfo(String head, String number, int idx, String name){
            this.head = head.toUpperCase();
            this.number = Integer.parseInt(number);
            this.idx = idx;
            this.name = name;
        }
    }
}
