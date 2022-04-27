package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 스터디
 * @author jhno96
 * @date 2022. 4. 24.
 */
public class Main_BJ_12865_평범한배낭 {

	static int N, K;
	static int[] W, V;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		W = new int[N + 1];
		V = new int[N + 1];
		dp = new int[N + 1][K + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int w = 1; w <= K; w++) {
				if(W[i] > w) dp[i][w] = dp[i - 1][w];
				else {
					dp[i][w] = Math.max(dp[i - 1][w - W[i]] + V[i], dp[i - 1][w]);
				}
			}
		}
		
		for(int[] row : dp) System.out.println(Arrays.toString(row));
		
		System.out.println(dp[N][K]);
	}

}
