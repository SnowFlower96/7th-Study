import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_11403_경로찾기 {

	static int[][] adjMatrix, dist; 
	static final int INF = Integer.MAX_VALUE / 2 - 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[n][n];
		dist = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(adjMatrix[i][j] > 0) dist[i][j] = adjMatrix[i][j];
				else dist[i][j] = INF;
			}
		}
		
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		
		for(int i =0 ;i < n;i++) {
			for(int j = 0; j < n; j++) {
				if(dist[i][j] != INF) sb.append(1+ " ");
				else sb.append(0 + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

}
