import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_3190_뱀 {
	
	static class Pos{
		int r; 
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
		
		@Override
		public boolean equals(Object obj) {
			Pos other = (Pos) obj;
			if (c == other.c && r == other.r)
				return true;
			return false;
		}
		
	}
	
	static class direct{
		int sec;
		char dir;
		
		public direct(int sec, char dir) {
			this.sec = sec;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "direct [sec=" + sec + ", dir=" + dir + "]";
		}
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // map 크기
		int[][] map = new int[N+1][N+1];
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		
		StringTokenizer st;
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = 4; // 맵에 사과를 표현
			
		}
		
		int D = Integer.parseInt(br.readLine());
		Queue<direct> change = new LinkedList<>();
		for(int i = 0; i< D; i++) {
			st = new StringTokenizer(br.readLine());
			
			int sec = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			
			change.offer(new direct(sec, dir));
		}
		
		int dir_snake_D[] = {0, 1, 2, 3}; //뱀의 가는 방향을 선택 (상 = 0  우 = 1 하 = 2 좌 = 3)
		int dir_snake_L[] = {0, 3, 2, 1};//뱀의 가는 방향을 선택 (상 좌 하 우)
		
		int dr[] = {-1, 0, 1, 0}; // 가는방향으로 이동
		int dc[] = {0, 1, 0, -1};
		
//		int dr_D[] = { 0, 1, 0, -1 };   //뱁의 바꿀 방향(우회전) 상 => 우, 우 => 하, 하 => 좌, 좌 => 상
//		int dc_D[] = { 1, 0, -1, 0 };
//		
//		int dr_L[] = { 0, -1, 0, 1 };	//뱀의 바꿀 방향(좌회전) 상 => 좌, 좌 => 하, 하 => 우, 우 => 상
//		int dc_L[] = { -1, 0, 1, 0 };
		
		int cnt_sec = 0; //시간을 기록
		
		Deque<Pos> snake = new LinkedList<Pos>();
		snake.offer(new Pos(1, 1)); // snake는 1,1 에서 시작한다. 
		int move_snake = 1; // snake는 처음에는 오른쪽을 향한다. 
		
		int index = 1;
		while(true) {
			cnt_sec++;

			int mr = snake.peek().r + dr[move_snake];
			int mc = snake.peek().c + dc[move_snake];

			if(mr < 1 || mr > N || mc < 1 || mc > N || snake.contains(new Pos(mr, mc))) {
				break;
			} //만약 벽에 닿거나 자기 자신에 닿으면 while문에서 빠져나온다.
			
			
			if(!change.isEmpty() && change.peek().sec == cnt_sec) {
				char dir = change.poll().dir;
				
				if(dir == 'D') {
					move_snake = dir_snake_D[(index + 1) % 4];
					index = (index + 1) % 4;
				}else {
					move_snake = dir_snake_L[(index + 1) % 4];			
					index = (index + 1) % 4;
				}
			}
			

			if(map[mr][mc] == 4) {
				map[mr][mc] = 0;
				snake.push(new Pos(mr,mc));
			}else {
				snake.push(new Pos(mr,mc));
				snake.removeLast();
			}
			
			
		}
		
		System.out.println(cnt_sec);

	}

}
