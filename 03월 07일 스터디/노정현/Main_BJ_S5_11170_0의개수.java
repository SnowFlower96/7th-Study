package silver.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_S5_11170_0의개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int result = 0;
			for(int i = N; i <= M; i++) {
				if(i == 0) result++;
				else result += getZero(i);
			}
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int getZero(int number) {
		int cnt = 0;
		
		while(number > 0) {
			if(number % 10 == 0) cnt++;
			number /= 10;
		}
		
		return cnt;
	}

}
