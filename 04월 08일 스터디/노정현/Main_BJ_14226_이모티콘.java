package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * Study
 * @author jhno96
 * @date 2022. 4. 8.
 */
public class Main_BJ_14226_이모티콘 {

	static int S, ans;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		dp = new int[3000];
		Arrays.fill(dp, Integer.MAX_VALUE / 2);
		
		dp[1] = 0;
		for(int i = 1; i <= 1000; ++i) {
			for(int j = 2; j * i <= 1000; ++j) {
				dp[i * j] = Math.min(dp[i * j], dp[i] + j);
			}
			for(int j = 1000; j >= 2; --j) {
				dp[j] = Math.min(dp[j],  dp[j + 1] + 1);
			}
		}
		
		System.out.println(dp[S]);
	}

}
