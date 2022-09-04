package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 오픈채팅방 {

    /*
    ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]
     */

    static String[] input = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
    public static void main(String[] args) {
        String[] answer = solution(input);
        for (String s:
             answer) {
            System.out.println(s);
        }
    }

    public static String[] solution(String[] record){
        String[] answer = {};
        Map<String,String> user = new HashMap<>();
        List<String> chatLog = new ArrayList<>();
        for (String s : record){
            String[] infoarray = s.split("\\s");
            if(infoarray[0].equals("Enter")){
                user.put(infoarray[1],infoarray[2]);
                String enterMsg = infoarray[1] + "님이 들어왔습니다.";
                chatLog.add(enterMsg);
            }
            else if(infoarray[0].equals("Leave")){
                String leaveMsg = infoarray[1] + "님이 나갔습니다.";
                chatLog.add(leaveMsg);
            }
            else if(infoarray[0].equals("Change")){
                user.put(infoarray[1],infoarray[2]);
            }
        }

        answer = new String[chatLog.size()];
        int i = 0;
        for (String log : chatLog){
            int nim = log.indexOf("님");
            String uid = log.substring(0,nim);
            String msg = log.substring(nim,log.length());
            answer[i++] = user.get(uid) + msg;
        }
        return answer;
    }
}
