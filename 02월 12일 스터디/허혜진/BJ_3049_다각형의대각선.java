package study0212;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_3049_다각형의대각선 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int nFac=1;
		int fourFac = 1;

		// n!/(n-r)!
		for(int i=1; i<=4; i++) {
			nFac *= N;
			N--;
		}
		//4!
		for(int i=1; i<=4; i++) {
			fourFac *= i;
		}
		//n!/(n-r)!*r! 
		System.out.println(nFac/fourFac);
	}

}
