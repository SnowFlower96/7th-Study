package com.ssafy.BJ03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2491_수열2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		// 오름차순의 카운트, 내림차순의 카운트 두가지 변수를 만든다.
		int down_cnt = 1;
		int up_cnt = 1;
		// 최대값을 저장할 변수를 만든다.
		int max = -1;
		// 첫번째 값을 미리 저장해 놓는다.
		arr[0] = Integer.parseInt(st.nextToken());
		for(int i =1; i< n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			// i값과 그 전의 값을 비고하여 같으면 두 변수 둘다 +을한다.
			if(arr[i]==arr[i-1]) {
				up_cnt++;
				down_cnt++;
			}
			// i가 올라가는 값이면 up변수에 +1을 하고 down변수는 1로 초기화한다. 
			if(arr[i] > arr[i-1]) {
				up_cnt++;
				down_cnt = 1;
			}
			// i가 내가는 값이면 down변수에 +1을 하고 up변수는 1로 초기화한다.
			if(arr[i] < arr[i-1]) {
				down_cnt++;
				up_cnt = 1;
			}
			// 최대값을 저장한다
			max = Math.max(max, Math.max(up_cnt, down_cnt));
		}
		
		System.out.println(max);
		

	}

}
