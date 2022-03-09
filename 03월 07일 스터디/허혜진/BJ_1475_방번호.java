package study0307;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1475_방번호 {

	static int num, arr[], cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		arr = new int[10];
		for (int i = 0; i < str.length(); i++) {
			num = str.charAt(i) - '0';

			//6과 9일때만 조건 따로 설정
			if (num == 6) {
				if (arr[6] > arr[9]) arr[9]++;
				else arr[6]++;
			} else if (num == 9) {
				if (arr[9] > arr[6]) arr[6]++;
				else arr[9]++;
			} else arr[num]++;
		}
		
		cnt = Integer.MIN_VALUE; // 충분히 작은 값
		
		for(int i=0; i<10; i++) {
			cnt = Math.max(cnt, arr[i]);
		}
		System.out.println(cnt);

	}

}
