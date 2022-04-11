package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14503_로봇청소기 {
		
	static int N, M, cnt;
	static int[][] map;
		
	// 북, 동, 남, 서
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 지도의 크기 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 청소기 초기값 확인
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		// 지도 정보 입력
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 1;
		dfs(r, c, dir);
		
		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int dir) {		
		// 현재 위치 청소
		map[r][c] = -1;
		
		// 현재 방향에서 왼쪽 방향으로 탐색
		for(int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
								
			if(map[nr][nc] == 0) {
				cnt++;
				dfs(nr, nc, dir);
				return;
			}
		}
		
		// 후진
		int backDir = (dir + 2) % 4;
		int br = r + dr[backDir];
		int bc = c + dc[backDir];
		
		if(map[br][bc] != 1) {
			dfs(br, bc, dir);
		}
	}

}
