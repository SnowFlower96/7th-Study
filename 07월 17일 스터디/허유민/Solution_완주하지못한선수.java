package programmers;

import java.util.Arrays;

public class Solution_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        // 정렬을 하여 순서대로 비교
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i;
        for(i = 0; i < participant.length-1; i++){
            if(!participant[i].equals(completion[i]) ){
                return participant[i];
            }
        }

        return participant[i];
    }
}
