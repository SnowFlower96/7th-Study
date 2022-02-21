package com.ssafy.BJ01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2559_수열_정원석 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int term = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		
//		배열 사용 
//		int sum_max = Integer.MIN_VALUE;
//		
//		for(int i = 0; i <= N - term; i++) {
//			int sum = 0;
//			for(int j = i; j < i + term; j++) {
//				sum += seq[j];
//			}
//			sum_max = Math.max(sum_max, sum);
//		}
//		
//		System.out.println(sum_max);	
		
		//투포인터
		//밑의 코드를 활용하면 시간단축기 가능하다.
		// 1 2 3 4 5 6 7 8 9 이 있고 term이 4라면
		// 1 2 3 4		     를 처음에 저장을 하고
		//   2 3 4 5	     로 한칸 전진하는 경우 1을 저장한 변수에서 뺴고 새로 들어오는 5를 더해준다.
		int sum = 0; 
		for(int i = 0; i < term; i++) {
			sum += seq[i];
		}
		int sum_max = sum;
		for(int i = term; i< N; i++) {
			sum = sum - seq[i-term] + seq[i]; 
			sum_max = Math.max(sum_max,  sum);
		}
		
		System.out.println(sum_max);
	}

}
