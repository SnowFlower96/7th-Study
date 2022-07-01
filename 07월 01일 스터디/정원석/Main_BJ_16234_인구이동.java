import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16234_인구이동 {

	static int N, L, R;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static boolean[][] visited;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		
		while(true) {
			
			//
			visited = new boolean[N][N];
			
			// 국가가 연결되어 있지 않은 곳의 개수
			int NationCheck = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						//만약 bfs를 한 결과 국경선이 연결되어 있는 곳이 없다면 check를 true처리한다.
						boolean check = bfs(i, j);
						//check가 true라면 NationCheck를 +1 해준다. 
						if(check) NationCheck++;
					}
				}
			}
			
			// 국가가 전부 연결이 되어 있지 않는다면 while문을 끝낸다.
			if(NationCheck==N*N) break;
			result++;
		}
		
		System.out.println(result);
	}

	
	// bfs처리
	private static boolean bfs(int r, int c) {
		// 연결되어 있는 국가 개수
		int NationCnt = 1;
		// 연결되어 있는 국가의 인구 수
		int NationPop = map[r][c];
		
		//bfs를 진행하는 queue
		Queue<Integer> queue = new LinkedList<>();
		
		//연결되어 있는 국가의 위치를 저장할 Queue
		Queue<Integer> pos = new LinkedList<>();
		
		queue.offer(r);
		queue.offer(c);
		
		pos.offer(r);
		pos.offer(c);
		
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int cr = queue.poll();
			int cc = queue.poll();
			
			for(int d = 0 ; d < 4; d++) {
				int sr = cr + dr[d];
				int sc = cc + dc[d];
				if(sr >= 0 && sr < N && sc >= 0 && sc < N && !visited[sr][sc] &&
						L <= Math.abs(map[cr][cc] - map[sr][sc]) && R >= Math.abs(map[cr][cc] - map[sr][sc]) ) {
					
					NationCnt++;
					NationPop += map[sr][sc];
					visited[sr][sc] = true;
					queue.offer(sr);
					queue.offer(sc);
					
					pos.offer(sr);
					pos.offer(sc);
				}
			}
		}
		
		// 만약 연결되어 있는 국가들이 있다면 문제에 나와 있는 수식 사용 하여
		// 각 국가에 있는 인구 수 저장
		if(NationCnt > 1) {
			int pop = NationPop / NationCnt;
			while(!pos.isEmpty()) {
				int cr = pos.poll();
				int cc = pos.poll();
				
				map[cr][cc] = pop;
			}			
			return false; 
		}
		// 만약 연결되어있는 국가가 없으면 true return
		else {
			return true;
		}
		
	}

}
