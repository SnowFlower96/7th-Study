package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-02
 */
public class Main_BJ_9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String target = br.readLine();

        System.out.println(solution1(input, target));
//        System.out.println(solution2(input, target));
    }

    private static String solution1(String input, String target) {
        char[] ans = new char[input.length()];
        int idx = 0;

        for (int i = 0; i < input.length(); i++) {
            ans[idx] = input.charAt(i);

            if(idx >= target.length() - 1) {
                boolean flag = true;
                for (int j = 0; j < target.length(); j++) {
                    if(target.charAt(j) != ans[idx - (target.length() - 1) + j]) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    idx -= target.length();
                }
            }
            idx++;
        }

        return idx != 0 ? String.valueOf(ans, 0, idx) : "FRULA";
    }

    private static String solution2(String input, String target) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            stack.push(c);

            // target 과 비교할 수 있으면(size 가 같거나 커질 때)
            if(stack.size() >= target.length()) {
                // 뒤에서부터 확인
                boolean flag = true;
                for (int j = 0; j < target.length(); j++) {
                    if(stack.get(stack.size() - target.length() + j) != target.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    for (int j = 0; j < target.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        for(Character c : stack) sb.append(c);

        return sb.length() == 0 ? "FRULA" : sb.toString();
    }

}
