package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2628_종이자르기 {

	static int R, C, N, ans, map[][];
	static int width;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[R][C]; // 0으로 초기화

		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int rc = Integer.parseInt(st.nextToken());
			int cutNum = Integer.parseInt(st.nextToken());
			
			if(rc == 0) rCheck(cutNum);
			if(rc == 1) cCheck(cutNum);
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 0) ans++; 
			}
		}
		System.out.println(ans);
	}

	private static void rCheck(int num) { // 가로
		if(num <= R/2 && num !=0) {
			for(int i=0; i<num; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] = 1;
				}
			}
		}
		if(num > R/2) {
			for(int i=num; i<R; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] = 1;
				}
			}
		}
	}

	private static void cCheck(int num) { // 세로
		if(num <= C/2 && num !=0) {
			for(int i=0; i<num; i++) {
				for(int j=0; j<R; j++) {
					map[j][i] = 1;
				}
			}
			
		}
		if(num > C/2) {
			for(int i=num; i<C; i++) {
				for(int j=0; j<R; j++) {
					map[j][i] = 1;
				}
			}
		}
		
	}

}
