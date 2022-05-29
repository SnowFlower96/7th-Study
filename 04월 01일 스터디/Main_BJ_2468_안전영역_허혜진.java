package study0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {

	static int N, maxRain, max;
	static int[][] map;
	static boolean[][] visited;
	//우 하 좌 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		maxRain = -1;
		
		//입력 받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxRain = Math.max(maxRain, map[i][j]);
			}
		}
		
		max = -1;
		for(int rain=0; rain<=maxRain; rain++) {
			visited = new boolean[N][N];
			int cnt = 0; // 안전지역
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] > rain) { // 방문하지 않았거나 물에 잠기지 않는 곳
						cnt += dfs(i, j, rain);
					}
				}
			}
			max = Math.max(max, cnt);
		}
		System.out.println(max);
		
		
	} // end main
	
	
	private static int dfs(int r, int c, int rain) {
		visited[r][c] = true;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc >=N || visited[nr][nc]) continue;
			if(map[nr][nc] > rain) { // 물에 잠기지 않을 때
				dfs(nr, nc, rain);
			}
		}
		return 1;
	}

	
	
	
	
	
	
}
