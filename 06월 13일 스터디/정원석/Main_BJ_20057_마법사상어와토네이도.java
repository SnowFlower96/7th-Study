import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_20057_마법사상어와토네이도 {

	static int N, result;
	
	static int dr[] = {0, -1, -1, -1, 0, 1, 1, 1};
	static int dc[] = {1, 1, 0, -1, -1, -1, 0, 1};
	
	static int[][] map, mapClone;
	
	public static void main(String[] args) throws IOException {
		// 어려웠다고 생각되어진 부분
		// 1. 이해를 잘 못했다. 현재위치에서 이동하면서 모래가 흩뿌려지는데 나는 잘못이해해서 이동하고 한번더 간다음 흩뿌려진다고 생각..? (가장 어려웠던 부분)
		// 2. 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				int m = Integer.parseInt(st.nextToken());
				map[i][j] = m;
			}
		}
		result = 0;
		
		rotation(N/2, N/2);

		System.out.println(result);
	}

	// 중간지점에서 시작
	private static void rotation(int r, int c) {
		
//		for (int t = 0; t < N * N - 1; t++) {
		for (int i = 1; i <= N - 1; i++) {
			if (i == N - 1) {
				// 마지막 구간은 > ^ < 이렇게 3방향 사용
				for (int d = 0; d <= 4; d += 2) {
					for (int j = 0; j < i; j++) {
						r += dr[d];
						c += dc[d];
						SandSpread(r, c, d);
					}
				}
			} else if (i % 2 == 0) {
				// 짝수 구간은 > ^ 2방향 사용
				for (int d = 0; d <= 2; d += 2) {
					for (int j = 0; j < i; j++) {
						r += dr[d];
						c += dc[d];
						SandSpread(r, c, d);
					}
				}
			} else {
				// 홀수 구간은 < v 2방향 사용
				for (int d = 4; d <= 6; d += 2) {
					for (int j = 0; j < i; j++) {
						r += dr[d];
						c += dc[d];
						SandSpread(r, c, d);
					}
				}
			}
		}
//		SandSpread(0,-1,4);
//		}
		
		
	}

	private static void SandSpread(int r, int c, int dir) {

//		System.out.println(r + " " + c + " ");
		
		int sand = map[r][c];
		int totalsand = sand;
		
		//모래가 있던 곳에서 삭제
		map[r][c] = 0;
		int sr = 0; 
		int sc = 0;
		int d = (dir+1) % 8;
		
		
		//방향에 따라 다르게 처리한다.
		
		if (d == (dir + 1) % 8) {
			sr = r + dr[d];
			sc = c + dc[d];
			if (isCheck(sr, sc))
				map[sr][sc] += (int) (totalsand * 0.1);
			else
				result += (int) (totalsand * 0.1);
			sand -= (int) (totalsand * 0.1);
			d = (d+1) %  8;
		}
		
		
		while(d != (dir+1) % 8) {
			
			if (d == dir) {

				sr = r + 2*dr[d];
				sc = c + 2*dc[d];
				if (isCheck(sr, sc))
					map[sr][sc] += (int) (totalsand * 0.05);
				else
					result += (int) (totalsand * 0.05);
				sand -= (int) (totalsand * 0.05);
				
				sr = r + dr[d];
				sc = c + dc[d];
				if (isCheck(sr, sc))
					map[sr][sc] += (int) (sand);
				else {
					result += (int) (sand);
				}
				
			} else if (d == (dir + 7) % 8) {
				sr = r + dr[d];
				sc = c + dc[d];
				if (isCheck(sr, sc))
					map[sr][sc] += (int) (totalsand * 0.1);
				else
					result += (int) (totalsand * 0.1);
				sand -= (int) (totalsand * 0.1);
				
			} else if (d == (dir + 6) % 8 || d == (dir + 2) % 8) {
				sr = r + dr[d];
				sc = c + dc[d];
				if (isCheck(sr, sc))
					map[sr][sc] += (int) (totalsand * 0.07);
				else
					result += (int) (totalsand * 0.07);
				sand -= (int) (totalsand * 0.07);
				
				sr += dr[d];
				sc += dc[d];
				if (isCheck(sr, sc))
					map[sr][sc] += (int) (totalsand * 0.02);
				else
					result += (int) (totalsand * 0.02);
				sand -= (int) (totalsand * 0.02);
				
			} else if (d == (dir + 5) % 8 || d == (dir + 3) % 8) {
				sr = r + dr[d];
				sc = c + dc[d];
				if (isCheck(sr, sc))
					map[sr][sc] += (int) (totalsand * 0.01);
				else
					result += (int) (totalsand * 0.01);
				sand -= (int) (totalsand * 0.01);
				
			}
			d = (d+1) % 8;
		}
		
	}

	private static boolean isCheck(int sr, int sc) {
		if(sr >= 0 && sr < N && sc >= 0 && sc < N) {
			return true;
		}
		return false;
	}
	
	
	
	
}
