package study0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_4963_섬의개수 {

	static int W, H, ans, map[][];
	static boolean[][] visited;
	//상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if(W==0 && H==0) break;
			
			map = new int[H][W];
			visited = new boolean[H][W];
			
			// 입력 받기
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(map[i][j] == 1 && !visited[i][j]) { // 땅이고, 방문하지 않았으면
						ans += dfs(i, j);
					}
				}
			}
			System.out.println(ans);
		}
	}
	
	private static int dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int d=0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=0 && nr<H && nc>=0 && nc<W) {	// 경계 내
				if(map[nr][nc] == 1 && !visited[nr][nc]) {	// 땅이고, 방문하지 않았으면
					dfs(nr, nc);
				}
			} 
		}
		return 1;
	}

}
