package programmers;

import java.util.*;

public class 전화번호목록 {
    class Solution {
        public boolean solution(String[] phone_book) {
            Arrays.sort(phone_book);
            boolean answer = true;

            // startwith을 사용하면 이중 for문을 사용할 필요가 없다.
            for(int i = 0; i < phone_book.length-1;i++){
                int min = Math.min(phone_book[i].length(), phone_book[i+1].length());
                int cnt = 0;
                for(int j = 0; j < min;j++){
                    char a = phone_book[i].charAt(j);
                    char b = phone_book[i+1].charAt(j);
                    if(a == b) {
                        cnt++;
                    }
                }
                if(min == cnt){
                    return answer = false;
                }
            }
            return answer;
        }
    }
}
