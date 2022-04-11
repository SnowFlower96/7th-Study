package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * Study
 * @author jhno96
 * @date 2022. 4. 7.
 */
public class Main_BJ_4963_섬의개수 {

	static int w, h;
	static int[][] map;
	
	static final int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static final int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			
			if(w == 0 && h == 0) break;
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			sb.append(bfs()).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs() {
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[h][w];
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(isVisited[i][j]) continue;
				
				isVisited[i][j] = true;
				if(map[i][j] == 1) {
					cnt++;
					
					queue.clear();
					queue.offer(new int[] { i, j });
					
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						
						for(int d = 0; d < 8; d++) {
							int nr = cur[0] + dr[d];
							int nc = cur[1] + dc[d];
							
							if(nr < 0 || nr >= h || nc < 0 || nc >= w || isVisited[nr][nc]) continue;
							
							if(map[nr][nc] == 1) {
								isVisited[nr][nc] = true;
								queue.offer(new int[] { nr, nc });
							}
						}
					}
				}
			}
		}
		
		return cnt;
	}

}
