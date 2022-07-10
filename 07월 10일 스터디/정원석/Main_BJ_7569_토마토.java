import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_7569_토마토 {
	
	static int[] dr = {0, 0, 0, 0, 1, -1};
	static int[] dc = {0, 0, 1, -1, 0, 0};
	static int[] dh = {1, -1, 0, 0, 0, 0};
	static int ripeTomato, M, N, H, time;
	static Queue<Integer> queue;
	static int[][][] box;
	
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ripeTomato = 0;
		
		box = new int[H][N][M];
		queue = new LinkedList<>();
		
		for(int i = 0 ; i < H; i++) {
			for(int j = 0; j< N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int t = 0; t < M; t++) {
					int tomato = Integer.parseInt(st.nextToken());
					box[i][j][t] = tomato;
					if(tomato == 0) ripeTomato++; 
					else if(tomato == 1) {
						queue.offer(i); // 높이
						queue.offer(j); // 세로칸
						queue.offer(t); // 가로칸
					}
				}
			}
		}
		
		if(ripeTomato == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		time = 0;
		
		bfs();
		
		System.out.println(ripeTomato == 0 ? time : -1 );
		
	}



	private static void bfs() {
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			
			for(int i = 0; i < size/3; i++) {
				int h = queue.poll();
				int r = queue.poll();
				int c = queue.poll();
				
				
				for(int d = 0; d < 6; d++) {
					int sh = h + dh[d];
					int sr = r + dr[d];
					int sc = c + dc[d];
					
					if( sh >= 0 && sh < H && sr >= 0 && sr < N && sc >= 0 && sc < M &&
							box[sh][sr][sc] == 0 ) {
						
						box[sh][sr][sc] = 1;
						ripeTomato--;
						
						
						queue.offer(sh);
						queue.offer(sr);
						queue.offer(sc);
					}
				}
				
			}
			time++;
			if(ripeTomato <= 0) return;
			
		}
		
	}
}
