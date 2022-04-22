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
 * @date 2022. 4. 19.
 */
public class Main_BJ_17086_아기상어2 {

	// 8방향 - 12시부터 시계방향
	static final int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		// bfs 탐색용 큐
		Queue<int[]> queue = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 상어이면 bfs 큐에 추가
				if(map[i][j] == 1) queue.offer(new int[] { i, j });
			}
		}
		
		// 안전거리를 저장할 배열 
		int[][] dist = new int[N][M];
		int ans = Integer.MIN_VALUE;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < 8; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				// 경계체크, 상어인지 혹은 거리가 측정된 곳인지 확인
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || dist[nr][nc] != 0 || map[nr][nc] == 1) continue;
				
				dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
				ans = Math.max(dist[nr][nc], ans);
				queue.offer(new int[] { nr, nc });
			}
		}
		
		System.out.println(ans);
	}

}
