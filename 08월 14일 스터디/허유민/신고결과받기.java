package programmers;
import java.util.*;
public class 신고결과받기 {

    class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = {};

            Map<String,Integer> Declaration = new HashMap<>();
            Map<String,HashSet<String>> list = new HashMap<>();

            // 초기화 작업
            for(int i = 0; i < id_list.length;i++){
                Declaration.put(id_list[i],0);
                HashSet<String> set = new HashSet<>();
                list.put(id_list[i],set);
            }

            for(int i = 0; i < report.length; i++){
                String[] stChange1 = report[i].split("\\s");
                String first = stChange1[0];
                String second = stChange1[1];

                //Declaration.put(second,Declaration.get("second")+1);
                list.get(second).add(first);
            }

            for(int i = 0; i < id_list.length;i++){
                HashSet<String> h = list.get(id_list[i]);
                if(h.size() >= k){
                    for (String userId : h) {
                        Declaration.put(userId, Declaration.get(userId) + 1);
                    }
                }

            }

            int i=0;
            answer = new int[id_list.length];
            for(String userId: id_list){
                answer[i]= Declaration.get(userId);
                i++;
            }
            return answer;
        }
    }
}
