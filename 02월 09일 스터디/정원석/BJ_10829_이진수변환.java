package com.ssafy.Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_10829_이진수변환 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine().trim());
		
		Stack<Long> bi = new Stack<>();
		
		divide(bi, n);
		long size = bi.size();
		//pop을 통하여 stack에 저장된 값들을 거꾸로 출력한다.
		for(long i = 0; i < size; i++) {
			System.out.print(bi.pop());
		}
	}
	
	// 이진수를 만들기 위한 메서드 생성
	public static void divide(Stack<Long> b, long n) {
		
		// n을 2로 나누다 1이 되면 그 1의 값을 stack에 저장하고 종료한다. 
		if(n == 1) {
			b.push(1L);
			return;
		}
		
		// n을 2로 나누어서 나머지가 0이면 0을 저장하고 1이면 1을 저장한다.
		if(n % 2 == 0) {
			b.push(0L);
		}else
			b.push(1L);
		
		// 계속해서 2로 나눈 값을 재귀를 함
		divide(b, n/2);
		
	}
}
