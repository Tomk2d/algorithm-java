package 배열과문자열;

public class pccp실전문제_2_1 {
    class Solution {
        public String solution(String video_len, String nowTime, String op_start, String op_end, String[] commands) {
            String answer = "";

            int op_start_h = Integer.parseInt(op_start.substring(0,2));
            int op_start_m = Integer.parseInt(op_start.substring(3,5));
            int op_start_mm = op_start_h * 60 + op_start_m;

            int op_end_h = Integer.parseInt(op_end.substring(0,2));
            int op_end_m = Integer.parseInt(op_end.substring(3,5));
            int op_end_mm = op_end_h*60 + op_end_m;

            int video_len_h = Integer.parseInt(video_len.substring(0,2));
            int video_len_m = Integer.parseInt(video_len.substring(3,5));
            int video_len_mm = video_len_h*60 + video_len_m;

            int now_h = Integer.parseInt(nowTime.substring(0,2));
            int now_m = Integer.parseInt(nowTime.substring(3,5));
            int now_mm = (now_h*60) + now_m;

            for(String command : commands){
                if(now_mm >= op_start_mm && now_mm <op_end_mm) now_mm = op_end_mm;

                if(command.equals("prev")){
                    int next_mm = now_mm - 10;
                    if(next_mm < 0) next_mm = 0;
                    now_mm = next_mm;

                }else if(command.equals("next")){
                    int next_mm = now_mm + 10;
                    if(next_mm > video_len_mm) next_mm = video_len_mm;
                    now_mm = next_mm;
                }
                if(now_mm >= op_start_mm && now_mm <op_end_mm) now_mm = op_end_mm;
            }

            String hour = now_mm/60 < 10 ? "0" + now_mm/60 : "" + now_mm/60;
            String minute = now_mm%60 < 10 ? "0" + now_mm%60 : "" + now_mm%60;


            answer = hour + ":" + minute;
            return answer;
        }
    }

}
