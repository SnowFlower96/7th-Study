

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_11404_플로이드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		final int INF = 10000001;
		
		int dis[][] = new int[n+1][n+1];
		for(int i = 1 ; i <= n; i++) {
			for(int j = 1 ; j <= n; j++) {
				if(i == j) {
					dis[i][j] = 0;
				}else {
					dis[i][j] = INF;
				}
			}
		}
		
		for(int i = 0 ; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if(dis[from][to] == INF) {
				dis[from][to] = w;
			}else {
				dis[from][to] = Math.min(dis[from][to], w);
			}
		}
		
		for(int k = 1 ; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(i == j) dis[i][j] = 0;
					else	dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(dis[i][j] == INF) {
					sb.append(0 + " ");
				}
				else{
					sb.append(dis[i][j] + " ");
				}
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
