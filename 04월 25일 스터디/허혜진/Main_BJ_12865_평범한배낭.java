package study0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_12865_평범한배낭 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] weights = new int[N+1];
		int[] profits = new int[N+1];
		
		int[][] results = new int[N+1][W+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			profits[i] = Integer.parseInt(st.nextToken());
		}
		
		int itemWeight = 0;
		int itemBenefit = 0;
		
		for(int item=1; item <= N; item++) {
			itemWeight = weights[item];
			itemBenefit = profits[item];
			
			for(int weight = 1; weight <= W; weight++) {
				if(itemWeight <= weight) {
					results[item][weight] = Math.max(results[item-1][weight], itemBenefit+results[item-1][weight-itemWeight]);
				} else {
					results[item][weight] = results[item-1][weight];
				}
			}
		}
		
		System.out.println(results[N][W]);
		
	}

}
