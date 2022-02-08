package com.ssafy.Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1769_3의배수 {

	static int cnt = 0;
	static boolean isThree = false;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		divide(N);
		System.out.println(cnt);
		if(isThree) System.out.println("YES");
		else System.out.println("NO");
		
	}
	
	// 입력값을 각 자릿수로 더하여 합을 구한뒤 3의 배수인지 확인하는 메서드
	public static void divide(String n) {
		
		// 자리수의 합이 1의 자리수 일 경우 3의 배수인지 확인하고 3의 배수이면 isThree를 true로 변경한다.
		if(n.length() < 2) {
			if(Integer.parseInt(n)%3 == 0) 
				isThree = true;
			return;
		}
		//재귀함수를 사용할 때마다 cnt+1를 해준다.
		cnt++;
		
		//자리수들을 더할 변수를 만든다. 재귀함수가 사용될 때 마다 초기화 시킨다. 
		int sum = 0;
		
		//sum변수에 자리수 값들을 더하여 저장한다.
		for(int i = 0; i < n.length() ; i++) {
			sum += n.charAt(i)-'0';
		}
		
		divide(sum+"");
	}
}
