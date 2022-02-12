package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_S1_2667_단지번호붙이기 {

	// 좌상 시작, 시계방향
	static final int[] dr = {0, 1, 0, -1};
	static final int[] dc = {1, 0, -1, 0};
	// 배열 크기 및 단지 수
	static int N;
	static boolean[][] arr;
	static int num;
	// 방문 여부
	static boolean[][] isVisited;
	// 단지로 구분된 배열
	static int[][] danji;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 배열의 크기 입력
		N = Integer.parseInt(br.readLine());
		
		// 단지 입력
		arr = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = input.charAt(j) == '1' ? true : false;
			}
		}
				
		// 초기화
		isVisited = new boolean[N][N];
		danji = new int[N][N];
		num = 0;
		
		// 단지로 나누어 danji 배열에 저장 및 단지별 아파트 수 저장
		int[] area = solution();
				
		// 결과 출력
		sb.append(num).append("\n");
		for(int a : area)
			sb.append(a).append("\n");
		System.out.println(sb.toString());
	}

	public static int[] solution() {
		// 단지 번호 입력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 방문하였으면
				if(isVisited[i][j]) continue;
							
				// 아파트가 있으면
				if(arr[i][j]) {
					// 연결된 아파트를 단지 번호로 입력
					num++;
					search(i, j);
				}
			}
		}
		
		// 단지별 면적 계산
		int[] area = new int[num];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int temp = danji[i][j];
				if(temp != 0) area[temp - 1]++;
			}
		}
		
		return area;
	}
	
	public static void search(int r, int c) {
		if(isVisited[r][c]) return;
		
		// 방문 표시 및 단지 표기
		isVisited[r][c] = true;
		danji[r][c] = num;
		
		// 사방으로 탐색
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 경계 안에 있을 때
			if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if(arr[nr][nc])	search(nr, nc);
			}
		}
	}
		
}
