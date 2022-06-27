import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_20058_마법사상어와파이어스톰 {

	static int N, Q, maxCnt;
	static int[][] map;
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map = new int[1 << N][1 << N];
		for(int i = 0 ; i < map.length;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		//마법시전
		maxCnt = 0;
		
		for(int i = 1 ; i <= Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			rotation(L);
			
			meltIce();
			
//			if(i != Q) bfs(false);
//			else
			if(i == Q)	bfs(true);
			
//			for(int t = 0; t < map.length;t++) {
//				System.out.println(Arrays.toString(map[t]));
//			}
//			System.out.println();
		}
		
		int sum = 0;
		
		for(int i = 0 ; i < map.length;i++) {
			for(int j = 0 ; j< map.length;j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
		System.out.println(maxCnt);
	}

	private static void meltIce() {
		boolean[][] melt = new boolean[1<<N][1<<N];
		
		for(int i = 0 ; i < map.length; i++) {
			for(int j = 0; j< map.length; j++) {
				
				if(map[i][j] != 0) {
					int cnt = 0;
					for(int d =0 ; d < 4; d++) {
						int sr = i + dr[d];
						int sc = j + dc[d];
						if(sr >= 0 && sr < map.length && sc >= 0 && sc < map.length && map[sr][sc] != 0) {
							cnt++;
						}
					}
					if(cnt <= 2) {
						melt[i][j] = true;
					}
				}
			}
		}
		for(int i = 0; i < map.length; i++) {
			for(int j = 0 ; j < map.length; j++) {
				if(melt[i][j]) map[i][j]--;
			}
		}
	}
	

	private static void bfs(boolean last) {
		
		boolean visited[][] = new boolean[map.length][map.length];
		
		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				
				if (map[i][j] != 0) {
					int cnt = 1;
					Queue<Integer> queue = new LinkedList<>();
					visited[i][j] = true;
					queue.offer(i);
					queue.offer(j);
					
					
					while (!queue.isEmpty()) {
						int r = queue.poll();
						int c = queue.poll();

						for (int d = 0; d < 4; d++) {
							int sr = r + dr[d];
							int sc = c + dc[d];
							if (sr >= 0 && sr < map.length && sc >= 0 && sc < map.length && !visited[sr][sc]
									&& map[sr][sc] != 0) {
								cnt++;
								queue.offer(sr);
								queue.offer(sc);
								visited[sr][sc] = true;
							}

						}
					}
					if(last) {
						maxCnt = Math.max(maxCnt, cnt);
					}

				}
			}
		}
	}

	// 회전하는 메서드
	private static void rotation(int L) {
		int num = 1 << L;
//		System.out.println(num);
		if(L == 0) return;
		
		//복사한 이차원배열을 잠시 저장할 곳을 만든다. 
		int clone[][] = new int[1<<N][1<<N];
		
		for(int i = 0 ; i < map.length; i++) {
			clone[i] = map[i].clone();
		}
		

		for(int i = 0; i < 1 << N; i = i + num) {
			for(int j = 0; j < 1 << N; j = j + num) {
				
				for(int r = 0 ;r < num; r++) {
					for(int c = 0 ; c < num ; c++) {
						map[r+i][c+j] = clone[i+num-1-c][r+j];
					}
				} 
			}
		}
		
	}

}
