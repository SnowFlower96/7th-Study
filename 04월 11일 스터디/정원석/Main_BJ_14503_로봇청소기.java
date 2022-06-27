import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14503_로봇청소기 {

	static int N, M, sr, sc, dir;
	static int map[][];
	static boolean visited[][];
	static int dr[] = {-1, 0, 1, 0}; // 북 동 남 서
	static int dc[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		cnt = 0;
		clean = 1;
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j< M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Move(sr,sc, dir);
		System.out.println(clean);
		
	}
	
	static int cnt, clean;
	
	public static void Move(int r, int c, int dir) {
		int curR = r;
		int curC = c;
		int curDir = dir;
		
		while(true) {
			cnt = 0;
			visited[curR][curC] = true;
//			if (curR == 8 && curC == 4 && curDir == 3)
//				break;
			for (int i = 0; i < 4; i++) {
				int movDir = (curDir + 3) % 4;
				int movR = curR + dr[movDir];
				int movC = curC + dc[movDir];

				if (movR < 0 || movR >= N || movC < 0 || movC >= M)
					continue;

				if (visited[movR][movC] || map[movR][movC] == 1) {
					cnt++;
					if (cnt == 4) {
						int backR = curR + dr[(movDir + 2) % 4];
						int backC = curC + dc[(movDir + 2) % 4];

						if (map[backR][backC] == 1) {
							return;
						} else {
							curR = backR;
							curC = backC;
							curDir = movDir;
							break;
						}
					}
					curDir = movDir;
					continue;
				}

				clean++;
				curR = movR;
				curC = movC;
				curDir = movDir;
				break;
			}
		}
		
		
	}
	
}
