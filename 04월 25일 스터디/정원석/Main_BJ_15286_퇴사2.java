import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_15286_퇴사2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] maxProfit = new int[N+2];
		int[][] schedule = new int[N+1][2];
		for(int i = 1 ; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			schedule[i][0] = T; //
			schedule[i][1] = P; //
		}
		
		for(int i = 1; i <= N; i++) {
			int next = i + schedule[i][0];
			if(next <= N+1) {
				maxProfit[next] = Math.max(maxProfit[i] + schedule[i][1], maxProfit[next]);
			}
			maxProfit[i+1] = Math.max(maxProfit[i], maxProfit[i+1]);
		}
		
		
		System.out.println(maxProfit[N+1]);
	}

}
 