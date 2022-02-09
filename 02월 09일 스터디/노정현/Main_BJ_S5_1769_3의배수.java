package silver.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_S5_1769_3의배수 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String num = br.readLine();
		int cnt = 0;

		// 자릿수가 한자리가 될 때까지
		while(num.length() > 1) {
			cnt++;  // 변환 횟수 증가
			int sum = 0;  // 각 자리의 합
			// 각 자리의 합
			for(int i = 0; i < num.length(); i++) {
				sum += Integer.parseInt(String.valueOf(num.charAt(i)));
			}
			num = String.valueOf(sum);
		}

		// 출력
		System.out.println(cnt);
		if((Integer.parseInt(num) % 3) == 0)  // 3의 배수이면
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}

}
