package com.ssafy.BJ02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2578_빙고 {

	static int bingo[][];
	static int bingo_cnt = 0;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bingo = new int[5][5];
		StringTokenizer st;
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 5; j++)
					bingo[i][j] = Integer.parseInt(st.nextToken());
		}
		int call = 0;
		called : for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 5 ; j++) {
					int num = Integer.parseInt(st.nextToken());
					call++;
					
					for(int r = 0; r < 5 ; r++) {
						for(int c = 0; c< 5; c++) {
							if(bingo[r][c] == num) bingo[r][c] = 0;								
						}
					}
					
					RowCheck();
					ColCheck();
					l_to_r_ColCheck();
					r_to_l_DiaCheck();
					
					if(bingo_cnt >= 3) { 
						System.out.println(call);
						break called;
					}
					
					bingo_cnt = 0;
				}
		}
			
	
	}
	
	public static void RowCheck() {
		for(int i = 0; i < 5; i++ ){
			int cnt=0;
			for(int j = 0; j < 5; j++) {
				if(bingo[i][j] == 0) cnt++;
				if(cnt == 5) {
					bingo_cnt++;
					break;
				}
			}
		}	
	}
	
	public static void ColCheck() {
		for(int i = 0; i < 5; i++ ){
			int cnt=0;
			for(int j = 0; j < 5; j++) {
				if(bingo[j][i] == 0) cnt++;
				if(cnt == 5) {
					bingo_cnt++;
					break;
				}
			}
		}	
	}
	
	public static void l_to_r_ColCheck() {
		int cnt=0;
		for(int i = 0; i < 5; i++ ){
			if(bingo[i][i] == 0) cnt++;
		}
		if(cnt == 5) 
			bingo_cnt++;	
	}
	
	public static void r_to_l_DiaCheck() {
		int cnt=0;
		for(int i = 0; i < 5; i++ ){
			if(bingo[i][4-i] == 0) cnt++;
		}
		if(cnt == 5) 
			bingo_cnt++;
	}

}
