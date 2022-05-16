import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2589_보물섬 {
	
	static int R, C;
	static int maxDis;
	static char map[][];
	static boolean[][] visited;
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// 육지로만 이동 가능
		// 한칸이동하는데 한시간이 걸림
		// 보물은 서로간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두곳이 나뉘어 묻혀있다.(돌아가면 안됨)
		
		//step01 => map을 반복해서 돌려 육지를 만날때 마다 bfs를 사용하여 최단거리를 구한다. 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
 		
		map = new char[R][C];
		
		maxDis = 0;
		for(int t = 0 ; t < R;t++) {
			map[t] = br.readLine().toCharArray();
		}
		
		for(int i = 0 ; i < R;i++) {
			for(int j = 0 ; j < C; j++) {
				//레벨별 탐색을 육지가 나올 떄마다 한다.
				if(map[i][j] == 'L') {
					visited = new boolean[R][C];
					bfs(i, j);
				}
			}
		}
		
		System.out.println(maxDis);
	}

	private static void bfs(int r, int c) {
		int cnt = 0;
		
		Queue<Integer> queue = new LinkedList<>();
		visited[r][c] = true;
		queue.offer(r);
		queue.offer(c);
		
		while(!queue.isEmpty()) {
			
			int Qsize = queue.size();
			
			//레벨별 탐색을한다.
			for (int i = 0; i < Qsize/2; i++) {
				int cr = queue.poll();
				int cc = queue.poll();
//				System.out.println(cr + " " + cc + " cnt : " + cnt);

				for (int d = 0; d < 4; d++) {
					int sr = cr + dr[d];
					int sc = cc + dc[d];

					if (sr < 0 || sr >= R || sc < 0 || sc >= C || visited[sr][sc] || map[sr][sc] == 'W')
						continue;

					queue.offer(sr);
					queue.offer(sc);
					visited[sr][sc] = true;

				}
			}
			
//			System.out.println();
			maxDis = Math.max(maxDis, cnt);
			cnt++;
			
		}
		
	}
	

	
}
