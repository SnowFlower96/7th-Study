import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_4963_섬의개수 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(H == 0 && W == 0) break;
			
			map = new int[H][W];
			for(int i =0 ; i< H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			cnt = 0;
			visited = new boolean[H][W];
			
			for(int i = 0; i< H; i++) {
				for(int j = 0 ; j< W; j++) {
					if(map[i][j] ==1 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			System.out.println(cnt);
			
		}

	}
	
	static int W, H;
	static int map[][];
	static int dr[] = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상 우상 우 우하 하 좌하 좌 좌상
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static boolean visited[][];
	static int cnt;
	
	private static void bfs(int r, int c) { 
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(r); queue.offer(c);
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int curR = queue.poll();
			int curC = queue.poll();
			
			for(int d = 0; d < 8; d++) {
				int movR = curR + dr[d];
				int movC = curC + dc[d];
				
				if(movR < 0 || movR >= H || movC < 0 || movC >= W || visited[movR][movC]
						|| map[movR][movC] == 0) continue;
				
				queue.offer(movR);
				queue.offer(movC);
				visited[movR][movC] = true;
			}
		}
		
		cnt++;
		
	}
	
}
