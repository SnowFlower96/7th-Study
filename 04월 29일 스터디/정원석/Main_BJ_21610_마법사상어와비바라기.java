import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_21610_마법사상어와비바라기 {

	static int N, M;
	static int[][] map;
	static boolean[][] disappear;
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1}; 
	static Queue<Integer> queue, Cloud;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		queue = new LinkedList<>();
		Cloud = new LinkedList<>();
		
		Cloud.offer(N-1); Cloud.offer(0); Cloud.offer(N-1); Cloud.offer(1);
		Cloud.offer(N-2); Cloud.offer(0); Cloud.offer(N-2); Cloud.offer(1); 
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			queue.offer(Integer.parseInt(st.nextToken())); // 이동정보 뱡향 출력
			queue.offer(Integer.parseInt(st.nextToken())); // 이동정보 거리 출력
		}
		
		for(int i = 0 ; i < M; i++) {
			// 구름 사라진 지점 체크
			disappear = new boolean[N][N];
			
			//구름 이동
			moveCloud();
			
			//구름생성
			makeCloud();
//			for(int j = 0 ; j < N; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
//			System.out.println();
//			System.out.println(Cloud.toString());
//			System.out.println("=========================");
		}
		
		int result = 0;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);

	}


	private static void moveCloud() {
		
		int d = queue.poll(); //이동방향
		int s = queue.poll(); //이동거리
//		System.out.println((d + " " + s));
		Queue<Integer> waterUp = new LinkedList<>();
		
		while(!Cloud.isEmpty()) {
			
			//step01 구름을 d방향으로 s칸 이동한다. 연결되어 있으므로 고려하여 이동시킨다.
			int sr =  dr[d] < 0 ? (N + ( (dr[d] * s) + Cloud.poll() ) % N ) % N : ( (dr[d] * s) + Cloud.poll() ) % N;
			int sc =  dc[d] < 0 ? (N + ( (dc[d] * s) + Cloud.poll() ) % N ) % N : ( (dc[d] * s) + Cloud.poll() ) % N;
//			System.out.println(sr + " " + sc);
			
			//step02 바구니에 저장된 물의 양을 1 증가 시킨다.
			map[sr][sc]++;
			
			//step03 구름을 사라지게한다.(Cloud.poll()하면서 자동으로 진행)
			
			//사라질 구름들은 구름이 생기지 않으므로 체크해둔다. 
			disappear[sr][sc] = true;
			
			waterUp.offer(sr);
			waterUp.offer(sc);
			
		}
		//물복사 버그
		copyBug(waterUp);
//		for(int i = 0 ; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
//		
//		for(int i = 0 ; i < N; i++) {
//			System.out.println(Arrays.toString(disappear[i]));
//		}
//		System.out.println();
	}

	//step 04. 물복사 버그 
	private static void copyBug(Queue<Integer> waterUp) {
		
		
		while(!waterUp.isEmpty()) {
			int r = waterUp.poll();
			int c = waterUp.poll();
			
			for (int i = 2; i < 9; i = i + 2) {
				int mr = r + dr[i];
				int mc = c + dc[i];
				// 경계안에 있는 대각선이고 물이 있다면 물을 두 배 해준다.
				if (mr >= 0 && mr < N && mc >= 0 && mc < N && map[mr][mc] != 0) {
					map[r][c]++;
				}
			}
		}
	}

	// step 05. 구름 생성
	private static void makeCloud() {
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 맵을 전부 탐색해서 물의 양이 2이상이고 구름이 사라지지 않았다면 2를 줄이고 구름을 생성시킨다.
				if(map[i][j] >= 2 && !disappear[i][j]) {
					map[i][j] -= 2;
					Cloud.offer(i);
					Cloud.offer(j);
				}
			}
		}
		
	}
}


















