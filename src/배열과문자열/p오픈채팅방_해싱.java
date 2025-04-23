package 배열과문자열;

import java.util.*;

class p오픈채팅방_해싱 {
    // uid : nickname
    private Map<String, String> users = new HashMap<>();
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();

        for(String order : record){
            String[] orderArr = order.split(" ");

            if(orderArr[0].equals("Enter")){
                users.put(orderArr[1], orderArr[2]);
            }else if(orderArr[0].equals("Change")){
                users.put(orderArr[1], orderArr[2]);
            }
        }

        for(String order : record){
            String[] orderArr = order.split(" ");

            if(orderArr[0].equals("Enter")){
                String nickname = users.get(orderArr[1]);
                answer.add(""+nickname+"님이 들어왔습니다.");
            }else if(orderArr[0].equals("Leave")){
                String nickname = users.get(orderArr[1]);
                answer.add(""+nickname+"님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }

}
