package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * Study
 * @author jhno96
 * @date 2022. 4. 21.
 */
public class Main_BJ_14499_주사위굴리기 {

	static int N, M, x, y, K;
	static int[][] map;
	static int[] order;
	
	static int[] dice = { Integer.MIN_VALUE, 0, 0, 0, 0, 0, 0 };
	
	// 동, 서, 북, 남
	static final int[] dr = { 0, 0, -1, 1 };
	static final int[] dc = { 1, -1, 0, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); 
		map = new int[N][M];
		order = new int[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		solution();
	}

	private static void solution() {
		for(int dir : order) {
			int nr = x + dr[dir - 1];
			int nc = y + dc[dir - 1];
			
			if(!isMovable(nr, nc)) continue;
			
			x = nr;
			y = nc;
			
			if(dir == 1) {  // 동
				int temp = dice[4];
				dice[4] = dice[6];
				dice[6] = dice[3];
				dice[3] = dice[1];
				dice[1] = temp;
			} else if(dir == 2) {  // 서
				int temp = dice[1];
				dice[1] = dice[3];
				dice[3] = dice[6];
				dice[6] = dice[4];
				dice[4] = temp;
			} else if(dir == 3) {  // 북
				int temp = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[6];
				dice[6] = temp;
			} else if(dir == 4) {  // 남
				int temp = dice[6];
				dice[6] = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[2];
				dice[2] = temp;
			}
			
			if(map[x][y] == 0) map[x][y] = dice[6];
			else {
				dice[6] = map[x][y];
				map[x][y] = 0;
			}

			System.out.println(dice[1]);
		}
	}

	private static boolean isMovable(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M ? true : false;
	}
}
