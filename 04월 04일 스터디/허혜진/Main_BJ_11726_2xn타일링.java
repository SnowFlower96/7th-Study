package study0404;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_11726_2xn타일링 {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+2]; // N+1이면 인덱스 에러남
		
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3; i<N+1; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%10007; // 계산 과정 중간에 %를 취해주지 않으면 int 범위 벗어남
		}
		
		System.out.println(dp[N]);
		
	}

}
