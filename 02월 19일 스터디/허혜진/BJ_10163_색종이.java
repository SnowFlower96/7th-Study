package study0219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10163_색종이 {

	static int N, result[], map[][];
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[101][101];
		
		N = Integer.parseInt(br.readLine());
		result = new int[N];
		
		for(int t=1; t<=N; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			if(N == 1) {
				System.out.println(w*h);
				return;
			}
			
			for(int i=x; i<=w+x-1; i++) {
				for(int j=y; j<=h+y-1; j++) {
					map[i][j] = t;
				}
			}
			
		}
		
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				if(map[i][j] != 0) {
					result[map[i][j]-1]++;
				}
			}
		}
		for(int ans:result) {
			System.out.println(ans);
		}
		
	}

}
