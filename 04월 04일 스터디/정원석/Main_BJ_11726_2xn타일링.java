import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_11726_2xn타일링 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		
		long D[] = new long[(int) (N+1)];		
		
		for(int i = 1; i <= N; i++) {
			if( i == 1) D[1] = 1;
			else if( i == 2) D[2] = 2;
			else D[i] = ( D[i-1] + D[i-2] ) % 10007;
		}
		
		long result = D[(int) N];
		System.out.println(result);

	}

}
