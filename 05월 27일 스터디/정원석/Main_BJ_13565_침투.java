import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_13565_침투 {
	
	static int R, C;
	static int[] dr = { -1, 1, 0, 0};
	static int[] dc = { 0, 0, 1, -1};
	static boolean flag;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		flag = false;
		for(int i = 0 ; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j) -'0' ;
			}
		}
		
		visited = new boolean[R][C];
		for(int i = 0 ; i < C; i++) {
			if(map[0][i] == 0) {
				bfs(0, i);
			}
		}
		System.out.println("NO");
	}
	
	static int[][] map;
	static boolean[][] visited;
	
	private static void dfs(int r, int c) {
		if(r == R-1) {	
			System.out.println("YES");
			System.exit(0);	
		}
		
		
		visited[r][c] = true;
		for(int i = 0 ; i < 4 ; i++) {
			int sr = r + dr[i];
			int sc = c + dc[i];
			
			if(sr < 0 || sr >= R || sc < 0 || sc >= C || visited[sr][sc] || map[sr][sc] == 1) continue;
			
			dfs(sr,sc);
		}
		
	}
	
	private static void bfs(int r, int c) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(r);
		queue.offer(c);
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int cr = queue.poll();
			int cc = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int sr = cr + dr[i];
				int sc = cc + dc[i];
				
				if(sr < 0 || sr >= R || sc < 0 || sc >= C || visited[sr][sc] || map[sr][sc] == 1) continue;
				queue.offer(sr);
				queue.offer(sc);
				visited[sr][sc] = true;
				if(sr==R-1) {
					System.out.println("YES");
					System.exit(0);	
				}
			}
		}
		
	}
	
}
