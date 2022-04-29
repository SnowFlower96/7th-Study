import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] object = new int[N+1][2];
		int[][] D = new int[N+1][K+1]; 
		for(int i = 1 ; i <= N; i++) {
			st = new StringTokenizer(br.readLine()); 
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			object[i][0] = W;
			object[i][1] = V;
		}
		
		for(int i = 1 ; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				
				//만약 물건의 무게가 현재 무게보다 크다면 그전에있던 값을 그대로 옮긴다.
				if(object[i][0] > j) {
					D[i][j] = D[i-1][j];
				}
				//아니라면 지금 검색하는 무게를 뺀 값의 가치 + 무게의 가치 와 그전의 가치를 비고하여 큰값을 저장한다.
				else {
					D[i][j] = Math.max(D[i - 1][j], D[i - 1][j - object[i][0]] + object[i][1] );
				}
			}
		}
		System.out.println(D[N][K]);

	}

}
