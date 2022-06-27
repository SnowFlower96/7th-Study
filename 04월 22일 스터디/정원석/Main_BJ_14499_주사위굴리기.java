import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14499_주사위굴리기 {

	static int N, M, cr, cc, K;
	static int[][] map;
	static Dice dice;
	static boolean stop;
	
	static class Dice{
		//지도와 맞다은 면,  북     남      동    서   마주보는 면 
		int current ,  north, south, east, west, jump;
		
		public Dice(int current, int north, int south, int east, int west, int jump) {
			this.current = current;
			this.north = north;
			this.south = south;
			this.east = east;
			this.west = west;
			this.jump = jump;
		}

		@Override
		public String toString() {
			return "Dice [current=" + current + ", north=" + north + ", south=" + south + ", east=" + east + ", west="
					+ west + ", jump=" + jump + "]";
		}
		
		
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cr = Integer.parseInt(st.nextToken());
		cc = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dice = new Dice(0,0,0,0,0,0);
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K; i++) {
			stop = false;
			int roll = Integer.parseInt(st.nextToken());
			switch (roll) {
			case 1:
				rollEast(cr,cc);
				break;
			case 2:
				rollWest(cr, cc);
				break;
			case 3:
				rollNorth(cr, cc);
				break;
			case 4:
				rollSouth(cr, cc);
				break;
			}
			if(!stop) System.out.println(dice.jump);
		}
		
		
	}
	
	private static void rollSouth(int r, int c) {
		if(r+1 < N) {
			if(map[r+1][c] == 0) {
				map[r+1][c] = dice.south;
				int temp = dice.current;
				dice.current = dice.south;
				dice.south = dice.jump;
				dice.jump = dice.north;
				dice.north = temp;
			}else if(map[r+1][c] != 0) { 
				int temp = dice.current;
				dice.current = map[r+1][c];
				dice.south = dice.jump;
				dice.jump = dice.north;
				dice.north = temp;
				map[r+1][c] = 0;
			}
			cr = r + 1;
		}
		else {
			stop = true;
		}
	}
	private static void rollNorth(int r, int c) {
		if(r-1 >= 0) {
			if(map[r-1][c] == 0) {
				map[r-1][c] = dice.north;
				int temp = dice.current;
				dice.current = dice.north;
				dice.north = dice.jump;
				dice.jump = dice.south;
				dice.south = temp;
			}else if(map[r-1][c] != 0) { 
				int temp = dice.current;
				dice.current = map[r-1][c];
				dice.north = dice.jump;
				dice.jump = dice.south;
				dice.south = temp;
				map[r-1][c] = 0;
			}
			cr = r - 1;
		}else {
			stop = true;
		}
	}
	private static void rollEast(int r, int c) {
		if(c+1 < M) {
			if(map[r][c+1] == 0) {
				map[r][c+1] = dice.east;
				int temp = dice.current;
				dice.current = dice.east;
				dice.east = dice.jump;
				dice.jump = dice.west;
				dice.west = temp;
			}else if(map[r][c+1] != 0) {
				int temp = dice.current;
				dice.current = map[r][c+1];
				dice.east = dice.jump;
				dice.jump = dice.west;
				dice.west = temp;
				map[r][c+1] = 0;
			}
			cc = c+1;
		}else {
			stop = true;
		}
	}
	private static void rollWest(int r, int c) {
		if(c-1 >= 0) {
			if(map[r][c-1] == 0) {
				map[r][c-1] = dice.west;
				int temp = dice.current;
				dice.current = dice.west;
				dice.west = dice.jump;
				dice.jump = dice.east;
				dice.east = temp;
			}else if(map[r][c-1] != 0) {
				int temp = dice.current;
				dice.current = map[r][c-1];
				dice.west = dice.jump;
				dice.jump = dice.east;
				dice.east = temp;
				map[r][c-1] = 0;
			}
			cc = c-1;
		}else {
			stop = true;
		}
	}
}
