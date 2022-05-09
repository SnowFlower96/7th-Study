import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_18429_근손실 {

	static int N, K, routine;
	static int kit[], input[];
	static boolean isChecked[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer  st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		kit= new int[N];
		input = new int[N];
		isChecked = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());			
		}
		
		routine = 0;
		Permutation(0);
		
		System.out.println(routine);
	}
	
	private static void Permutation(int cnt) {
		
		if(cnt == N) {
			int weight = 500;
			for(int i =0 ; i < N; i++) {
				if(weight+input[i]-K < 500 ) return;
				else weight = weight + input[i] -K;
			}
			routine++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			
			if(isChecked[i]) continue;
			
			input[cnt] = kit[i];
			isChecked[i] = true;
			
			Permutation(cnt+1);
			isChecked[i] = false;
		}
		
	}

}
