package silver.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_S5_2578_빙고 {

	static int[][] arr = new int[5][5];
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 배열 입력
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 사회자 입력
		int[] input = new int[25];
		int idx = 0;
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				input[idx++] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 사회자가 부른 값을 하나씩 순회
		for(int i = 0; i < input.length; i++) {
			checkArray(input[i]);
			int temp = bingo();
			if(temp >= 3) {
				System.out.println(i + 1);
				break;
			}
		}
	}

	// 사회자가 부른 값에 해당하는 배열값을 0으로
	public static void checkArray(int val) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(arr[i][j] == val) arr[i][j] = 0;
			}
		}
	}
	
	// 빙고인지 확인
	public static int bingo() {
		int cnt = 0;
		int n = 0;
		
		// 0번째 행과 0번째 열만 확인
		// 0번째 행 고정, 각 열마다 아래방향 확인
		for(int i = 0; i < 5; i++) {
			if(arr[0][i] != 0) continue;
			n = 0;
			// 연속으로 5개인지 확인(아래 방향)
			for(int d = 0; d < 5; d++) {
				if(arr[d][i] == 0) {
					n++;
				}
			}
			if(n == 5) cnt++;
		}
		
		// 0번째 열 고정, 각 행마다 우측방향 확인
		for(int i = 0; i < 5; i++) {
			if(arr[i][0] != 0) continue;
			n = 0;
			// 연속으로 5개인지 확인(우측 방향)
			for(int d = 0; d < 5; d++) {
				if(arr[i][d] == 0) {
					n++;
				}
			}
			if(n == 5) cnt++;
		}
		
		// (0, 0) 에서 대각선 확인
		if(arr[0][0] == 0) {
			n = 0;
			for(int i = 0; i < 5; i++) {
				// 연속으로 5개인지 확인(우측 방향)
				if(arr[i][i] == 0) {
					n++;
				}
				if(n == 5) cnt++;
			}			
		}
	
		// (4, 0) 에서 대각선 확인
		if(arr[4][0] == 0) {
			n = 0;
			for(int i = 0; i < 5; i++) {
				// 연속으로 5개인지 확인(우측 방향)
				if(arr[4 - i][i] == 0) {
					n++;
				}
				if(n == 5) cnt++;
			}			
		}
		
		return cnt;
	}
	
}
