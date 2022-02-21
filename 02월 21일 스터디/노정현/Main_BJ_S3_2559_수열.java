package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_S3_2559_수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solution(arr, N, K));
		
		br.close();
	}

	public static int solution(int[] arr, int N, int K) {
		int result = Integer.MIN_VALUE;
		
		for(int i = 0; i <= N - K; i++) {
			int sum = 0;
			for(int j = i; j < i + K; j++) {
				sum += arr[j];
			}
			result = Math.max(result, sum);
		}
		
		return result;
	}
	
}
