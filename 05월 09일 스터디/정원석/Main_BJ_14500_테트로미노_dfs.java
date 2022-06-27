import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_14500_테트로미노_dfs {

					// 제자리 상 하 우 좌
	static int dr[] = { 0, 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 0, 1, -1 };
	static int N, M;
	static int maxSum;
	static int[][] map;
	static boolean[][] visited;

	// 테트로미노 모양 전부 만들기 (대칭, 회전 포함) (왼쪽부터 시작으로 하는 기준 잡음)	
	static final int[][] tetrominoWOO = { { 3, 3, 4, 2 }, { 3, 1, 2, 2 }, { 3, 1, 2, 3 }, { 2, 3, 4, 2 } };
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];
		map = new int[N][M];
		maxSum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				
				visited[i][j] = true;
				dfs(i,j,0,0);
				visited[i][j] = false;
				
				WooSum(i,j);
			}
		}
		System.out.println(maxSum);

	}

	//길이가 4인 dfs를 모든 방향으로 돌리면 ㅗ,ㅓ,ㅏ,ㅜ를 제외한 모든 테트로미노를 구할 수 있다.
	private static void dfs(int i, int j, int cnt, int sum) {
		if(cnt == 4) {
			maxSum = Math.max(sum, maxSum);
			return;
		}
		for(int d = 1; d <= 4; d++) {
			int sr = i + dr[d];
			int sc = j + dc[d];
			
			if(sr < 0 || sr >= N || sc < 0 || sc >= M) continue;
			
			if(visited[sr][sc]) continue;
			
			visited[sr][sc] = true;
			dfs(sr, sc, cnt+1, sum+map[sr][sc]);
			visited[sr][sc] = false;
			
		}
		
	}

	//ㅗ,ㅓ,ㅏ,ㅜ의 합을 구한다.
	private static void WooSum(int r, int c) {

		for (int i = 0; i < tetrominoWOO.length; i++) {
			int sum = 0;

			int sr = r;
			int sc = c;
			sum += map[sr][sc];

			for (int j = 0; j < tetrominoWOO[i].length; j++) {
				sr = sr + dr[tetrominoWOO[i][j]];
				sc = sc + dc[tetrominoWOO[i][j]];
				if (j == 2)
					continue;
				if (sr < 0 || sr >= N || sc < 0 || sc >= M)
					break;

				sum += map[sr][sc];
			}
			maxSum = Math.max(maxSum, sum);
		}

	}
	
}
