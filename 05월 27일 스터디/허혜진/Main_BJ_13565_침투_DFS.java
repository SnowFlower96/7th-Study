package study0527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_13565_침투_DFS {

	static int M, N, map[][];
	// 상 우 하 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean visited[][], isFinish;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			if (map[0][i] == 0)
				dfs(0, i);
		}
		System.out.println("NO");

	}

	private static void dfs(int r, int c) {

		// 기저조건
		if (r == M - 1) { 
			
			System.out.println("YES");
			System.exit(0);
		}

		for (int d = 0; d < 4; d++) {

			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= M || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) continue;
			visited[nr][nc] = true;
			dfs(nr, nc);
		}

	}

}
