

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_10159_저울 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] compare = new int[N+1][N+1];
		
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			
			compare[front][back] = front;
			compare[back][front] = front;
		}
		
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i != j && compare[i][j] == 0 && (compare[i][k] != 0 && compare[k][j] != 0)) {
						compare[i][j] = compare[compare[i][k]][compare[k][j]];
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			int cnt = 0;
			for(int j = 1; j <= N; j++) {
				if(compare[i][j] == 0) {
					cnt++;
				}
			}
			sb.append(cnt-1 + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
