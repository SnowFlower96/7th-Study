import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_안전영역 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		int maxWaterLevel = 0;
		for(int i = 0 ; i < N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 영역중의 최대 높이를 저장한다.
				maxWaterLevel = Math.max(maxWaterLevel, map[i][j]);
			}
		}
		
		// 최다 안전영역의 개수를 저장할 변수
		// 잠긴 지역이 없을 경우 1을 출력(모든 영역이 안전영역이기 때문에) 
		int maxSafeZone = -1;
		
		// 잠긴 수위가 1부터 maxWaterLevel까지 반복한다.
		// 반복하면서 안전영역의 개수가 가장 클때 maxSafeZone에 저장한다.
		for(int w = 0; w <= maxWaterLevel; w++) {
			
			//높이를 높여갈때마다 safeZoneNumber을 초기화한다.
			safeZoneNumber = 0;
			//높이를 높여갈때마다 방문한 지역들을 초기화한다. 
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					//만약 현재 수위보다 높이가 높고 방문하지 않은 지역일 경우 bfs를 돌린다.
					if(map[i][j] > w && !visited[i][j]) {
						// (bfs || dfs)가 돌아가면서 safeZoneNuber + 1이 된다.
//						bfs(i, j, w);
						dfs(i, j, w);
						// safeZoneNumber에 +1을 해준다.
						safeZoneNumber++;
					}
				}
			}
			// 가장 개수가 많은 지역을 저장한다.
			maxSafeZone = Math.max(maxSafeZone, safeZoneNumber);
			
		}
		
		
		System.out.println(maxSafeZone);
		
	}
	
	static int safeZoneNumber, N;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int[][] map;
	
	//bfs함수
	public static void bfs(int r, int c, int waterlevel) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(r);
		queue.offer(c);
		
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int curR = queue.poll();
			int curC = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int movR = curR + dr[d];
				int movC = curC + dc[d];
				
				//경계범위를 넘거나 waterlevel보다 작거나 같을경우 또는 방문했던 지역인 경우 continue를 시킨다.
				if(movR < 0 || movR >= N || movC < 0 || movC >= N 
						|| map[movR][movC] <= waterlevel
						|| visited[movR][movC]) continue;
				
				//만약 위의 조건을 전부 만족하지 않는다면 방문했다는 표시와 queue에 좌표를 넣는다.
				visited[movR][movC] = true;
				queue.offer(movR);
				queue.offer(movC);
				
			}
		}
	}

	
	public static void dfs(int r, int c, int waterlevel) {
		
		visited[r][c] = true;
		
		for(int d = 0 ; d < 4; d++) {
			int movR = r + dr[d];
			int movC = c + dc[d];
			
			if(movR < 0 || movR >= N || movC < 0 || movC >= N 
					|| map[movR][movC] <= waterlevel
					|| visited[movR][movC]) continue;
			
			dfs(movR, movC, waterlevel);
			
		}
	}
}
