package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class 불량사용자 {

    static String[] user_input = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
    static String[] banned_id = {"*rodo", "*rodo", "******"};
    static ArrayList<Integer>[] banned_char;
    static String[] temp;
    static boolean[] visited;
    static boolean[] ban_visited;
    static int max;
    static HashSet<String> set;

    public static void main(String[] args) {
        System.out.println(solution(user_input, banned_id));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        banned_char = new ArrayList[banned_id.length];
        visited = new boolean[user_id.length];
        ban_visited = new boolean[banned_id.length];
        temp = new String[banned_id.length];
        max = 0;
        set = new HashSet<>();

        // '*' 가 아닌 문자들의 index 값을 기록
        for (int i = 0; i < banned_id.length; i++) {
            String s = banned_id[i];
            banned_char[i] = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != '*') {
                    banned_char[i].add(j);
                }
            }
        }

        combination(user_id, banned_id, 0, 0, banned_id.length);

//        answer = max;
//        System.out.println("set size : " + set.size());
        return set.size();
    }

    static void combination(String[] user_id, String[] banned_id, int idx, int cnt, int r) {
        if (cnt == r) {
//            for (int i = 0; i < r; i++) {
//                System.out.print(temp[i] + " ");
//            }
//            System.out.println();
            // 만들어진 경우를 정렬하여 문자열들을 합쳐 하나의 문자로 만들어 중복 제거
            String[] result = temp.clone();
            Arrays.sort(result);
            String str = "";
            for (int i = 0; i < result.length; i++) {
                str += result[i];
            }
            set.add(str);
//            max++;
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            boolean flag = false;
            String s = user_id[i];
            for (int j = cnt; j < banned_char.length; j++) {
                ArrayList<Integer> list = banned_char[j];
                int count = 0;

                //|| ban_visited[j]
                if (s.length() != banned_id[j].length() || ban_visited[j]) continue;
                for (int k = 0; k < list.size(); k++) {
                    int a = list.get(k);
                    if (s.charAt(a) == banned_id[j].charAt(a)) {
                        count++;
                    }
                }
                if (count == list.size()) {
                    flag = true;
                    ban_visited[j] = true;
                    if (!visited[i] && flag) {
                        visited[i] = true;
                        temp[cnt] = user_id[i];
                        combination(user_id, banned_id, i, cnt + 1, r);
                        temp[cnt] = null;
                        visited[i] = false;
                    }
                    ban_visited[j] = false;
                    break;
                }
            }

        }
    }
}
