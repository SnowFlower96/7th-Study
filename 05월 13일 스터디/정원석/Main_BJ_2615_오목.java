import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2615_오목 {

	static int[][] board;
	static int[] dr = {1 ,1, 0, -1};
	static int[] dc = {0, 1, 1, 1};
	static int[] drChecked = { -1, -1, 0, 1};
	static int[] dcChecked = { 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new int[20][20];
		
		for(int i = 1 ; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j < 20; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1 ; i < 20; i++) {
			for(int j = 1; j < 20; j++) {
				if(board[i][j] != 0 && concaveCheck(i, j, board[i][j])) {
					System.out.println(board[i][j]);
					System.out.println(i +" " + j);
					System.exit(0);
				}
			}
		}
		System.out.println(0);

	}

	private static boolean concaveCheck(int r, int c, int ball) {
		for(int i = 0 ; i < 4; i++) {
			int cnt = 1;
			int sr = r + dr[i];
			int sc = c + dc[i];
			int srChecked = r + drChecked[i];
			int scChecked = c + dcChecked[i];
			
			if(srChecked > 0 && srChecked <= 19 && scChecked > 0 && scChecked <= 19 && board[srChecked][scChecked] == ball) continue;
			
			while(true) {
				if(sr < 1 || sr > 19 || sc < 1 || sc > 19 || board[sr][sc] != ball) break;
				
				cnt++;
				sr += dr[i];
				sc += dc[i];
				if(cnt == 5) {
					if(sr >= 1 && sr < 20 && sc >= 1 && sc < 20 && board[sr][sc] == ball) break;
					
					return true;
				}
			}
		}
		
		return false;
		
	}

}
