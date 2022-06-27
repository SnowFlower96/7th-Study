package study0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main_BJ_17413_단어뒤집기2 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		
		char[] c = str.toCharArray();
		
		Stack<Character> stack = new Stack<>();

		for(int i=0; i<c.length; i++) {
			//문자가 < 일때
			if(c[i] == '<') {
				
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				
				int cnt = i;
				// 닫히는 태그 > 만날때 까지 반복
				while(true) {
					if(c[cnt] == '>')  {
						sb.append(c[cnt]);
						break;
					}
					sb.append(c[cnt]);
					cnt++;
				}
				
				i = cnt; 	//인덱스를 증가한 cnt로 바꿔줌
				
			} else if(c[i] != ' ') {	//일반 문자열일때 stack에 추가
				stack.add(c[i]); 
			
			} else {	// 문자가 공백이면 stack에 담긴 거 pop
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(' ');		// 조건에 의해 공백은 stack에 담기지 않기 때문에 sb에 공백 추가
				
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
//		System.out.println(stack);

		System.out.println(sb);
	}

}
