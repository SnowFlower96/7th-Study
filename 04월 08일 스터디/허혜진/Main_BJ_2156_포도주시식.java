package study0408;

import java.util.Scanner;

public class Main_BJ_2156_포도주시식 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] wine = new int[N+1];
		int[] dp = new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			wine[i] = sc.nextInt();
		}
		
		dp[1] = wine[1];
		
		if(N>1) {
			dp[2] = wine[1] + wine[2];
		}
	}

}
