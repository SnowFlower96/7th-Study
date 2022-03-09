package study0214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2578_빙고 {

	static int map[][], call[];
	static int bingoCnt=0;
	static int zeroCnt=0;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[5][5];
		call = new int[25];
		
		// 빙고판 입력 받기
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 사회자가 부르는 수
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=i*5; j<i*5+5; j++) {
				call[j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<call.length; i++) {
			
			for(int x=0; x<5; x++) {
				for(int y=0; y<5; y++) {
					if(call[i] == map[x][y]) {
						map[x][y] = 0;
					}
				}
			}// map
			
				
			rCheck(); // 행 체크
			cCheck(); // 열 체크
			leftCheck(); // 왼쪽 대각선
			rightCheck(); // 오른족 대각선
				
			if(bingoCnt == 3) {
				System.out.println(i+1);
				break;
			}
			bingoCnt=0;
			
		}
		
	}//main

	private static void rCheck() {
		for(int x=0; x<5; x++) {
			zeroCnt=0;
			for(int y=0; y<5; y++) {
				if(map[x][y] == 0) {
					zeroCnt++;
					
				}
				if(zeroCnt == 5) bingoCnt++;
			}
		}
	}
	
	private static void cCheck() {
		for(int x=0; x<5; x++) {
			zeroCnt=0;
			for(int y=0; y<5; y++) {
				if(map[y][x] == 0) {
					zeroCnt++;
					
				}
				if(zeroCnt == 5) bingoCnt++;
			}
		}
	}
	
	private static void leftCheck() {
		zeroCnt=0;
		for(int x=0; x<5; x++) {
			if(map[x][x] == 0) {
				zeroCnt++;
				
			}
			if(zeroCnt == 5) bingoCnt++;
		}
	}
	
	private static void rightCheck() {
		zeroCnt=0;
		for(int x=0; x<5; x++) {
			if(map[x][4-x] == 0) {
				zeroCnt++;
			}
			if(zeroCnt == 5) bingoCnt++;
		}
	}




}
