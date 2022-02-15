package com.ssafy.study01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//딱지의 개수정보를 저장할 객체를 만들 클래스를 작성
class scab{
	
	int star, circle, square, triangle;

	public scab() {
	}
	
	public scab(int star, int circle, int square, int triangle) {
		super();
		this.star = star;
		this.circle = circle;
		this.square = square;
		this.triangle = triangle;
	}
	
}

public class Main_BJ_14696_딱지놀이 {
	
	public static void main(String[] args) throws IOException{
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		//A와 B의 정보를 저장
		scab[] A = new scab[N];
		scab[] B = new scab[N];
		
		for(int i = 0; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input(A, i, st);
			st = new StringTokenizer(br.readLine());
			input(B, i, st);
		}
		
		
		//승리한 사람을 sb에 담는다.
		for(int i =0; i< N; i++) {
			//별 개수이 다를때
			if(A[i].star > B[i].star) sb.append("A\n");
			else if(A[i].star < B[i].star) sb.append("B\n");
			//원의 개수가 다를때
			else if(A[i].circle > B[i].circle) sb.append("A\n");
			else if(A[i].circle < B[i].circle) sb.append("B\n");
			//네모의 개수가 다를때
			else if(A[i].square > B[i].square) sb.append("A\n");
			else if(A[i].square < B[i].square) sb.append("B\n");
			//세모의 개수가 다를때
			else if(A[i].triangle > B[i].triangle) sb.append("A\n");
			else if(A[i].triangle < B[i].triangle) sb.append("B\n");
			//모든 모양의 개수가 같을 때
			else sb.append("D\n");
		}
		
		System.out.println(sb.toString());
	}

	//객체배열에 값을 저장할 메서드
	public static void input(scab[] arr, int t, StringTokenizer st) {
		int n = Integer.parseInt(st.nextToken());
		
		// 객체를 초기화시킨다.(생성한다.)
		arr[t] = new scab(0, 0, 0, 0);
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());			
			// 4면 star에 1을 더한다.
			if(num == 4) arr[t].star++;
			// 3이면 circle에 1을 더한다.
			else if(num == 3) arr[t].circle++;
			// 2이면 square에 1을 더한다.
			else if(num == 2) arr[t].square++;
			// 1이면 triangle에 1을 더한다.
			else arr[t].triangle++;
		}	
	}
}
