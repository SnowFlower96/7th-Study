import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1743_음식물피하기 {

	static int R, C, K;
	static int[][] trash, map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	
	
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
		
		Queue<Integer> queue = new LinkedList<>();
		boolean visited[][] = new boolean[R+1][C+1];
		
		int max_sum = 0;
		for (int t = 0; t < K; t++) {
			int sum = 1;
			
			if(visited[trash[t][0]][trash[t][1]]) continue;
			
			queue.offer(trash[t][0]);
			queue.offer(trash[t][1]);
			
			visited[trash[t][0]][trash[t][1]] = true;
			
			while (!queue.isEmpty()) {
				int r = queue.poll();
				int c = queue.poll();

				for (int i = 0; i < 4; i++) {
					int sr = r + dr[i];
					int sc = c + dc[i];

					if (sr > 0 && sr <= R && sc > 0 && sc <= C && map[sr][sc] == 1 && !visited[sr][sc]) {
						sum++;
						queue.offer(sr);
						queue.offer(sc);
						visited[sr][sc] = true;
					}

				}
			}
			
			max_sum = Math.max(max_sum, sum);
		}
		
		System.out.println(max_sum);
	}

}
