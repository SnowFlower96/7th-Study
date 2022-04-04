package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_11726_2xn타일링 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[] table = new long[N + 1];
		
		for(int i = 1; i <= N; i++) {
			if(i <= 2) table[i] = i;
			else table[i] = (table[i - 1] + table[i - 2]) % 10007;
		}
		
		System.out.println(table[N]);
	}

}	
