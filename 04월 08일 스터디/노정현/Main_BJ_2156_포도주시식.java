package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Study
 * @author jhno96
 * @date 2022. 4. 8.
 */
public class Main_BJ_2156_포도주시식 {

	static int n;
	static int[] wines;
	static Integer[] table;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		wines = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}
		
		table = new Integer[N + 1];
		
		// top-down
//		table[0] = 0;
//		table[1] = wines[1];
//		
//		if(N > 1) {
//			table[2] = wines[1] + wines[2];
//		}
//		System.out.println(recur(N));
		
		// bottom-up
		table[0] = 0;
		for(int i = 1; i <= N; i++) {
			if(i <= 2) {
				table[i] = table[i - 1] + wines[i];
			}
			else 
				table[i] = Math.max(table[i - 1], Math.max(table[i - 2] + wines[i], table[i - 3] + wines[i - 1] + wines[i]));
		}
		System.out.println(table[N]);
	}
	
	@SuppressWarnings("unused")
	private static int recur(int n) {
		
		if(table[n] == null) {
			table[n] = Math.max(Math.max(recur(n - 2), recur(n - 3) + wines[n - 1]) + wines[n], recur(n - 1));
		}
		
		return table[n];
	}

}
