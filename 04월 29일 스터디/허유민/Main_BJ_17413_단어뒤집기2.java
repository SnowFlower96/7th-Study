package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_17413_단어뒤집기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        //char[] words = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();

        char temp;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);

            // 1.
            if(temp == '<'){
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                sb.append(temp);
                flag=true;
                continue;
            }else if(temp == '>'){
                sb.append(temp);
                flag=false;
                continue;
            }

            if(flag){
                sb.append(temp);
                continue;
            }

            //2.
            if(temp == ' '){
                // 2-1.
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                sb.append(temp);
            }
            else {
                stack.push(temp);
            }

        }
        //2-1.
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb);

    }
}
