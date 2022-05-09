import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1743_음식물피하기_dfs {

	static int R, C, K, cnt, max;
	static int[][] trash, map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	static boolean[][] visited;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken()); 
		
		trash = new int[K][2];
		map = new int[R+1][C+1]; 
		
		for(int i = 0 ; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
			trash[i][0] = r;
			trash[i][1] = c;
		}
		
		max = 0;
		visited = new boolean[R+1][C+1];
		
		for(int i = 0 ; i < K; i++) {
			cnt = 0 ;
			dfs(trash[i][0], trash[i][1]);
			max = Math.max(cnt, max);
		}
		
		System.out.println(max);
		
			
	}
	
	
	static void dfs(int r, int c) {
		
		visited[r][c] = true;
		cnt++;
		
		for(int i = 0 ; i < 4; i++) {
			int sr = r + dr[i];
			int sc = c + dc[i];
			
			if(sr <= R && sr >0 && sc <= C && sc > 0 && !visited[sr][sc] && map[sr][sc] == 1) {
				dfs(sr,sc);
			}
		}
		
		
	}

}
