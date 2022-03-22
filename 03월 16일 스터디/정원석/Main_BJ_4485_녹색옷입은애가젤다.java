package study_03_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_4485_녹색옷입은애가젤다 {
	
	static class Vertex implements Comparable<Vertex>{
		int r, c, minDistance; // 정점 번호, 출발지에서 자신으로의 최소비용
		
		public Vertex(int r, int c, int minDistance) {
			super();
			this.r = r;
			this.c = c;
			this.minDistance = minDistance;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return this.minDistance - o.minDistance;
		}
		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {


			
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tc = 0;
		while (true) {
			
			int N = Integer.parseInt(in.readLine());

			if(N == 0) {
				break;
			}
			
			int[][] cave = new int[N][N];
			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] distance = new int[N][N]; // 출발지에서 자신으로 오는 최소 비용

			for (int i = 0; i < N; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}

			boolean[][] visited = new boolean[N][N]; // 최소비용 확정여부
			PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();

			distance[0][0] = cave[0][0];
			pQueue.add(new Vertex(0, 0, cave[0][0]));

			while (!pQueue.isEmpty()) {
				// 단계1 : 최소비용이 확정되지 않은 정점중 최소비용의 정점 선택
				Vertex current = pQueue.poll();

				if (visited[current.r][current.c])
					continue;

				visited[current.r][current.c] = true;

				for (int i = 0; i < 4; i++) {
					int nr = current.r + dr[i];
					int nc = current.c + dc[i];

					if (nr >= 0 && nr < N && nc >= 0 && nc < N && 
							distance[nr][nc] > distance[current.r][current.c] + cave[nr][nc]) {
						
						distance[nr][nc] = distance[current.r][current.c] + cave[nr][nc];
						pQueue.add(new Vertex(nr, nc, distance[nr][nc]));
						
					}
				}
				
			}
			
			tc++;

			System.out.println("Problem " + tc + ": " + distance[N - 1][N - 1]);

		}
	}

}
