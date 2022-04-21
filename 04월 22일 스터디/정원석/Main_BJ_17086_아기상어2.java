import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17086_아기상어2 {

	static int N, M, maxDis;
	static int map[][];
	static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1}; //상 우상 우 우하 하 좌하 좌 좌상 
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<int[]> shark;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		shark = new ArrayList<int[]>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					shark.add(new int[] {i, j});
				}
			}
		}
		maxDis = -1;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				if(map[i][j] == 0) {
					bfs(i,j);
				}
			}
		}
		
		
		System.out.println(maxDis);

	}

	
	static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		boolean visited[][] = new boolean[N][M];
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		int safeDistance = 0;
		
		while(!queue.isEmpty()) {
			
			int qSize = queue.size();
			for(int i = 0; i < qSize; i++) {
				int[] pos = queue.poll();
				int cr = pos[0];
				int cc = pos[1];
				
				for(int d = 0 ; d < 8; d++) {
					int sr = cr + dr[d];
					int sc = cc + dc[d];
					if(sr >= 0 && sr < N && sc >= 0 && sc < M && !visited[sr][sc]) {
						for(int[] a: shark) {
							if(a[0] == sr && a[1] == sc) {
								safeDistance++;
								maxDis = Math.max(maxDis, safeDistance);
								return;
							}
						}
						visited[sr][sc] = true;
						queue.offer(new int[] {sr, sc});
					}
				}
			}
			safeDistance++;
		}
	}
}
