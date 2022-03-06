package study_03_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main_BJ_1012_유기농배추 {

	
	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	// 우 하 좌 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			map = new int[R][C];
			visited = new boolean[R][C];
			
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			
			int result = 0;
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(!visited[i][j] && map[i][j]==1) {
						dfs(i, j);
						result++;
					}
				}
			}
			
			System.out.println(result);
			
		}
		
	}
	
	public static void dfs(int r, int c) {

		visited[r][c] = true;
		
		for(int d = 0 ; d < 4; d++) {
			int sr = r + dr[d];
			int sc = c + dc[d];
			
			if(sr >= 0 && sr < R && sc >= 0 && sc < C && map[sr][sc] == 1 && !visited[sr][sc]) {
				dfs(sr, sc);
			}
		}
		
	}


}
