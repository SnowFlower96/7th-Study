package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_S5_2491_수열 {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int max = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 커질 때
		int cnt_increase = 1;
		for(int i = 0; i < N - 1; i++) {
			if(arr[i] <= arr[i+1]) cnt_increase++;
			else cnt_increase = 1;
			max = Math.max(max, cnt_increase);
		}
		
		// 작아질 때
		int cnt_decrease = 1;
		for(int i = 0; i < N - 1; i++) {
			if(arr[i] >= arr[i+1]) cnt_decrease++;
			else cnt_decrease = 1;
			max = Math.max(max, cnt_decrease);
		}
		
		// 결과 출력
		System.out.println(max);
	}

}
