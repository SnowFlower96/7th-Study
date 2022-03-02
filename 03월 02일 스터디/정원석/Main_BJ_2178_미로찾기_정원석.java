package study_03_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2178_미로찾기_정원석 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] miro = new char[N+1][M+1];
		result = new int[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			String temp = br.readLine();
			for(int j = 1; j <= M; j++) {
				miro[i][j] = temp.charAt(j-1);
			}
		}
		
		bfs(miro);
		System.out.println(result[N][M]);
	}
	
	static int N, M;
	//사방탐색 : 			우 하  좌  상
	static int[] dr = { 0, 1, 0, -1};
	static int[] dc = { 1, 0, -1, 0};
	
	//각 단계마다 거리의 수를 저장하기 위한 배열을 생성
	static int[][] result;
	
	//bfs 탐색을 위한 메서드
	public static void bfs(char[][] miro) {
		
		boolean visited[][] = new boolean[N+1][M+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//시작점이 1,1이기 때문에 queue에 1,1을 offer한다.
		//두번 넣는 이유는 첫번째 넣는 좌표값이 r을 의미하고,
		//			   두번째 넣는 좌표값이 c를 의미한다.
		queue.offer(1);
		queue.offer(1);
		
		//방문 여부를 확인하기 위하여 1,1좌표를 true로 바꿔준다.
		visited[1][1] = true;
		
		//시작부터 거리는 1이므로 시작점인 1,1 값에 1을 삽입한다.
		result[1][1] = 1;
		while(!queue.isEmpty()) {
			//현재 위치를 파악하기 위하여  queue.poll을 두번한다.
			int r = queue.poll();
			int c = queue.poll();
			//현재 위치까지의 거리를 저장할 변수
			int current = result[r][c];
			
			for(int d = 0; d < 4; d++) {
				// 탐색을 돌릴 다음 위치를 저장한다.
				int sr = r + dr[d];
				int sc = c + dc[d];
				
				// 경계를 벗어나거나 다음 값이 '1'이 아닌 '0'일 경우 넘어간다.
				if(sr < 1 || sr > N || sc < 1 || sc > M || miro[sr][sc] == '0') continue;
				
				//만약 방문하지 않은 곳이라면
				if(!visited[sr][sc]) {
					// 탐색 중인 위치에 거리를 +1하여 저장한다.
					result[sr][sc] = current + 1;
					// 탐색중인 위치에 방문했다는 표시를 한다.
					visited[sr][sc] = true;
					// 탐색중인 위치의 좌표를 queue에 저장한다.
					queue.offer(sr);
					queue.offer(sc);
				}
			}
			
		}
		
	}

}
