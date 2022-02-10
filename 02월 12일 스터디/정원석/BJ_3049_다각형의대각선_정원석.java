package com.ssafy.study01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_3049_다각형의대각선_정원석 {
	/*
	 * 이렇게 푸는게 아니란 것은 알지만...
	 * 생각이 나지 않았기 때문에 그냥 노가다로 규칙을 찾아 버렸습니다.
	 * 4각형은 1개
	 * 5각형은 ((1+2)+1) + (1)개
	 * 6각형은 ((1+2+3)+(1+2)+1) + (4 + 1)개
	 * 7각형은 ((1+2+3+4)+(1+2+3)+(1+2)+1) + (10 + 4 + 1)개
	 * n각형의 처음 꼭지점에서 출발한 대각선이 만나는 다른 대각선과의 접점은 n-3까지의 등차수열이다.
	 * 따라서 n각형의 대각선끼리의 접점을 찾는 규칙은 (n-1까지의 접점) + (n-3까지의 등차수열들의 합)이다.
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(Diagonal(n));

	}
	
	// 등차수열 만드는 함수
	public static int Diagonal(int n) {
		int sum = 0;
		
		if(n == 3) {;
			return 0;
		}
		
		for(int i = n-3 ; i >= 1; i--) {
			for(int j = 1; j <= i; j++) {
				sum += j;
			}
		}
		
		return sum + Diagonal(n-1);
	}

}
