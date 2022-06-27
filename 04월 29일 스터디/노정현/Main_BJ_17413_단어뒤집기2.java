package silver.s3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * Study
 * 
 * @Author jhno96
 * @Date 2022. 4. 26.
 */
public class Main_BJ_17413_단어뒤집기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();
		Stack<Character> stack = new Stack<>();

		// 지금 입력하는 값이 단어인지
		boolean isWord = true;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			// 입력이 <이면 태그 시작
			if (c == '<') {
				isWord = false;
				while (!stack.isEmpty())
					bw.write(stack.pop());
			} else if (c == '>') {
				isWord = true;
				bw.write(c);
				continue;
			}

			// 현재 입력하는 값이 단어이면
			if (isWord) {
				// 마지막 단어이면
				if (i == (input.length() - 1)) {
					stack.push(c);
					while (!stack.isEmpty())
						bw.write(stack.pop());
				}
				
				// 빈칸이면 단어를 뒤집어서 출력
				if (c == ' ') {
					while (!stack.isEmpty())
						bw.write(stack.pop());
					bw.write(' ');
				}
				else
					stack.push(c);
			}
			// 태그이면 그대로 출력
			else {
				bw.write(c);
			}
		}

		bw.flush();
	}

}
