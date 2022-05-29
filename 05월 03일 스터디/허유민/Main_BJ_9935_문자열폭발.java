package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        String explosion = br.readLine();
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack.push(c);

            if (stack.size() >= explosion.length()) {
                boolean flag = true;
                for (int j = explosion.length() - 1, k = stack.size() - 1; j >= 0; j--, k--) {
                    if (stack.get(k) != explosion.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    for (int j = 0; j < explosion.length(); j++) {
                        stack.pop();
                    }
                }
            }


        }
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}
