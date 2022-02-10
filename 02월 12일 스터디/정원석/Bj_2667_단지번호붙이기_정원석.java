package com.ssafy.study02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bj_2667_단지번호붙이기_정원석 {

	final static int[] dr = {0, 0, 1, -1};
	final static int[] dc = {1, -1, 0, 0}; // 동서남북 사방탐색
	static int town_sum = 0; // 단지 개수를 저장
	static List<Integer> house_sum = new ArrayList<>(); //단지안의 하우스 개수를 저장할 List
	static int N;
	static boolean[][] isSelected;	//지나온 집을 체크할 boolean 배열
	
	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		isSelected = new boolean[N][N];
		//집들을 저장할 맵
		String[][] town = new String[N][N];
		
		for(int i = 0; i < N ;i++) {
			town[i] = br.readLine().trim().split("");
		}
		
		//step 01. 맵에서 하우스를 찾기
		for(int i = 0; i< N; i++) {
			for(int j = 0; j< N; j++) {
				
				//하우스를 찾고 그 하우스가 체크한 하우스가 아니라면 Search함수 호출
				if(town[i][j].equals("1") && isSelected[i][j] == false) {
					//단지개수와 하우스개수++
					town_sum++;
					house_sum.add(0);
					Search(town, i, j);
					
				}
			}
		}
		
		System.out.println(town_sum);
		//오릌차순으로 정렬
		Collections.sort(house_sum);
		for(int i : house_sum)
			System.out.println(i);
		
	}
	
	//step 02. 사방탐색을 활용하여 단지 찾기
	public static void Search(String[][] arr, int r, int c) {
//		System.out.println("넘어간다!");
		//단지안에 하우스를 개수를 ++;
		house_sum.set(town_sum-1, house_sum.get(town_sum-1) + 1);
		// 이 하우스는 체크해다고 표시
		isSelected[r][c] = true;
		
		int sr = r; int sc = c; //초기 r,c 저장
		
		// 사방탐색
		for(int i = 0; i < 4; i++) {
			sr = r + dr[i];
			sc = c + dc[i];
//			System.out.println("찾는중!!!");
//			System.out.println("sr = " + sr + "sc = " + sc);
			//하우스가 유효범위 안에있고 체크 되어있지 않은곳이면 그곳으로 이동하여 Search 재귀호출
			if(sr >= 0 && sr < N && sc >= 0 && sc < N && arr[sr][sc].equals("1") && isSelected[sr][sc]==false) {
//				System.out.println("1이당!!!!!!!");
//				System.out.println("sr = " + sr + "sc = " + sc);
				Search(arr, sr, sc);
			}
		}
		
		
	}

	

}
