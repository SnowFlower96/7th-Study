package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10163_색종이_허유민 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[1001][1001];
		//int[] paper = new int[N];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			for (int j = r; j < r+w; j++) {
				for (int j2 = c; j2 < c+h; j2++) {
					map[j][j2] = i;
					//paper[i]++;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 0; j < 1001; j++) {
				for (int j2 = 0; j2 < 1001; j2++) {
					if(map[j][j2] == i) {
						cnt++;
					}else {
						
					}
				}
			}
			System.out.println(cnt);
		}

	}

}
