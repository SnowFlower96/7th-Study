package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2468_안전영역 {

	static int N;
	static int[][] map;
	static boolean[][] isVisited;
	
	// 시계방향
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solution());
	}

	private static int solution() {
		int result = 0;
		
		int rain = 0;
		while(true) {
			int cnt = 0;
			int left = N * N;
			isVisited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!isVisited[i][j] && map[i][j] > rain) {
						bfs(i, j, rain);
						cnt++;
					}
					else {
						left--;
					}
				}
			}
			
			result = Math.max(result, cnt);
			rain++;
			
			if(left == 0) break;
		}
			
		
		return result;
	}

	private static void bfs(int r, int c, int rain) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] { r, c });
		isVisited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				// 경계 체크
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(map[nr][nc] > rain && !isVisited[nr][nc]) {
					isVisited[nr][nc] = true;
					queue.offer(new int[] { nr, nc });
				}
			}
		}
	}
	
}

