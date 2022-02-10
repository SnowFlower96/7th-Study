package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10829_이진수변환 {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//long N = 53;
		long N = sc.nextLong();
		
		// 2진수 변환
		//String N2 = Integer.toBinaryString((int) N);
		// 재귀로 구현
		binary(N);
		// 뒤집어서 출력
		String reversedStr = sb.reverse().toString();
		System.out.println(reversedStr);
		sc.close();
	}
	
	public static void binary(long N) {
		long i = N % 2;
		long j = N / 2;
		
		if(N / 2 == 0) {
			sb.append(i);
			return;
		} else {
			sb.append(i);
		}
		binary(j);
	}

}
