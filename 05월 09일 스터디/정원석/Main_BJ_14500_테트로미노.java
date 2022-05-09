import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_14500_테트로미노 {

					// 제자리 상 하 우 좌
	static int dr[] = { 0, 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 0, 1, -1 };
	static int N, M;
	static int maxSum;
	static int[][] map;

	// 테트로미노 모양 전부 만들기 (대칭, 회전 포함) (왼쪽부터 시작으로 하는 기준 잡음)
	static final int[][] tetromino = { { 3, 3, 3 }, { 2, 3, 1 }, { 2, 2, 3 }, { 2, 3, 2},
			{ 3, 1, 3 }, { 1, 3, 1 }, { 3, 2, 3 }, { 3, 2, 2 }, { 3, 3, 1 }, 
			{ 1, 3, 3 }, { 3, 1, 1 }, { 4, 2, 2 }, { 2, 3, 3 }, { 3, 3, 2 }, { 2, 2, 2 } };
	
	static final int[][] tetrominoWOO = { { 3, 3, 4, 2 }, { 3, 1, 2, 2 }, { 3, 1, 2, 3 }, { 2, 3, 4, 2 } };
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		maxSum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//0,0 부터 N-1, M-1까지 반복문을 돌면서 sum을 구한다.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				Sum(i,j);
			}
		}
		System.out.println(maxSum);

	}

	// 미리만들어놓은 테트로미노 배열들을 활요하여 sum을 구한다.
	private static void Sum(int r, int c) {

		for (int i = 0; i < tetromino.length; i++) {
			int sum = 0;

			int sr = r;
			int sc = c;
			//현재위치를 sum에 저장한다.
			sum += map[sr][sc];

			//경계안에 있다면 그위치에 있는 값을 sum에 더한다.
			for (int j = 0; j < tetromino[i].length; j++) {
				sr = sr + dr[tetromino[i][j]];
				sc = sc + dc[tetromino[i][j]];
				if (sr < 0 || sr >= N || sc < 0 || sc >= M)
					break;

				sum += map[sr][sc];
			}
			// 테트로미노를 전부 체크했을때 가장 큰값을 maxSum에 넣는다.
			maxSum = Math.max(sum, maxSum);
		}
		
		for (int i = 0; i < tetrominoWOO.length; i++) {
			int sum = 0;

			int sr = r;
			int sc = c;
			sum += map[sr][sc];

			for (int j = 0; j < tetrominoWOO[i].length; j++) {
				sr = sr + dr[tetrominoWOO[i][j]];
				sc = sc + dc[tetrominoWOO[i][j]];
				// j가 2일때 (원래 있던곳으로 돌아갈때)는 sum에 더하지 않는다.
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
