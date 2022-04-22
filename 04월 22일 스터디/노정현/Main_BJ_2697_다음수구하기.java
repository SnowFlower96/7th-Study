package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * Study
 * @author jhno96
 * @date 2022. 4. 19.
 */
public class Main_BJ_2697_다음수구하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			String input = br.readLine();

			sb.append(solution(input)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static String solution(String input) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i = input.length() - 1; i > 0; i--) {
			if(input.charAt(i) > input.charAt(i - 1)) {
				sb.append(input.substring(0, i - 1));

				char val = input.charAt(i - 1);
				char[] left = input.substring(i).toCharArray();

				char min = Character.MAX_VALUE; 
				int idx = -1;
				for(int j = 0; j < left.length; j++) {
					if(val < left[j]) {
						if(left[j] < min) {
							min = left[j];
							idx = j;
						}
					}
				}

				left[idx] = val;
				Arrays.sort(left);
				sb.append(min).append(left);
				
				flag = true;
				break;
			}
		}

		return flag ? sb.toString() : "BIGGEST";
	}

}
