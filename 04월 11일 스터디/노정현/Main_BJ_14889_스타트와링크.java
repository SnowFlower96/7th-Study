package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14889_스타트와링크 {

	static int N, ans;
	static int[][] table;
	static boolean[] select;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		table = new int[N][N];
		select = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		combination(0, 0);
		System.out.println(ans);
	}
	
	private static void combination(int cnt, int start) {
		if(cnt == N / 2) {
			int diff = getDiff();
			ans = Math.min(ans, diff);
			return;
		}
		
		for(int i = start; i < N; i++) {
			select[i] = true;
			combination(cnt + 1, i + 1);
			select[i] = false;
		}
	}

	private static int getDiff() {
		int score1 = 0;
		int score2 = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				
				// team 1
				if(select[i] && select[j]) {
					score1 += table[i][j];
				}
				// team 2
				else if(!select[i] && !select[j]) {
					score2 += table[i][j];					
				}
			}
		}
		
		return Math.abs(score1 - score2);
	}

}
