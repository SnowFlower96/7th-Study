package study_07_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_Main_17070_파이프옮기기1 {

	static int dr[] = { 0, 1, 1};
	static int dc[] = { 1, 1, 0};
	static int direct[] = { 1, 2, 3}; // →,  ↘, ↓
	static int N, cnt;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		dfs(0, 1, 1);
		System.out.println(cnt);
		
	}

	private static void dfs(int r, int c, int dir) {
		if( r == N-1 && c == N-1 ) {
//			System.out.println((r+1) + " "  + (c+1));
//			System.out.println();
			cnt++;
			return;
		}
		
//		System.out.println((r+1) + " "  + (c+1) + " " + dir);
		
		visited[r][c] = true;
		if(dir == 1) {
			LinkedPipe(r, c, dir, 0, 2);
		}else if(dir == 2) {
			LinkedPipe(r, c, dir, 0, 3);
		}else {
			LinkedPipe(r, c, dir, 1, 3);
		}
	}

	private static void LinkedPipe(int r, int c, int dir, int start, int end) {
		for(int i = start; i < end; i++) {
			int sr = r + dr[i];
			int sc = c + dc[i];
			
			if(sr >= 0 && sr < N && sc >= 0 && sc < N && !visited[sr][sc] 
					&& map[sr][sc] != 1) {
				if(i == 1 && (map[sr-1][sc] == 1 || map[sr][sc-1] == 1) ) continue;
				dfs(sr, sc, i+1);
				visited[sr][sc] = false;
			}
		}

		
	}

}
